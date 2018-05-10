package fr.aphp.wind.uima.segmenter.pojo;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.JCasFactory.createJCas;

import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import com.opencsv.CSVReader;

import fr.aphp.wind.uima.segmenter.annotator.RegexSectionAE;
import fr.aphp.wind.uima.type.CompteRendu;
import fr.aphp.wind.uima.type.Section;

public class SectionSegmenterPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient AnalysisEngineDescription regexSectionAE;
	private Integer[] arraySection;
	private Integer[] arrayDoc;
	private String[] arrayPattern;
	private int sectionCount;
	private JCas aCas;

	public SectionSegmenterPojo(Integer[] arraySection, Integer[] arrayDoc, String[] arrayPattern)
			throws IOException, UIMAException {
		this.arraySection = arraySection;
		this.arrayDoc = arrayDoc;
		this.arrayPattern = arrayPattern;
		this.initialize();
	}
	
	public SectionSegmenterPojo(String sectionPathCsv)
			throws IOException, UIMAException {
		getSectionDetails(sectionPathCsv);
		this.initialize();
	}

	
	private void getSectionDetails(String sectionPath) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(sectionPath), ",".charAt(0), "\"".charAt(0), true );
	     String [] nextLine;
	     ArrayList<Integer> arrayListSection = new ArrayList<Integer>();
	     ArrayList<Integer> arrayListDoc = new ArrayList<Integer>();
	     ArrayList<String> arrayListPattern = new ArrayList<String>();

	     while ((nextLine = reader.readNext()) != null) {
	        // nextLine[] is an array of values from the line
	    	 arrayListSection.add(Integer.valueOf(nextLine[0]));
	    	 arrayListDoc.add(Integer.valueOf(nextLine[1]));
	    	 arrayListPattern.add(nextLine[2]);
	    	 
	     }	
	     this.arraySection = arrayListSection.toArray(new Integer[arrayListSection.size()]) ;
	     this.arrayDoc = arrayListDoc.toArray(new Integer[arrayListSection.size()]) ;
	     this.arrayPattern = arrayListPattern.toArray(new String[arrayListSection.size()]) ;
	  
	}

	public void initialize() throws IOException, UIMAException {
		aCas = createJCas();
		regexSectionAE = createEngineDescription(RegexSectionAE.class
				, RegexSectionAE.PARAM_ARRAY_SECTION, this.arraySection
				, RegexSectionAE.PARAM_ARRAY_DOC, this.arrayDoc
				, RegexSectionAE.PARAM_ARRAY_PATTERN, this.arrayPattern);
	}

	public String analyzeText(Integer ids_doc, Integer type_doc, String text) throws IOException,
			UIMAException {
		// Get the json
		aCas.reset();
		aCas.setDocumentLanguage("fr");

		CompteRendu  compteRenduAnnot = new CompteRendu(aCas);
		compteRenduAnnot.setTypeDoc(type_doc);
		compteRenduAnnot.setIds_doc(ids_doc);
		compteRenduAnnot.addToIndexes();

		aCas.setDocumentText(text);
		// run the pipeline
		
		SimplePipeline.runPipeline(aCas, regexSectionAE);
	
		String result = "";
		int index = 0;
		sectionCount=JCasUtil.select(aCas, Section.class).size();
		Iterator<Section> sections = JCasUtil.select(aCas, Section.class).iterator();
			while (sections.hasNext()) {
				index++;
				Section section = sections.next();
				result += ids_doc
						+";"+ index
						+";"+ sectionCount
						+";"+ section.getIds_ref_section()
						+";"+ section.getBegin()
						+";"+ section.getEnd()
						+";\""+ section.getCoveredText().replaceAll("\"", "\"\"") + "\"";
				if(sections.hasNext())
					result+="\n";
						
				}
			return result;
	}
	
	public Integer getSectionCount(){
		return this.sectionCount;
		
	}
	
	public void readObject() throws UIMAException, IOException {
			this.initialize();
	
	}

}
