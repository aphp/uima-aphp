

/* First created by JCasGen Wed Feb 07 00:31:20 CET 2018 */
package fr.aphp.wind.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Feb 07 00:31:20 CET 2018
 * XML source: /home/nps/workspace/NoteDeid/src/main/resources/types/NoteDeidTypeSystem.xml
 * @generated */
public class SourceDocumentInformation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SourceDocumentInformation.class);
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
  protected SourceDocumentInformation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SourceDocumentInformation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SourceDocumentInformation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SourceDocumentInformation(JCas jcas, int begin, int end) {
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
  //* Feature: uri

  /** getter for uri - gets 
   * @generated
   * @return value of the feature 
   */
  public String getUri() {
    if (SourceDocumentInformation_Type.featOkTst && ((SourceDocumentInformation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "org.apache.uima.type.SourceDocumentInformation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SourceDocumentInformation_Type)jcasType).casFeatCode_uri);}
    
  /** setter for uri - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setUri(String v) {
    if (SourceDocumentInformation_Type.featOkTst && ((SourceDocumentInformation_Type)jcasType).casFeat_uri == null)
      jcasType.jcas.throwFeatMissing("uri", "org.apache.uima.type.SourceDocumentInformation");
    jcasType.ll_cas.ll_setStringValue(addr, ((SourceDocumentInformation_Type)jcasType).casFeatCode_uri, v);}    
   
    
  //*--------------*
  //* Feature: offsetInSource

  /** getter for offsetInSource - gets 
   * @generated
   * @return value of the feature 
   */
  public int getOffsetInSource() {
    if (SourceDocumentInformation_Type.featOkTst && ((SourceDocumentInformation_Type)jcasType).casFeat_offsetInSource == null)
      jcasType.jcas.throwFeatMissing("offsetInSource", "org.apache.uima.type.SourceDocumentInformation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SourceDocumentInformation_Type)jcasType).casFeatCode_offsetInSource);}
    
  /** setter for offsetInSource - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setOffsetInSource(int v) {
    if (SourceDocumentInformation_Type.featOkTst && ((SourceDocumentInformation_Type)jcasType).casFeat_offsetInSource == null)
      jcasType.jcas.throwFeatMissing("offsetInSource", "org.apache.uima.type.SourceDocumentInformation");
    jcasType.ll_cas.ll_setIntValue(addr, ((SourceDocumentInformation_Type)jcasType).casFeatCode_offsetInSource, v);}    
   
    
  //*--------------*
  //* Feature: documentSize

  /** getter for documentSize - gets 
   * @generated
   * @return value of the feature 
   */
  public int getDocumentSize() {
    if (SourceDocumentInformation_Type.featOkTst && ((SourceDocumentInformation_Type)jcasType).casFeat_documentSize == null)
      jcasType.jcas.throwFeatMissing("documentSize", "org.apache.uima.type.SourceDocumentInformation");
    return jcasType.ll_cas.ll_getIntValue(addr, ((SourceDocumentInformation_Type)jcasType).casFeatCode_documentSize);}
    
  /** setter for documentSize - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDocumentSize(int v) {
    if (SourceDocumentInformation_Type.featOkTst && ((SourceDocumentInformation_Type)jcasType).casFeat_documentSize == null)
      jcasType.jcas.throwFeatMissing("documentSize", "org.apache.uima.type.SourceDocumentInformation");
    jcasType.ll_cas.ll_setIntValue(addr, ((SourceDocumentInformation_Type)jcasType).casFeatCode_documentSize, v);}    
   
    
  //*--------------*
  //* Feature: lastSegment

  /** getter for lastSegment - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getLastSegment() {
    if (SourceDocumentInformation_Type.featOkTst && ((SourceDocumentInformation_Type)jcasType).casFeat_lastSegment == null)
      jcasType.jcas.throwFeatMissing("lastSegment", "org.apache.uima.type.SourceDocumentInformation");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SourceDocumentInformation_Type)jcasType).casFeatCode_lastSegment);}
    
  /** setter for lastSegment - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLastSegment(boolean v) {
    if (SourceDocumentInformation_Type.featOkTst && ((SourceDocumentInformation_Type)jcasType).casFeat_lastSegment == null)
      jcasType.jcas.throwFeatMissing("lastSegment", "org.apache.uima.type.SourceDocumentInformation");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SourceDocumentInformation_Type)jcasType).casFeatCode_lastSegment, v);}    
  }

    