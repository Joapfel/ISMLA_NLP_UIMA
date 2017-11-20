
/* First created by JCasGen Mon Nov 13 22:00:38 CET 2017 */
package de.ws.ismla.tokenizer.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Nov 13 22:00:38 CET 2017
 * @generated */
public class LineMetaData_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = LineMetaData.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.ws.ismla.tokenizer.types.LineMetaData");
 
  /** @generated */
  final Feature casFeat_lineNumber;
  /** @generated */
  final int     casFeatCode_lineNumber;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getLineNumber(int addr) {
        if (featOkTst && casFeat_lineNumber == null)
      jcas.throwFeatMissing("lineNumber", "de.ws.ismla.tokenizer.types.LineMetaData");
    return ll_cas.ll_getIntValue(addr, casFeatCode_lineNumber);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setLineNumber(int addr, int v) {
        if (featOkTst && casFeat_lineNumber == null)
      jcas.throwFeatMissing("lineNumber", "de.ws.ismla.tokenizer.types.LineMetaData");
    ll_cas.ll_setIntValue(addr, casFeatCode_lineNumber, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public LineMetaData_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_lineNumber = jcas.getRequiredFeatureDE(casType, "lineNumber", "uima.cas.Integer", featOkTst);
    casFeatCode_lineNumber  = (null == casFeat_lineNumber) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_lineNumber).getCode();

  }
}



    