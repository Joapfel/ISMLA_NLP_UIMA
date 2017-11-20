
/* First created by JCasGen Mon Nov 13 15:05:42 CET 2017 */
package de.ws.ismla.tokenizer.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Nov 13 21:55:56 CET 2017
 * @generated */
public class DocumentMetaData_Type extends Annotation_Type {
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = DocumentMetaData.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.ws.ismla.tokenizer.types.DocumentMetaData");
 
  /** @generated */
  final Feature casFeat_sourcePath;
  /** @generated */
  final int     casFeatCode_sourcePath;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getSourcePath(int addr) {
        if (featOkTst && casFeat_sourcePath == null)
      jcas.throwFeatMissing("sourcePath", "de.ws.ismla.tokenizer.types.DocumentMetaData");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sourcePath);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSourcePath(int addr, String v) {
        if (featOkTst && casFeat_sourcePath == null)
      jcas.throwFeatMissing("sourcePath", "de.ws.ismla.tokenizer.types.DocumentMetaData");
    ll_cas.ll_setStringValue(addr, casFeatCode_sourcePath, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public DocumentMetaData_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_sourcePath = jcas.getRequiredFeatureDE(casType, "sourcePath", "uima.cas.String", featOkTst);
    casFeatCode_sourcePath  = (null == casFeat_sourcePath) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sourcePath).getCode();

  }
}



    