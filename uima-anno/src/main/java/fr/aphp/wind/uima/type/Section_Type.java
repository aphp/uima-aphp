
/* First created by JCasGen Thu Jul 05 13:32:11 CEST 2018 */
package fr.aphp.wind.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Jul 05 13:32:11 CEST 2018
 * @generated */
public class Section_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Section.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("fr.aphp.wind.uima.type.Section");
 
  /** @generated */
  final Feature casFeat_ids_ref_section;
  /** @generated */
  final int     casFeatCode_ids_ref_section;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getIds_ref_section(int addr) {
        if (featOkTst && casFeat_ids_ref_section == null)
      jcas.throwFeatMissing("ids_ref_section", "fr.aphp.wind.uima.type.Section");
    return ll_cas.ll_getIntValue(addr, casFeatCode_ids_ref_section);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setIds_ref_section(int addr, int v) {
        if (featOkTst && casFeat_ids_ref_section == null)
      jcas.throwFeatMissing("ids_ref_section", "fr.aphp.wind.uima.type.Section");
    ll_cas.ll_setIntValue(addr, casFeatCode_ids_ref_section, v);}
    
  
 
  /** @generated */
  final Feature casFeat_section_label;
  /** @generated */
  final int     casFeatCode_section_label;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSection_label(int addr) {
        if (featOkTst && casFeat_section_label == null)
      jcas.throwFeatMissing("section_label", "fr.aphp.wind.uima.type.Section");
    return ll_cas.ll_getStringValue(addr, casFeatCode_section_label);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSection_label(int addr, String v) {
        if (featOkTst && casFeat_section_label == null)
      jcas.throwFeatMissing("section_label", "fr.aphp.wind.uima.type.Section");
    ll_cas.ll_setStringValue(addr, casFeatCode_section_label, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Section_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ids_ref_section = jcas.getRequiredFeatureDE(casType, "ids_ref_section", "uima.cas.Integer", featOkTst);
    casFeatCode_ids_ref_section  = (null == casFeat_ids_ref_section) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ids_ref_section).getCode();

 
    casFeat_section_label = jcas.getRequiredFeatureDE(casType, "section_label", "uima.cas.String", featOkTst);
    casFeatCode_section_label  = (null == casFeat_section_label) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_section_label).getCode();

  }
}



    