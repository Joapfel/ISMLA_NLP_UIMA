package de.ws.ismla.ae.fit;

import java.io.File;
import java.io.IOException;

import org.apache.uima.UIMAException;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.collection.CollectionReaderDescription;
import org.apache.uima.fit.factory.AnalysisEngineFactory;
import org.apache.uima.fit.factory.CollectionReaderFactory;
import org.apache.uima.fit.factory.JCasFactory;
import org.apache.uima.fit.factory.TypeSystemDescriptionFactory;
import org.apache.uima.fit.pipeline.SimplePipeline;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.metadata.TypeSystemDescription;

import de.ws.ismla.collectionReader.fit.RecursiveFileReaderFit;

public class MainFit {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		runSegmentor("This is a test sentence. And we need another sentence. Should we add a third one?");
		runPipeline();
	}
	
	/**
	 * sentence segmentor uima fit version
	 * @param sentences
	 */
	public static void runSegmentor(String sentences){
		
		TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription(new File((String)"src/main/resources/typeSystemDescriptor").getAbsolutePath());
		
		try {

			AnalysisEngine ae = AnalysisEngineFactory.createEngine(OpennlpSegmentorFit.class, tsd, OpennlpSegmentorFit.PARAM_SEGMENTOR_EN, "src/main/resources/de/ws/ismla/models/en-sent.bin");
			JCas jcas = JCasFactory.createJCas(tsd);
			jcas.setDocumentLanguage("en");
			jcas.setDocumentText(sentences);
			
			ae.process(jcas);
			
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UIMAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void runPipeline(){
		
		TypeSystemDescription tsd = TypeSystemDescriptionFactory.createTypeSystemDescription(new File((String)"src/main/resources/typeSystemDescriptor").getAbsolutePath());
		
		try {
			
			CollectionReaderDescription reader =
					CollectionReaderFactory.createReaderDescription(
					RecursiveFileReaderFit.class, tsd, RecursiveFileReaderFit.PARAM_INPUT_DIRECTORY, "src/main/resources/de/ws/ismla/inputFiles",
					RecursiveFileReaderFit.PARAM_LANGUAGE, "en");
			
			AnalysisEngineDescription sent =
					AnalysisEngineFactory.createEngineDescription(OpennlpSegmentorFit.class, tsd,
					OpennlpSegmentorFit.PARAM_SEGMENTOR_EN, "src/main/resources/de/ws/ismla/models/en-sent.bin");
			
			AnalysisEngineDescription token = 
					AnalysisEngineFactory.createEngineDescription(OpennlpTokenizerFit.class, tsd, 
					OpennlpTokenizerFit.PARAM_TOKENIZER_EN, "src/main/resources/de/ws/ismla/models/en-token.bin");
			
			AnalysisEngineDescription tag = 
					AnalysisEngineFactory.createEngineDescription(OpennlpPOSTaggerFit.class, tsd,
							OpennlpPOSTaggerFit.PARAM_POSTAGGER_EN, "src/main/resources/de/ws/ismla/models/en-pos-maxent.bin");
			
			
			AnalysisEngineDescription consumer = 
					  AnalysisEngineFactory.createEngineDescription(
					    StatsConsumerFit.class, 
					    StatsConsumerFit.PARAM_OUTPUT_DIR, "src/main/resources/de/ws/ismla/outputDir");
			
			SimplePipeline.runPipeline(reader, sent, token, tag, consumer);
			
			
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UIMAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
