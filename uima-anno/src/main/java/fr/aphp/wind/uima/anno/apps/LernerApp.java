package fr.aphp.wind.uima.anno.apps;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import java.io.File;
import java.util.HashSet;
import java.util.Set;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.cpe.CpePipeline;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import de.tudarmstadt.ukp.dkpro.core.io.brat.BratWriter;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import fr.aphp.wind.uima.anno.writer.BratDeidWriter;
import fr.aphp.wind.uima.anno.writer.Conll2000WriterDeid;

/**
 * @author nps
 *
 */
public class LernerApp {
  public static void main(String[] args) throws Exception {


    String inputPath = args[0];
    String outputPath = args[1];


    /*
     * txt reader
     */
    // @formatter:off
		CollectionReaderDescription reader = createReaderDescription(TextReader.class,
	//					TextReader.PARAM_SOURCE_LOCATION, args[0],
		    			TextReader.PARAM_SOURCE_LOCATION, inputPath,
						TextReader.PARAM_PATTERNS, "*.txt",
						TextReader.PARAM_LANGUAGE, "en");
    // @formatter:on      
    /*
     * tokenizer
     */
    // @formatter:off
		AnalysisEngineDescription	 stanfordSegmenter = createEngineDescription(StanfordSegmenter.class
				, StanfordSegmenter.PARAM_LANGUAGE, "fr"
				, StanfordSegmenter.PARAM_NEWLINE_IS_SENTENCE_BREAK, "TWO_CONSECUTIVE"
				);
        // @formatter:on      


    /*
     * dictionnary annotator
     */
    AnalysisEngineDescription dictTagger =
        AnalysisEngineFactory.createEngineDescriptionFromPath("LernerDictionaryAnnotator.xml");

    AnalysisEngineDescription conllWriter = createEngineDescription(Conll2000WriterDeid.class,
        Conll2000WriterDeid.PARAM_TARGET_LOCATION, new File(outputPath, "data.conll"),
        Conll2000WriterDeid.PARAM_OVERWRITE, true, Conll2000WriterDeid.PARAM_WRITE_CHUNK, true,
        Conll2000WriterDeid.PARAM_WRITE_POS, true, Conll2000WriterDeid.PARAM_SINGULAR_TARGET, true,
        Conll2000WriterDeid.PARAM_WRITE_INDEX, true, Conll2000WriterDeid.PARAM_WRITE_FEATURE, true

    );

    /*
     * what should be exported as brat
     */
    Set<String> set = new HashSet<String>();
    Integer count = 100;
    while (count >= 1) {
      set.add("fr.aphp.wind.uima.type.dict" + count);
      count--;
    }


    set.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token");
    set.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence");



    // @formatter:off
		AnalysisEngineDescription	bratWriter = createEngineDescription(BratDeidWriter.class
//				, BratDeidWriter.PARAM_TARGET_LOCATION, args[1]
			    , BratDeidWriter.PARAM_TARGET_LOCATION, outputPath
				, BratDeidWriter.PARAM_TEXT_ANNOTATION_TYPES, set
				, BratDeidWriter.PARAM_ALL_TYPES, false
				, BratDeidWriter.PARAM_ENABLE_TYPE_MAPPINGS, true
				, BratDeidWriter.PARAM_TYPE_MAPPINGS, new String[]{
						".*\\.([^\\.]+) -> $1"
		         }
				, BratWriter.PARAM_OVERWRITE, true);		
        
		
		/*
         * the whole pipeline
         */
		CpePipeline.runPipeline(
				  reader
				, stanfordSegmenter
				, dictTagger
				, bratWriter
			//	, conllWriter
				);
        // @formatter:on	
  }
}
