

/* First created by JCasGen Mon Nov 13 15:05:42 CET 2017 */
package de.ws.ismla.tokenizer.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Nov 13 21:55:56 CET 2017
 * XML source: /home/johannes/workspace_industrial_strength/NaiveTokenizer/src/main/resources/de/ws/ismla/collectionReader/RecursiveFileReader.xml
 * @generated */
public class DocumentMetaData extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DocumentMetaData.class);
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
  protected DocumentMetaData() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public DocumentMetaData(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public DocumentMetaData(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public DocumentMetaData(JCas jcas, int begin, int end) {
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
  //* Feature: sourcePath

  /** getter for sourcePath - gets 
   * @generated
   * @return value of the feature 
   */
  public String getSourcePath() {
    if (DocumentMetaData_Type.featOkTst && ((DocumentMetaData_Type)jcasType).casFeat_sourcePath == null)
      jcasType.jcas.throwFeatMissing("sourcePath", "de.ws.ismla.tokenizer.types.DocumentMetaData");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DocumentMetaData_Type)jcasType).casFeatCode_sourcePath);}
    
  /** setter for sourcePath - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSourcePath(String v) {
    if (DocumentMetaData_Type.featOkTst && ((DocumentMetaData_Type)jcasType).casFeat_sourcePath == null)
      jcasType.jcas.throwFeatMissing("sourcePath", "de.ws.ismla.tokenizer.types.DocumentMetaData");
    jcasType.ll_cas.ll_setStringValue(addr, ((DocumentMetaData_Type)jcasType).casFeatCode_sourcePath, v);}    
  }

    