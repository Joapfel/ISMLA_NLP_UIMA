package de.ws.ismla.ae.fit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.fit.component.CasConsumer_ImplBase;
import org.apache.uima.fit.component.JCasAnnotator_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import de.ws.ismla.tokenizer.types.Sentence;
import de.ws.ismla.tokenizer.types.Token;

public class StatsConsumerFit extends JCasAnnotator_ImplBase {

	public static final String PARAM_OUTPUT_DIR = "outputDir";
	@ConfigurationParameter(name="PARAM_OUTPUT_DIR", defaultValue="src/main/resources/de/ws/ismla/outputDir")
	private String outputDir;

	private File out;
	private int c;

	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(context);
		
		out = new File(outputDir);

		if (!out.exists() || !out.isDirectory()) {
			throw new ResourceInitializationException();
		}
		c = 0;
	}

	@Override
	public void process(JCas arg0) throws AnalysisEngineProcessException {
		// TODO Auto-generated method stub
		try {

			File file = new File(out + "/" + "doc" + c + ".tsv");
			c++;

			PrintWriter pw = new PrintWriter(file);
			pw.println("language:" + "\t" + arg0.getDocumentLanguage());
			pw.println("sentences:" + "\t" + arg0.getAnnotationIndex(Sentence.class).size());
			pw.println("tokens:" + "\t" + arg0.getAnnotationIndex(Token.class).size());

			// get token types
			HashSet<String> posTags = new HashSet<String>();
			for (Token token : arg0.getAnnotationIndex(Token.class)) {
				String pos = token.getPOS();
				// can i remove this if statement?
				if (!posTags.contains(pos.toUpperCase())) {
					posTags.add(pos.toUpperCase());
				}
			}

			pw.println("token types: " + "\t" + posTags.size());
			float ttr = (posTags.size() / arg0.getAnnotationIndex(Token.class).size());
			pw.println("ttr:" + "\t" + ttr);
			pw.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
