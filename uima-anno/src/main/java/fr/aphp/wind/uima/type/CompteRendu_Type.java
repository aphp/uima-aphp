
/* First created by JCasGen Thu Jul 05 13:32:10 CEST 2018 */
package fr.aphp.wind.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.DocumentAnnotation_Type;

/** 
 * Updated by JCasGen Thu Jul 05 13:32:10 CEST 2018
 * @generated */
public class CompteRendu_Type extends DocumentAnnotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = CompteRendu.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.aphp.wind.uima.type.CompteRendu");
 
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
      jcas.throwFeatMissing("typeDoc", "fr.aphp.wind.uima.type.CompteRendu");
    return ll_cas.ll_getIntValue(addr, casFeatCode_typeDoc);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setTypeDoc(int addr, int v) {
        if (featOkTst && casFeat_typeDoc == null)
      jcas.throwFeatMissing("typeDoc", "fr.aphp.wind.uima.type.CompteRendu");
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
      jcas.throwFeatMissing("ids_doc", "fr.aphp.wind.uima.type.CompteRendu");
    return ll_cas.ll_getIntValue(addr, casFeatCode_ids_doc);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIds_doc(int addr, int v) {
        if (featOkTst && casFeat_ids_doc == null)
      jcas.throwFeatMissing("ids_doc", "fr.aphp.wind.uima.type.CompteRendu");
    ll_cas.ll_setIntValue(addr, casFeatCode_ids_doc, v);}
    
  
 
  /** @generated */
  final Feature casFeat_category;
  /** @generated */
  final int     casFeatCode_category;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getCategory(int addr) {
        if (featOkTst && casFeat_category == null)
      jcas.throwFeatMissing("category", "fr.aphp.wind.uima.type.CompteRendu");
    return ll_cas.ll_getStringValue(addr, casFeatCode_category);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setCategory(int addr, String v) {
        if (featOkTst && casFeat_category == null)
      jcas.throwFeatMissing("category", "fr.aphp.wind.uima.type.CompteRendu");
    ll_cas.ll_setStringValue(addr, casFeatCode_category, v);}
    
  



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

 
    casFeat_category = jcas.getRequiredFeatureDE(casType, "category", "uima.cas.String", featOkTst);
    casFeatCode_category  = (null == casFeat_category) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_category).getCode();

  }
}



    