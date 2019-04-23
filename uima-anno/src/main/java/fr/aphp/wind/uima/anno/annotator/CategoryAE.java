package fr.aphp.wind.uima.anno.annotator;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import fr.aphp.wind.uima.type.CompteRendu;
import fr.aphp.wind.uima.type.SourceDocumentInformation;



public class CategoryAE extends JCasAnnotator_ImplBase {
  /**
   * Specify the suffix of text output files. Default value <code>.txt</code>. If the suffix is not
   * needed, provide an empty string as value.
   */

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    CompteRendu compteRenduAnnot = new CompteRendu(aJCas);
    // TODO: get the category from the filename
    compteRenduAnnot.setCategory(getUriShort(aJCas));
    compteRenduAnnot.addToIndexes();
//    System.out.println(getUriShort(aJCas));

  }
  
  private String getUri(JCas aJCas) {
    FSIterator<Annotation> a = aJCas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
    SourceDocumentInformation b = null;
    if (a.hasNext()) {
        b = (SourceDocumentInformation) a.next();
        return b.getUri();
    }
    //file:/tmp/input/CR-CONS_24081078.txt
    return DocumentMetaData.get(aJCas).getDocumentUri().replaceAll(".*?([^/]+).txt$", "$1");
}

private String getUriShort(JCas aJCas) {
    // CR-CONS_24081078
    // CR-CONS
    return getUri(aJCas).replaceAll("^(.*?)_.*$", "$1");
}

}
