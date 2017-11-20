

/* First created by JCasGen Mon Nov 13 22:00:38 CET 2017 */
package de.ws.ismla.tokenizer.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Nov 13 22:00:38 CET 2017
 * XML source: /home/johannes/workspace_industrial_strength/NaiveTokenizer/src/main/resources/de/ws/ismla/collectionReader/SingleLineReader.xml
 * @generated */
public class LineMetaData extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(LineMetaData.class);
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
  protected LineMetaData() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public LineMetaData(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public LineMetaData(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public LineMetaData(JCas jcas, int begin, int end) {
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
  //* Feature: lineNumber

  /** getter for lineNumber - gets 
   * @generated
   * @return value of the feature 
   */
  public int getLineNumber() {
    if (LineMetaData_Type.featOkTst && ((LineMetaData_Type)jcasType).casFeat_lineNumber == null)
      jcasType.jcas.throwFeatMissing("lineNumber", "de.ws.ismla.tokenizer.types.LineMetaData");
    return jcasType.ll_cas.ll_getIntValue(addr, ((LineMetaData_Type)jcasType).casFeatCode_lineNumber);}
    
  /** setter for lineNumber - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setLineNumber(int v) {
    if (LineMetaData_Type.featOkTst && ((LineMetaData_Type)jcasType).casFeat_lineNumber == null)
      jcasType.jcas.throwFeatMissing("lineNumber", "de.ws.ismla.tokenizer.types.LineMetaData");
    jcasType.ll_cas.ll_setIntValue(addr, ((LineMetaData_Type)jcasType).casFeatCode_lineNumber, v);}    
  }

    