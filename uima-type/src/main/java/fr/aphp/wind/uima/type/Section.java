

/* First created by JCasGen Fri Aug 18 12:35:43 CEST 2017 */
package fr.aphp.wind.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Jan 16 10:22:12 CET 2018
 * XML source: /home/nps/workspace/NoteDeid/SectionSegmenterDescriptor.xml
 * @generated */
public class Section extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Section.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Section() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Section(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Section(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Section(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: ids_ref_section

  /** getter for ids_ref_section - gets 
   * @generated
   * @return value of the feature 
   */
  public int getIds_ref_section() {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_ids_ref_section == null)
      jcasType.jcas.throwFeatMissing("ids_ref_section", "org.apache.uima.type.Section");
    return jcasType.ll_cas.ll_getIntValue(addr, ((Section_Type)jcasType).casFeatCode_ids_ref_section);}
    
  /** setter for ids_ref_section - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setIds_ref_section(int v) {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_ids_ref_section == null)
      jcasType.jcas.throwFeatMissing("ids_ref_section", "org.apache.uima.type.Section");
    jcasType.ll_cas.ll_setIntValue(addr, ((Section_Type)jcasType).casFeatCode_ids_ref_section, v);}    
   
    
  //*--------------*
  //* Feature: section_label

  /** getter for section_label - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSection_label() {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_section_label == null)
      jcasType.jcas.throwFeatMissing("section_label", "org.apache.uima.type.Section");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Section_Type)jcasType).casFeatCode_section_label);}
    
  /** setter for section_label - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSection_label(String v) {
    if (Section_Type.featOkTst && ((Section_Type)jcasType).casFeat_section_label == null)
      jcasType.jcas.throwFeatMissing("section_label", "org.apache.uima.type.Section");
    jcasType.ll_cas.ll_setStringValue(addr, ((Section_Type)jcasType).casFeatCode_section_label, v);}    
  }

    