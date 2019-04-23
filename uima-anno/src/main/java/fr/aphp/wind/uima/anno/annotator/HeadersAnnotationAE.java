package fr.aphp.wind.uima.anno.annotator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.util.CasUtil;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Level;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.unihd.dbs.uima.types.heideltime.Token;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.PropertiesUtils;
import fr.aphp.wind.uima.core.casutils.CasTools;
import fr.aphp.wind.uima.core.stringutils.StringDistance;
import fr.aphp.wind.uima.core.stringutils.StringUtils;

public class HeadersAnnotationAE extends JCasAnnotator_ImplBase {
  private Logger logger = Logger.getLogger(getClass().getName());
  private Pattern pHeader1;
  private Pattern pFooter1;
  private Pattern pFooter2;

  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    pHeader1 = Pattern.compile("^[\\s\\S]+\\n(Références :).*\n");
    // pFooter1 = Pattern.compile("\nInformation aux patients :[\\s\\S]+$");
    pFooter2 = Pattern.compile(
        "\n((?:Rédigé par :?|.*signature [ée]lectronique|Courrier validé|Destinataire :|Information aux patients :|Copie à :))(?:[^\n]*\n){1,20}(?:[^\n]+\n?){0,1}$");

  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {

    try {
	//System.out.println("CHERCHER HEADER ");
      searchRegex("fr.aphp.wind.uima.type.NoteHeader", pHeader1, aJCas);
      // if (!searchRegex("", pFooter1, aJCas)) {
      searchRegex("fr.aphp.wind.uima.type.NoteFooter", pFooter2, aJCas);
      // }


    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } // match found; extract locations of start and end of match
      // (or of entire containing annotation, if that option is
      // on)

  }

  private boolean searchRegex(String annotationType, Pattern p, JCas aJCas) {
    Type tokenType = CasUtil.getAnnotationType(aJCas.getCas(), annotationType);

    Matcher matcher = p.matcher(aJCas.getDocumentText());
    if (matcher.find()) {
	//System.out.println("FOUND HEADER ");
      // create Annotation in CAS
      FeatureStructure fs2 =
          aJCas.getCas().createAnnotation(tokenType, matcher.start(1), matcher.end(1));
      aJCas.getCas().getIndexRepository().addFS(fs2);
      return true;
    }
    return false;
  }

}
