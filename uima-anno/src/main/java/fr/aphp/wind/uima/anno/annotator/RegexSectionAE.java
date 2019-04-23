package fr.aphp.wind.uima.anno.annotator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;

import fr.aphp.wind.uima.type.CompteRendu;
import fr.aphp.wind.uima.type.Section;




public class RegexSectionAE extends JCasAnnotator_ImplBase {
	/**
	 * Specify the suffix of text output files. Default value <code>.txt</code>.
	 * If the suffix is not needed, provide an empty string as value.
	 */
	public static final String PARAM_ARRAY_SECTION = "ids_ref_section";
	@ConfigurationParameter(name = PARAM_ARRAY_SECTION, mandatory = true)
	private String[] ids_ref_section;

	public static final String PARAM_ARRAY_DOC = "ids_ref_doc";
	@ConfigurationParameter(name = PARAM_ARRAY_DOC, mandatory = true)
	private String[] ids_ref_doc;

	public static final String PARAM_ARRAY_PATTERN = "section_regex";
	@ConfigurationParameter(name = PARAM_ARRAY_PATTERN, mandatory = true)
	private String[] section_regex;


	private String ids_type_doc;
	private CompteRendu cr;

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
	
		cr = JCasUtil.selectSingle(aJCas, CompteRendu.class);
		ids_type_doc = cr.getCategory();
		String ids_ref_section_val;
		String ids_ref_doc_val;
		String section_regex_val;

		Type tokenType = CasUtil.getAnnotationType(aJCas.getCas(), "fr.aphp.wind.uima.type.Section");
//	    fr.aphp.wind.uima.type.Section entete = (Section) aJCas.getCas().createAnnotation(tokenType, 0, aJCas.getDocumentText().length());
//	    entete.setIds_ref_section(-1);
//		aJCas.getIndexRepository().addFS(entete);
//		System.out.println(ids_type_doc);
		for (int i = 0; i < ids_ref_section.length; i++) {// foreach row of the
															// annotations

			ids_ref_section_val = ids_ref_section[i];
			ids_ref_doc_val = ids_ref_doc[i];
			section_regex_val = section_regex[i];
			if (ids_type_doc.equals(ids_ref_doc_val)) {
			//	System.out.println(section_regex[i]);
				String docText = aJCas.getDocumentText();
				
			
				
				int pos = 0;
				Pattern mPattern = Pattern.compile(
						"([\\s\\p{P}\\p{S}]*\n[\n\\s]*)" 
						+ Pattern.quote(section_regex_val) 
						//+ "[\\s\\S\\p{P}\\p{S}]*$"
						);

				Matcher matcher = mPattern.matcher(docText);

				while (pos < docText.length() && matcher.find(pos)) {
//	              System.out.println(ids_ref_section_val);

					// match found; extract locations of start and end of match
					// (or of entire containing annotation, if that option is
					// on)
					int annotStart, annotEnd;

					annotStart = matcher.start()
							+ matcher.group(1).length();//on retire le début qui sert juste à matcher correctement
					annotEnd = matcher.end();

					// create Annotation in CAS
					Section fs = (Section) aJCas.getCas().createAnnotation(tokenType, annotStart, annotEnd);
					fs.setSection_label(section_regex_val);
					aJCas.getIndexRepository().addFS(fs);
					pos = annotEnd;
					break; // get only one regex, the first one
				}
			}
		} // end foreach row annotations
		Section tmp = null;
		int index = 0;
		for (Section section : JCasUtil.select(aJCas, Section.class)) {
			if (index > 0) {
				if(section.getBegin() == tmp.getBegin()){//same match
					if(tmp.getSection_label().length()
							> section.getSection_label().length()){//preceeding was longer
						//System.out.println("REMOVED: " + section.getSection_label());
						section.removeFromIndexes(); // current is removed, we keep preceeding
						continue;
					}else{
					//	System.out.println("REMOVED: " + tmp.getSection_label());
						tmp.removeFromIndexes();
						tmp = section;
						continue;
					}
				}
			//tmp.setEnd(section.getBegin() );
			}
			tmp = section;
			index++;
		}
	}

}
