package org.apache.uima.annotator.DictionaryAnnotator;

import static org.apache.uima.fit.factory.AnalysisEngineFactory.createEngineDescription;
import static org.apache.uima.fit.factory.CollectionReaderFactory.createReaderDescription;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.collection.metadata.CpeDescriptorException;
import org.apache.uima.fit.cpe.CpePipeline;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;
import de.tudarmstadt.ukp.dkpro.core.io.brat.BratWriter;
import de.tudarmstadt.ukp.dkpro.core.io.text.TextReader;
import de.tudarmstadt.ukp.dkpro.core.stanfordnlp.StanfordSegmenter;
import opennlp.tools.stemmer.snowball.SnowballStemmer;
import opennlp.tools.stemmer.snowball.SnowballStemmer.ALGORITHM;

public class DictionaryAnnotatorTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  @Test
  public void test() throws IOException, UIMAException, SAXException, CpeDescriptorException {

    String inputPath = "input/";
    String outputPath = "output/";


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
        boundary.add("de");
        AnalysisEngineDescription    stanfordSegmenter = createEngineDescription(StanfordSegmenter.class
                , StanfordSegmenter.PARAM_LANGUAGE, "fr"
                , StanfordSegmenter.PARAM_NEWLINE_IS_SENTENCE_BREAK, "TWO_CONSECUTIVE"
                
                );
        // @formatter:on      


    /*
     * dictionnary annotator
     */
    AnalysisEngineDescription dictTagger =
        AnalysisEngineFactory.createEngineDescriptionFromPath("DictionaryAnnotator.xml");

    /*
     * what should be exported as brat
     */
    Set<String> set = new HashSet<String>();



    set.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token");
    set.add("de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence");



    // @formatter:off
        AnalysisEngineDescription   bratWriter = createEngineDescription(BratWriter.class
//              , BratDeidWriter.PARAM_TARGET_LOCATION, args[1]
                , BratWriter.PARAM_TARGET_LOCATION, outputPath
                , BratWriter.PARAM_TEXT_ANNOTATION_TYPES, set
                , BratWriter.PARAM_ENABLE_TYPE_MAPPINGS, true
                , BratWriter.PARAM_TYPE_MAPPINGS, new String[]{
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
            //  , conllWriter
                );
        // @formatter:on

  }

  @Test
  public void testStemmer() {
    SnowballStemmer a = new SnowballStemmer(ALGORITHM.FRENCH);
    CharSequence b = a.stem("maladivement");
    System.out.println(b);
    assert ("malad".equals(b));
  }

}
