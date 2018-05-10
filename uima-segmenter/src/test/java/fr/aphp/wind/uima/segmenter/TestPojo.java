package fr.aphp.wind.uima.segmenter;

import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CASException;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.InvalidXMLException;
import org.junit.Test;

import fr.aphp.wind.uima.segmenter.pojo.SentenceSegmenterPojo;
import fr.aphp.wind.uima.segmenter.pojo.SectionSegmenterPojo;

/**
 * Unit test for simple App.
 */
public class TestPojo {
private String text = "Hello world!";
	@Test
	public void testReplace() throws IOException, UIMAException {
		//SentenceSegmenterPojo tt = new SentenceSegmenterPojo();
	//	System.out.println(tt.analyzeText("Hello world"));
	}

	/**
	 * Rigourous Test :-)
	 * 
	 * @throws IOException
	 * @throws UIMAException 
	 */

	@Test
	public void testSection() throws IOException,
			UIMAException {
		SectionSegmenterPojo pj = new SectionSegmenterPojo("testSection.csv");
		String result = pj.analyzeText(1234, 100009, text);
		System.out.println(result);
		assert (pj.getSectionCount().equals(4));

	}
}
