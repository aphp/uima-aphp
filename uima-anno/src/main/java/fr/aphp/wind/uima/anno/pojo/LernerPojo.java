package fr.aphp.wind.uima.anno.pojo;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.collection.metadata.CpeDescriptorException;
import org.apache.uima.fit.cpe.CpePipeline;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.xml.sax.SAXException;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import de.tudarmstadt.ukp.dkpro.core.io.brat.BratWriter;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import fr.aphp.wind.uima.anno.annotator.CategoryAE;
import fr.aphp.wind.uima.anno.annotator.HeadersAnnotationAE;
import fr.aphp.wind.uima.anno.annotator.RegexSectionAE;
import fr.aphp.wind.uima.anno.writer.BratDeidWriter2;
import fr.aphp.wind.uima.anno.writer.Conll2000WriterDeid;

public class LernerPojo {

    private String[] type_section;
    private String[] type_doc;
    private String[] section_regex;

    public void run(String inputPath, String outputPath, boolean multiCore, boolean exportTokens,
	    boolean exportSentence, boolean exportSection, String sectionFilePath, String lang)
	    throws IOException, UIMAException, SAXException, CpeDescriptorException {
	System.out.println(String.format(
		"Multicore:%s ExportTokens:%s ExportSentence:%s ExportSection:%s SectionFilePath:%s Lang:%s",
		multiCore, exportTokens, exportSentence, exportSection, sectionFilePath, lang));
	/*
	 * txt reader
	 */
    // @formatter:off
        CollectionReaderDescription reader = createReaderDescription(TextReader.class,
                        TextReader.PARAM_SOURCE_LOCATION, inputPath,
                        TextReader.PARAM_PATTERNS, "*.txt",
                        TextReader.PARAM_LANGUAGE, "en");
    // @formatter:on      
	/*
	 * tokenizer
	 */
    // @formatter:off
        Set<String> boundary = new HashSet<String>();
        boundary.add("[-]");
        AnalysisEngineDescription    stanfordSegmenter = createEngineDescription(StanfordSegmenter.class
                , StanfordSegmenter.PARAM_LANGUAGE, lang
                , StanfordSegmenter.PARAM_NEWLINE_IS_SENTENCE_BREAK, "TWO_CONSECUTIVE"
                
                );
        // @formatter:on      

	/*
	 * dictionnary annotator
	 */
	AnalysisEngineDescription dictTagger = AnalysisEngineFactory
		.createEngineDescriptionFromPath("LernerDictionaryAnnotator.xml");

	AnalysisEngineDescription conllWriter = createEngineDescription(Conll2000WriterDeid.class,
		Conll2000WriterDeid.PARAM_TARGET_LOCATION, new File(outputPath, "data.conll"),
		Conll2000WriterDeid.PARAM_OVERWRITE, true, Conll2000WriterDeid.PARAM_WRITE_CHUNK,
		true, Conll2000WriterDeid.PARAM_WRITE_POS, true,
		Conll2000WriterDeid.PARAM_SINGULAR_TARGET, true,
		Conll2000WriterDeid.PARAM_WRITE_INDEX, true,
		Conll2000WriterDeid.PARAM_WRITE_FEATURE, true

	);
	AnalysisEngineDescription headersAnnotation = createEngineDescription(HeadersAnnotationAE.class);

	AnalysisEngineDescription categoryAnnot = null;
	AnalysisEngineDescription sectionAnnot = null;
	if (exportSection) {
	    /*
	     * category annot
	     */
	    categoryAnnot = createEngineDescription(CategoryAE.class);
	    /*
	     * section annot
	     */
	    readSectionFile(sectionFilePath);
	    sectionAnnot = createEngineDescription(RegexSectionAE.class,
		    RegexSectionAE.PARAM_ARRAY_SECTION, this.type_section,
		    RegexSectionAE.PARAM_ARRAY_DOC, this.type_doc,
		    RegexSectionAE.PARAM_ARRAY_PATTERN, this.section_regex);
	}

	/*
	 * what should be exported as brat
	 */
	Set<String> set = new HashSet<String>();
	Integer count = 100;
	while (count >= 1) {
	    set.add("fr.aphp.wind.uima.type.dict" + count);
	    count--;
	}

	if (exportTokens) {
	    set.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token");
	}
	if (exportSentence) {
	    set.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence");
	}
	if (exportSection) {
	    set.add("fr.aphp.wind.uima.type.Section");
	    set.add("fr.aphp.wind.uima.type.NoteHeader");
	    set.add("fr.aphp.wind.uima.type.NoteFooter");
	}
	
	Set<String> exclude = new HashSet<String>();
	exclude.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence");
	exclude.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token");
	exclude.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.TokenForm_value");
	exclude.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.TokenForm");
	exclude.add("fr.aphp.wind.uima.type.CompteRendu");
	exclude.add("fr.aphp.wind.uima.type.CompteRendu_typeDoc");
	exclude.add("fr.aphp.wind.uima.type.CompteRendu_ids_doc");
	exclude.add("fr.aphp.wind.uima.type.CompteRendu_category");

    // @formatter:off
        AnalysisEngineDescription   bratWriter = createEngineDescription(BratDeidWriter2.class
                , BratDeidWriter2.PARAM_TARGET_LOCATION, outputPath
                , BratDeidWriter2.PARAM_TEXT_ANNOTATION_TYPES, set
                , BratDeidWriter2.PARAM_EXCLUDE_TYPES, exclude
           //     , BratWriter.PARAM_ALL_TYPES, false
                , BratDeidWriter2.PARAM_ENABLE_TYPE_MAPPINGS, true
                , BratDeidWriter2.PARAM_TYPE_MAPPINGS, new String[]{
                        ".*\\.([^\\.]+) -> $1"
                 }
                , BratDeidWriter2.PARAM_OVERWRITE, true);        
        
// @formatter:on    
	if (exportSection) {
	    System.out.println("Run with section");
	    if (multiCore) {
		/*
		 * the whole pipeline
		 */
		CpePipeline.runPipeline(reader, categoryAnnot, headersAnnotation, sectionAnnot, stanfordSegmenter,
			dictTagger, bratWriter
		// , conllWriter
		);
			    } else {
		SimplePipeline.runPipeline(reader, categoryAnnot, headersAnnotation, sectionAnnot, stanfordSegmenter,
			dictTagger, bratWriter);
	    }
	} else {
	    System.out.println("Run without section");

	    if (multiCore) {
		/*
		 * the whole pipeline
		 */
		CpePipeline.runPipeline(reader, stanfordSegmenter, dictTagger, bratWriter
		// , conllWriter
		);
	    } else {
		SimplePipeline.runPipeline(reader, stanfordSegmenter, dictTagger, bratWriter);
	    }
	}
    }

    private void readSectionFile(String path) {
	CsvParserSettings parserSettings = new CsvParserSettings();
	// the file used in the example uses '\n' as the line separator
	// sequence.
	// the line separator sequence is defined here to ensure systems such as
	// MacOS and Windows
	// are able to process this file correctly (MacOS uses '\r'; and Windows
	// uses '\r\n').
	parserSettings.getFormat().setLineSeparator("\n");
	parserSettings.setHeaderExtractionEnabled(true);
	parserSettings.setQuoteDetectionEnabled(true);
	parserSettings.getFormat().setCharToEscapeQuoteEscaping('"');
	parserSettings.getFormat().setDelimiter(",".charAt(0));
	parserSettings.setMaxCharsPerColumn(-1);

	// creates a CSV parser
	CsvParser parser = new CsvParser(parserSettings);
	parser.beginParsing(getReader(path));

	String[] row;
	ArrayList<String> idsRefSection = new ArrayList<>();
	ArrayList<String> idsRefDoc = new ArrayList<>();
	ArrayList<String> pattern = new ArrayList<>();

	while ((row = parser.parseNext()) != null) {
	    idsRefDoc.add(row[0]);
	    idsRefSection.add(row[1]);
	    pattern.add(row[2]);
	}

	this.type_section = idsRefSection.toArray(new String[idsRefSection.size()]);
	this.type_doc = idsRefDoc.toArray(new String[idsRefDoc.size()]);
	this.section_regex = pattern.toArray(new String[pattern.size()]);

	// The resources are closed automatically when the end of the input is
	// reached,
	// or when an error happens, but you can call stopParsing() at any time.

	// You only need to use this if you are not parsing the entire content.
	// But it doesn't hurt if you call it anyway.
	parser.stopParsing();
    }

    public Reader getReader(String relativePath) {
	try {
	    return new FileReader(relativePath);
	} catch (FileNotFoundException e) {
	    throw new RuntimeException(e);
	}
    }

}
