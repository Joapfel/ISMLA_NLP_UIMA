package de.ws.ismla.ae;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import de.ws.ismla.tokenizer.types.Sentence;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.util.Span;

public class OpennlpSegmentor extends JCasAnnotator_ImplBase {
	
	//resource key
	//contains relative path to the model
	final private String SEGMENTOR_EN = "opennlp_en_segmentor_model";
	
	InputStream streamIn;
	SentenceModel model;
	SentenceDetectorME detector;
	
	@Override
	public void initialize(UimaContext aContext) throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(aContext);
		
		try {
			
			//get absolute path from relative one
			String path = new File((String)aContext.getConfigParameterValue(SEGMENTOR_EN)).getAbsolutePath();
			//get the model file
			streamIn = new FileInputStream(path);
			//init the model
			model = new SentenceModel(streamIn);
			//init the detector
			detector = new SentenceDetectorME(model);
			
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
		
		String document = arg0.getDocumentText();
		
		Span spans[] = detector.sentPosDetect(document);
		
		for(Span span : spans){
			Sentence sent = new Sentence(arg0);
			sent.setBegin(span.getStart());
			sent.setEnd(span.getEnd());
			sent.addToIndexes(arg0);
		}
	}

}
