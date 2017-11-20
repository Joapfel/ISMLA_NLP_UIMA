package de.ws.ismla.CASConsumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import de.ws.ismla.tokenizer.types.Sentence;
import de.ws.ismla.tokenizer.types.Token;

public class StatsConsumer extends CasConsumer_ImplBase {
	
	final private String OUTPUT_DIR = "outputDir";
	
	private File out;
	private int c;
	
	@Override
	public void initialize() throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize();
		
		String outputDir = new File((String)getConfigParameterValue(OUTPUT_DIR)).getAbsolutePath();
		out = new File(outputDir);
		
		if(!out.exists() || !out.isDirectory()){
			throw new ResourceInitializationException();
		}
		c = 0;
	}

	public void processCas(CAS arg0) throws ResourceProcessException {
		// TODO Auto-generated method stub
		try {
			
			JCas jcas = arg0.getJCas();
			File file = new File(out + "/" + "doc" + c + ".tsv");
			c++;
			
			PrintWriter pw = new PrintWriter(file);
			pw.println("language:" + "\t" + arg0.getDocumentLanguage());
			pw.println("sentences:" + "\t" + jcas.getAnnotationIndex(Sentence.class).size());
			pw.println("tokens:" + "\t" + jcas.getAnnotationIndex(Token.class).size());
			
			//get token types
			HashSet<String> posTags = new HashSet<String>();
			for(Token token : jcas.getAnnotationIndex(Token.class)){
				String pos = token.getPOS();
				//can i remove this if statement?
				if(!posTags.contains(pos.toUpperCase())){
					posTags.add(pos.toUpperCase());
				}
			}
			
			pw.println("token types: " + "\t" + posTags.size());
			float ttr = (posTags.size() / jcas.getAnnotationIndex(Token.class).size());
			pw.println("ttr:" + "\t" + ttr);
			pw.close();
			
		} catch (CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
