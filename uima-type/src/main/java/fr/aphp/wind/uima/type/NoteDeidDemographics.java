

/* First created by JCasGen Wed Feb 07 00:31:20 CET 2018 */
package fr.aphp.wind.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.tcas.DocumentAnnotation;


/** 
 * Updated by JCasGen Wed Feb 07 00:31:20 CET 2018
 * XML source: /home/nps/workspace/NoteDeid/src/main/resources/types/NoteDeidTypeSystem.xml
 * @generated */
public class NoteDeidDemographics extends DocumentAnnotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NoteDeidDemographics.class);
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
  protected NoteDeidDemographics() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NoteDeidDemographics(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NoteDeidDemographics(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NoteDeidDemographics(JCas jcas, int begin, int end) {
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
  //* Feature: json

  /** getter for json - gets 
   * @generated
   * @return value of the feature 
   */
  public String getJson() {
    if (NoteDeidDemographics_Type.featOkTst && ((NoteDeidDemographics_Type)jcasType).casFeat_json == null)
      jcasType.jcas.throwFeatMissing("json", "org.apache.uima.type.NoteDeidDemographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NoteDeidDemographics_Type)jcasType).casFeatCode_json);}
    
  /** setter for json - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setJson(String v) {
    if (NoteDeidDemographics_Type.featOkTst && ((NoteDeidDemographics_Type)jcasType).casFeat_json == null)
      jcasType.jcas.throwFeatMissing("json", "org.apache.uima.type.NoteDeidDemographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((NoteDeidDemographics_Type)jcasType).casFeatCode_json, v);}    
   
    
  //*--------------*
  //* Feature: fileName

  /** getter for fileName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getFileName() {
    if (NoteDeidDemographics_Type.featOkTst && ((NoteDeidDemographics_Type)jcasType).casFeat_fileName == null)
      jcasType.jcas.throwFeatMissing("fileName", "org.apache.uima.type.NoteDeidDemographics");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NoteDeidDemographics_Type)jcasType).casFeatCode_fileName);}
    
  /** setter for fileName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setFileName(String v) {
    if (NoteDeidDemographics_Type.featOkTst && ((NoteDeidDemographics_Type)jcasType).casFeat_fileName == null)
      jcasType.jcas.throwFeatMissing("fileName", "org.apache.uima.type.NoteDeidDemographics");
    jcasType.ll_cas.ll_setStringValue(addr, ((NoteDeidDemographics_Type)jcasType).casFeatCode_fileName, v);}    
  }

    