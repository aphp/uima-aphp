package fr.aphp.wind.uima.segmenter.pojo;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.JCasFactory.createJCas;

import java.io.IOException;
import java.io.Serializable;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
public class SentenceSegmenterPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient AnalysisEngineDescription stanfordSegmenter;
	private transient JCas aCas;

	public SentenceSegmenterPojo()
			throws InvalidXMLException, ResourceInitializationException, IOException {
		
		this.initialize();
	}

	

	public void initialize() throws InvalidXMLException, ResourceInitializationException, IOException {

		stanfordSegmenter = createEngineDescription(StanfordSegmenter.class
				, StanfordSegmenter.PARAM_LANGUAGE, "fr"
				, StanfordSegmenter.PARAM_NEWLINE_IS_SENTENCE_BREAK, "TWO_CONSECUTIVE"
				);
	}

	public String analyzeText(String text) throws IOException,
			UIMAException {
		
		// Initialize the CAS
		//initialize();
		JCas jCas = createJCas();

		jCas.setDocumentText(text);


		SimplePipeline.runPipeline(jCas
				, stanfordSegmenter
				);
		
		String result = "";
		
		for (de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence sentence : JCasUtil.select(jCas,
				de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence.class)) {

			/* all Noun Phrases within that sentence */
			for (Token token : JCasUtil.selectCovered(jCas, Token.class, sentence)) {
				result += token.getCoveredText() + " ";
			} /* for each noun phrase within the sentence */
			result += "\n";
		} /* for each sentence */
			return result.replaceAll("\n$", "");
	}
	
	


}
