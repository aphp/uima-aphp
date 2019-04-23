package fr.aphp.wind.uima.anno.apps;

import static org.junit.Assert.*;
import java.io.IOException;
import org.apache.uima.UIMAException;
import org.apache.uima.collection.metadata.CpeDescriptorException;
import org.junit.Test;
import org.xml.sax.SAXException;
import fr.aphp.wind.uima.anno.pojo.LernerPojo;

public class LernerAppTest {

  @Test
  public void test() throws UIMAException, IOException, SAXException, CpeDescriptorException {
    LernerPojo lp = new LernerPojo();
    lp.run("input", "output", false, false, false, true, "ref_doc_section.csv", "fr");
  }

}
