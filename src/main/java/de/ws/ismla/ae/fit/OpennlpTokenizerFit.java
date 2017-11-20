package de.ws.ismla.ae.fit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

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
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class OpennlpTokenizerFit extends JCasAnnotator_ImplBase {

	public static final String PARAM_TOKENIZER_EN = "modelLocation";
	@ConfigurationParameter(name="PARAM_TOKENIZER_EN", defaultValue="src/main/resources/de/ws/ismla/models/en-token.bin", description="path to the tokenizer model")
	private String modelLocation;
	
	private InputStream streamInTokenizer;
	private TokenizerModel modelTokenizer;
	private Tokenizer tokenizer;

	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(aContext);

		try {

			streamInTokenizer = new FileInputStream(modelLocation);
			modelTokenizer = new TokenizerModel(streamInTokenizer);
			tokenizer = new TokenizerME(modelTokenizer);

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

		for(Sentence sent : JCasUtil.select(arg0, Sentence.class)){
			String actualSentence = sent.getCoveredText();

			Span tokenSpans[] = tokenizer.tokenizePos(actualSentence);

			for (int i = 0; i < tokenSpans.length; i++) {

				Span tokenSpan = tokenSpans[i];
				Token token = new Token(arg0);
				token.setBegin(sent.getBegin() + tokenSpan.getStart());
				token.setEnd(sent.getBegin() + tokenSpan.getEnd());
				token.addToIndexes(arg0);
				
//				System.out.println(token.getCoveredText());
//				System.out.println(token);

			}
		}
	}
}
