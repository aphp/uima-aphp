package fr.aphp.wind.uima.anno.annotator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.jcas.JCas;

import fr.aphp.wind.uima.core.casutils.CasTools;

public class RegexCaptureAE extends JCasAnnotator_ImplBase {
	/**
	 * Specify the suffix of text output files. Default value <code>.txt</code>.
	 * If the suffix is not needed, provide an empty string as value.
	 */
	private Logger logger = Logger.getLogger(getClass().getName());

	public static final String PARAM_ARRAY_ANNO_NAME = "anno_name";
	@ConfigurationParameter(name = PARAM_ARRAY_ANNO_NAME, mandatory = true)
	private String[] anno_name;

	public static final String PARAM_ARRAY_PATTERN = "capture_regex";
	@ConfigurationParameter(name = PARAM_ARRAY_PATTERN, mandatory = true)
	private String[] capture_regex;

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {

		String capture_regex_val;
		String anno_name_val;
		String docText = aJCas.getDocumentText();
		for (int i = 0; i < capture_regex.length; i++) {// foreach row of the
			int pos = 0; // annotations

			capture_regex_val = capture_regex[i];
			anno_name_val = anno_name[i];
			logger.debug(String.format("Regex Search: >%s<", capture_regex_val));
			// System.out.println(capture_regex_val);

			Pattern mPattern = Pattern.compile(capture_regex_val);
			Type tokenType = CasUtil.getAnnotationType(aJCas.getCas(), anno_name_val);

			Matcher matcher = mPattern.matcher(docText);
			while (pos < docText.length() && matcher.find(pos)) {
				// match found; extract locations of start and end of match
				// (or of entire containing annotation, if that option is
				// on)
				int annotStart, annotEnd;

				annotStart = matcher.start(1) + getRealBegin(matcher.group(1));
				// + matcher.group(0).length();//on retire le début qui sert
				// juste à matcher correctement
				annotEnd = matcher.end(1) - getRealEnd(matcher.group(1)) + 1;

				// create Annotation in CAS
				FeatureStructure fs2 = aJCas.getCas().createAnnotation(tokenType, annotStart, annotEnd);
				aJCas.getCas().getIndexRepository().addFS(fs2);
				pos = matcher.end(1);
				logger.info(String.format("Regex Add: >%s<", CasTools.getTextContent(fs2)));

			}

		} // end foreach row annotations
	}

	private int getRealBegin(String match) {
		int result = 0;
		while (result < match.length()) {
			if ((String.valueOf(match.charAt(result))).matches(" ")) {
				result++;
			} else {
				break;
			}
		}
		return result;
	}

	private int getRealEnd(String match) {
		int result = match.length() - 1;
		while (result > 0) {
			if ((String.valueOf(match.charAt(result))).matches(" ")) {
				result--;
			} else {
				break;
			}
		}
		return match.length() - result;
	}

}
