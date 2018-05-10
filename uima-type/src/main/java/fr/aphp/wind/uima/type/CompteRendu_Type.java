
/* First created by JCasGen Fri Aug 18 15:20:56 CEST 2017 */
package fr.aphp.wind.uima.type;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.DocumentAnnotation_Type;

/** 
 * Updated by JCasGen Tue Jan 16 10:22:12 CET 2018
 * @generated */
public class CompteRendu_Type extends DocumentAnnotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CompteRendu.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.type.CompteRendu");
 
  /** @generated */
  final Feature casFeat_typeDoc;
  /** @generated */
  final int     casFeatCode_typeDoc;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getTypeDoc(int addr) {
        if (featOkTst && casFeat_typeDoc == null)
      jcas.throwFeatMissing("typeDoc", "org.apache.uima.type.CompteRendu");
    return ll_cas.ll_getIntValue(addr, casFeatCode_typeDoc);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTypeDoc(int addr, int v) {
        if (featOkTst && casFeat_typeDoc == null)
      jcas.throwFeatMissing("typeDoc", "org.apache.uima.type.CompteRendu");
    ll_cas.ll_setIntValue(addr, casFeatCode_typeDoc, v);}
    
  
 
  /** @generated */
  final Feature casFeat_ids_doc;
  /** @generated */
  final int     casFeatCode_ids_doc;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getIds_doc(int addr) {
        if (featOkTst && casFeat_ids_doc == null)
      jcas.throwFeatMissing("ids_doc", "org.apache.uima.type.CompteRendu");
    return ll_cas.ll_getIntValue(addr, casFeatCode_ids_doc);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIds_doc(int addr, int v) {
        if (featOkTst && casFeat_ids_doc == null)
      jcas.throwFeatMissing("ids_doc", "org.apache.uima.type.CompteRendu");
    ll_cas.ll_setIntValue(addr, casFeatCode_ids_doc, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public CompteRendu_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_typeDoc = jcas.getRequiredFeatureDE(casType, "typeDoc", "uima.cas.Integer", featOkTst);
    casFeatCode_typeDoc  = (null == casFeat_typeDoc) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_typeDoc).getCode();

 
    casFeat_ids_doc = jcas.getRequiredFeatureDE(casType, "ids_doc", "uima.cas.Integer", featOkTst);
    casFeatCode_ids_doc  = (null == casFeat_ids_doc) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ids_doc).getCode();

  }
}



    