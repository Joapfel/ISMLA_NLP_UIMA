package de.ws.ismla.ae;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import de.ws.ismla.tokenizer.types.Sentence;
import de.ws.ismla.tokenizer.types.Token;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

/*
 * the pos tagger was added for test purposes - remove it !
 */
public class OpennlpTokenizer extends JCasAnnotator_ImplBase {
	
	final private String TOKENIZER_EN = "opennlp_en_tokenizer_model";
	final private String POSTAGGER_EN = "opennlp_en_postagger_model";
	
	private InputStream streamInTokenizer;
	private TokenizerModel modelTokenizer;
	private Tokenizer tokenizer;
	
	private InputStream streamInPOSTagger;
	private POSModel modelPOSTagger;
	private POSTaggerME tagger;
	
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(aContext);
		
		String pathToTokenizer = (String)aContext.getConfigParameterValue(TOKENIZER_EN);
		String pathToPOSTagger = (String) aContext.getConfigParameterValue(POSTAGGER_EN);
		
		try {
			
			streamInTokenizer = new FileInputStream(pathToTokenizer);
			modelTokenizer = new TokenizerModel(streamInTokenizer);
			tokenizer = new TokenizerME(modelTokenizer);
			
			streamInPOSTagger = new FileInputStream(pathToPOSTagger);
			modelPOSTagger = new POSModel(streamInPOSTagger);
			tagger = new POSTaggerME(modelPOSTagger);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void process(JCas arg0) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub

		Iterator<Sentence> sentIterator = arg0.getAnnotationIndex(Sentence.class).iterator();
		
		while (sentIterator.hasNext()){	
			Sentence sent = sentIterator.next();
			String actualSentence = sent.getCoveredText();
				
			Span tokenSpans[] = tokenizer.tokenizePos(actualSentence);
			String[] tokensArr = tokenizer.tokenize(actualSentence);
			String[] tagged = tagger.tag(tokensArr);
			
//			for(String s : tagged){
//				System.out.println(s);
//			}
			
			for (int i = 0; i < tokenSpans.length; i++){
				
				Span tokenSpan = tokenSpans[i];
				Token token = new Token(arg0);
				token.setBegin(sent.getBegin() + tokenSpan.getStart());
				token.setEnd(sent.getBegin() + tokenSpan.getEnd());
//				token.setPOS(tagged[i]);
				
				token.addToIndexes(arg0);
				
			}
		}
	}
}
