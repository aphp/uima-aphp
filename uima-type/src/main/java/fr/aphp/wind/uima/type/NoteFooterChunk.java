

/* First created by JCasGen Wed Feb 07 23:51:37 CET 2018 */
package fr.aphp.wind.uima.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;


/** 
 * Updated by JCasGen Wed Feb 07 23:51:37 CET 2018
 * XML source: /home/nps/workspace/NoteDeid/src/main/resources/types/NoteDeidTypeSystem.xml
 * @generated */
public class NoteFooterChunk extends Chunk {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NoteFooterChunk.class);
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
  protected NoteFooterChunk() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NoteFooterChunk(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NoteFooterChunk(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NoteFooterChunk(JCas jcas, int begin, int end) {
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
    if (NoteFooterChunk_Type.featOkTst && ((NoteFooterChunk_Type)jcasType).casFeat_chunkValue == null)
      jcasType.jcas.throwFeatMissing("chunkValue", "org.apache.uima.type.NoteFooterChunk");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NoteFooterChunk_Type)jcasType).casFeatCode_chunkValue);}
    
  /** setter for chunkValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setChunkValue(String v) {
    if (NoteFooterChunk_Type.featOkTst && ((NoteFooterChunk_Type)jcasType).casFeat_chunkValue == null)
      jcasType.jcas.throwFeatMissing("chunkValue", "org.apache.uima.type.NoteFooterChunk");
    jcasType.ll_cas.ll_setStringValue(addr, ((NoteFooterChunk_Type)jcasType).casFeatCode_chunkValue, v);}    
  }

    