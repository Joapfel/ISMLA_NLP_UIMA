package de.ws.ismla.ae.fit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import de.ws.ismla.tokenizer.types.Sentence;
import de.ws.ismla.tokenizer.types.Token;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerModel;

public class OpennlpPOSTaggerFit extends JCasAnnotator_ImplBase {
	
	public static final String PARAM_POSTAGGER_EN = "modelLocation";
	@ConfigurationParameter(name="PARAM_POSTAGGER_EN", defaultValue="src/main/resources/de/ws/ismla/models/en-pos-maxent.bin")
	private String modelLocation;
	
	private InputStream streamIn;
	private POSModel model;
	private POSTaggerME tagger;
	
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(context);
		
		try {
			
			streamIn = new FileInputStream(modelLocation);
			model = new POSModel(streamIn);
			tagger = new POSTaggerME(model);
			
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
		
		for(Sentence sentence : JCasUtil.select(arg0, Sentence.class)){

			ArrayList<Token> sentenceTmp = new ArrayList<Token>();
			
			//subiteration for getting the tokens
			for(Token token : JCasUtil.subiterate(arg0, Token.class, sentence, true, true)){
				sentenceTmp.add(token);
			}
			
			//toarray
			String[] sentenceArr = new String[sentenceTmp.size()];
			for(int i = 0; i < sentenceTmp.size(); i++){
				sentenceArr[i] = sentenceTmp.get(i).getCoveredText();
			}
			
			//predict tags
			String[] tags = tagger.tag(sentenceArr);
			
			//add pos info
			for(int i = 0; i < sentenceTmp.size(); i++){
				Token token = sentenceTmp.get(i);
				token.setPOS(tags[i]);
				
//				System.out.println(token.getCoveredText());
//				System.out.println(token);
			}
		}

	}

}
