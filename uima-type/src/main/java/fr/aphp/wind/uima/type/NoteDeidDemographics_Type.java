
/* First created by JCasGen Wed Feb 07 00:31:20 CET 2018 */
package fr.aphp.wind.uima.type;

import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.tcas.DocumentAnnotation_Type;

/** 
 * Updated by JCasGen Wed Feb 07 00:31:20 CET 2018
 * @generated */
public class NoteDeidDemographics_Type extends DocumentAnnotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NoteDeidDemographics.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.type.NoteDeidDemographics");
 
  /** @generated */
  final Feature casFeat_json;
  /** @generated */
  final int     casFeatCode_json;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getJson(int addr) {
        if (featOkTst && casFeat_json == null)
      jcas.throwFeatMissing("json", "org.apache.uima.type.NoteDeidDemographics");
    return ll_cas.ll_getStringValue(addr, casFeatCode_json);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setJson(int addr, String v) {
        if (featOkTst && casFeat_json == null)
      jcas.throwFeatMissing("json", "org.apache.uima.type.NoteDeidDemographics");
    ll_cas.ll_setStringValue(addr, casFeatCode_json, v);}
    
  
 
  /** @generated */
  final Feature casFeat_fileName;
  /** @generated */
  final int     casFeatCode_fileName;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getFileName(int addr) {
        if (featOkTst && casFeat_fileName == null)
      jcas.throwFeatMissing("fileName", "org.apache.uima.type.NoteDeidDemographics");
    return ll_cas.ll_getStringValue(addr, casFeatCode_fileName);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setFileName(int addr, String v) {
        if (featOkTst && casFeat_fileName == null)
      jcas.throwFeatMissing("fileName", "org.apache.uima.type.NoteDeidDemographics");
    ll_cas.ll_setStringValue(addr, casFeatCode_fileName, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public NoteDeidDemographics_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_json = jcas.getRequiredFeatureDE(casType, "json", "uima.cas.String", featOkTst);
    casFeatCode_json  = (null == casFeat_json) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_json).getCode();

 
    casFeat_fileName = jcas.getRequiredFeatureDE(casType, "fileName", "uima.cas.String", featOkTst);
    casFeatCode_fileName  = (null == casFeat_fileName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_fileName).getCode();

  }
}



    