
/* First created by JCasGen Wed Feb 07 23:51:37 CET 2018 */
package fr.aphp.wind.uima.type;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk_Type;

/** 
 * Updated by JCasGen Wed Feb 07 23:51:37 CET 2018
 * @generated */
public class NoteFooterChunk_Type extends Chunk_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NoteFooterChunk.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.type.NoteFooterChunk");
 
  /** @generated */
  final Feature casFeat_chunkValue;
  /** @generated */
  final int     casFeatCode_chunkValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getChunkValue(int addr) {
        if (featOkTst && casFeat_chunkValue == null)
      jcas.throwFeatMissing("chunkValue", "org.apache.uima.type.NoteFooterChunk");
    return ll_cas.ll_getStringValue(addr, casFeatCode_chunkValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setChunkValue(int addr, String v) {
        if (featOkTst && casFeat_chunkValue == null)
      jcas.throwFeatMissing("chunkValue", "org.apache.uima.type.NoteFooterChunk");
    ll_cas.ll_setStringValue(addr, casFeatCode_chunkValue, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public NoteFooterChunk_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_chunkValue = jcas.getRequiredFeatureDE(casType, "chunkValue", "uima.cas.String", featOkTst);
    casFeatCode_chunkValue  = (null == casFeat_chunkValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_chunkValue).getCode();

  }
}



    