package de.ws.ismla.collectionReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;

import de.ws.ismla.tokenizer.types.LineMetaData;

public class SingleLineReader extends CollectionReader_ImplBase {

	final private String LANGUAGE = "language";
	final private String INPUT_FILE = "inputFile";
	private String inputfile;
	private String language;

	File input;
	Scanner scanner;

	private int processedLines;
	private int totalLines;

	@Override
	public void initialize() throws ResourceInitializationException {
		// TODO Auto-generated method stub
		super.initialize();

		inputfile = new File((String) getConfigParameterValue(INPUT_FILE)).getAbsolutePath();
		language = (String) getConfigParameterValue(LANGUAGE);
		input = new File(inputfile);

		if (!input.exists()) {
			throw new ResourceInitializationException();
		}

		processedLines = 0;

		try {

			scanner = new Scanner(input);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void getNext(CAS arg0) throws IOException, CollectionException {
		// TODO Auto-generated method stub
		//TODO: replace with hasNext method
		if (scanner.hasNextLine()) {
			String line = scanner.nextLine();

			if (!line.isEmpty()) {
				try {

					JCas jcas = arg0.getJCas();
					jcas.setDocumentText(line);
					jcas.setDocumentLanguage(language);

					LineMetaData meta = new LineMetaData(jcas);
					meta.setBegin(0);
					meta.setEnd(jcas.getDocumentText().length());
					meta.setLineNumber(processedLines);
					meta.addToIndexes(jcas);
					
					//ensure me that things are happening
					System.out.println(line);
					
					processedLines++;
					totalLines++;

				} catch (CASException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void close() throws IOException {
		// TODO Auto-generated method stub
		scanner.close();

	}

	public Progress[] getProgress() {
		// TODO Auto-generated method stub
		return new Progress[]{new ProgressImpl(processedLines, 0, Progress.ENTITIES)};
	}

	public boolean hasNext() throws IOException, CollectionException {
		// TODO Auto-generated method stub
		return scanner.hasNextLine();
	}

}
