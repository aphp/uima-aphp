

/* First created by JCasGen Sat Mar 17 18:58:39 CET 2018 */
package fr.aphp.wind.uima.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;


/** 
 * Updated by JCasGen Sat Mar 17 18:58:39 CET 2018
 * XML source: /home/nps/workspace/uima-root/uima-deid/target/jcasgen/typesystem.xml
 * @generated */
public class GeneralNbSecuChunk extends Chunk {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GeneralNbSecuChunk.class);
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
  protected GeneralNbSecuChunk() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public GeneralNbSecuChunk(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public GeneralNbSecuChunk(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public GeneralNbSecuChunk(JCas jcas, int begin, int end) {
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
  //* Feature: chunkValue

  /** getter for chunkValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getChunkValue() {
    if (GeneralNbSecuChunk_Type.featOkTst && ((GeneralNbSecuChunk_Type)jcasType).casFeat_chunkValue == null)
      jcasType.jcas.throwFeatMissing("chunkValue", "fr.aphp.wind.uima.type.GeneralNbSecuChunk");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneralNbSecuChunk_Type)jcasType).casFeatCode_chunkValue);}
    
  /** setter for chunkValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setChunkValue(String v) {
    if (GeneralNbSecuChunk_Type.featOkTst && ((GeneralNbSecuChunk_Type)jcasType).casFeat_chunkValue == null)
      jcasType.jcas.throwFeatMissing("chunkValue", "fr.aphp.wind.uima.type.GeneralNbSecuChunk");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneralNbSecuChunk_Type)jcasType).casFeatCode_chunkValue, v);}    
  }

    