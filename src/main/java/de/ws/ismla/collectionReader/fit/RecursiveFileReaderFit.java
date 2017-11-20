package de.ws.ismla.collectionReader.fit;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.apache.uima.UimaContext;
import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.fit.component.CasCollectionReader_ImplBase;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.ws.ismla.tokenizer.types.DocumentMetaData;

public class RecursiveFileReaderFit extends CasCollectionReader_ImplBase {
	
	public static final String PARAM_LANGUAGE = "language";
	@ConfigurationParameter(name="PARAM_LANGUAGE", defaultValue="en")
	private String language;
	
	public static final String PARAM_INPUT_DIRECTORY = "inputDirectory";
	@ConfigurationParameter(name="PARAM_INPUT_DIRECTORY", defaultValue="src/main/resources/de/ws/ismla/inputFiles")
	private String inputDirectory;
	
	private Iterator<File> iterator;
	private int processedFiles;
	private int totalFiles;
	
	/**
	 * recursive file indexing
	 * @param base
	 * @return all found files
	 */
	private static ArrayList<File> indexFile (File base){
		ArrayList<File> rval = new ArrayList<File>();
		
		for (File f : base.listFiles()){
			if(!f.getName().startsWith(".")){
				if(f.isFile()){
					rval.add(f);
				}else if (f.isDirectory()){
					ArrayList<File> list = indexFile(f);
					rval.addAll(list);
				}
			}
		}
		
		return rval;
	}
	
	
	@Override
	public void initialize(UimaContext context) throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize(context);
		
		File f = new File(inputDirectory);
		
		if(!f.exists() || !f.isDirectory()){
			throw new ResourceInitializationException();
		}
		
		ArrayList<File> files = indexFile(f);
		iterator = files.iterator();
		
		totalFiles = files.size();
		processedFiles = 0;
	}

	public void getNext(CAS arg0) throws IOException, CollectionException {
		// TODO Auto-generated method stub
		
		File nextFile = iterator.next();
		Scanner sc = new Scanner(nextFile);
		
		StringBuilder sb = new StringBuilder();
		while (sc.hasNextLine()){
			sb.append(sc.nextLine());
		}
		sc.close();
		
		try {
			
			JCas jcas = arg0.getJCas();
			jcas.setDocumentText(sb.toString());
			jcas.setDocumentLanguage(language);
			
//			DocumentMetaData meta = new DocumentMetaData(jcas);
//			meta.setBegin(0);
//			meta.setEnd(sb.toString().length());
//			meta.setSourcePath(nextFile.getAbsolutePath());
//			meta.addToIndexes(jcas);
			
		} catch (CASException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		processedFiles++;
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return new Progress[]{new ProgressImpl(processedFiles, totalFiles, Progress.ENTITIES)};
	}

	public boolean hasNext() throws IOException, CollectionException {
		// TODO Auto-generated method stub
		return iterator.hasNext();
	}

}
