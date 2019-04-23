package fr.aphp.wind.uima.anno.apps;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import java.util.HashSet;
import java.util.Set;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.cpe.CpePipeline;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import fr.aphp.wind.uima.anno.annotator.RegexCaptureAE;
//import fr.aphp.wind.uima.anno.writer.BratDeidWriter;
import fr.aphp.wind.uima.anno.writer.DictionaryWriter;

/**
 * @author nps
 *
 */
public class DictionaryTokenizer {
  public static void main(String[] args) throws Exception {


     String inputPath = args[0];
     String outputPath = args[1];
     String lang = args[2];


    /*
     * txt reader
     */
    // @formatter:off
        CollectionReaderDescription reader = createReaderDescription(TextReader.class,
                        TextReader.PARAM_SOURCE_LOCATION, inputPath,
                        TextReader.PARAM_PATTERNS, "*.csv",
                        TextReader.PARAM_LANGUAGE, "en");
    // @formatter:on      
    /*
     * tokenizer
     */
    // @formatter:off
        Set<String> boundary = new HashSet<String>();
        boundary.add("\n");
        AnalysisEngineDescription    stanfordSegmenter = createEngineDescription(StanfordSegmenter.class
                , StanfordSegmenter.PARAM_LANGUAGE, lang
                , StanfordSegmenter.PARAM_WRITE_SENTENCE, false
                , StanfordSegmenter.PARAM_NEWLINE_IS_SENTENCE_BREAK
                , edu.stanford.nlp.process.WordToSentenceProcessor.NewlineIsSentenceBreak.ALWAYS
                , StanfordSegmenter.PARAM_BOUNDARIES_TO_DISCARD, boundary
                );
        // @formatter:on  

    // @formatter:off
        AnalysisEngineDescription   dictWriter = createEngineDescription(DictionaryWriter.class
                , DictionaryWriter.PARAM_TARGET_LOCATION, outputPath
                , DictionaryWriter.PARAM_OVERWRITE, true);  
        
        AnalysisEngineDescription regexCaptureAe = createEngineDescription(RegexCaptureAE.class
            , RegexCaptureAE.PARAM_ARRAY_ANNO_NAME, new String[] {"de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence"}
            , RegexCaptureAE.PARAM_ARRAY_PATTERN, new String[] {"(?m)^(.+?)$"}
            );
        /*
         * the whole pipeline
         */
        CpePipeline.runPipeline(
                  reader
                , regexCaptureAe
                , stanfordSegmenter
                , dictWriter
            //  , conllWriter
                );
  }
}
