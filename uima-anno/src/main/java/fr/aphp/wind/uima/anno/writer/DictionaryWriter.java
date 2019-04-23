/*******************************************************************************
 * Copyright 2014
 * Ubiquitous Knowledge Processing (UKP) Lab and FG Language Technology
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package fr.aphp.wind.uima.anno.writer;

import static org.apache.commons.io.IOUtils.closeQuietly;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.io.JCasFileWriter_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import fr.aphp.wind.uima.core.stringutils.StringUtils;
import fr.aphp.wind.uima.type.SourceDocumentInformation;

/**
 * <p>
 * Writes the CoNLL 2000 chunking format. The columns are separated by spaces.
 * </p>
 * 
 * <pre>
 * <code>
 * He        PRP  B-NP
 * reckons   VBZ  B-VP
 * the       DT   B-NP
 * current   JJ   I-NP
 * account   NN   I-NP
 * deficit   NN   I-NP
 * will      MD   B-VP
 * narrow    VB   I-VP
 * to        TO   B-PP
 * only      RB   B-NP
 * #         #    I-NP
 * 1.8       CD   I-NP
 * billion   CD   I-NP
 * in        IN   B-PP
 * September NNP  B-NP
 * .         .    O
 * </code>
 * </pre>
 * 
 * <ol>
 * <li>FORM - token</li>
 * <li>POSTAG - part-of-speech tag</li>
 * <li>CHUNK - chunk (BIO encoded)</li>
 * </ol>
 * 
 * <p>
 * Sentences are separated by a blank new line.
 * </p>
 * 
 * @see <a href="http://www.cnts.ua.ac.be/conll2000/chunking/">CoNLL 2000 shared
 *      task</a>
 */
@TypeCapability(inputs = { "de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData",
		"de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence",
		"de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token",
		"de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk" })
public class DictionaryWriter extends JCasFileWriter_ImplBase {
	private Logger logger = Logger.getLogger(getClass().getName());
	/**
	 * Name of configuration parameter that contains the character encoding used
	 * by the input files.
	 */
	public static final String PARAM_ENCODING = ComponentParameters.PARAM_SOURCE_ENCODING;
	@ConfigurationParameter(name = PARAM_ENCODING, mandatory = true, defaultValue = "UTF-8")
	private String encoding;

	public static final String PARAM_FILENAME_SUFFIX = "filenameSuffix";
	@ConfigurationParameter(name = PARAM_FILENAME_SUFFIX, mandatory = true, defaultValue = ".conll")
	private String filenameSuffix;




	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		PrintWriter out = null;
		try {
            SourceDocumentInformation srcDocInfo = new SourceDocumentInformation(aJCas);
            logger.warn(getUriShort(aJCas));
			out = new PrintWriter(new OutputStreamWriter(getOutputStream(aJCas
					,  "" ), encoding));
			convert(aJCas, out);
		} catch (Exception e) {
			
			throw new AnalysisEngineProcessException(e);
		} finally {
			closeQuietly(out);
		}
	}

	private void convert(JCas aJCas, PrintWriter aOut) {
		
	
		int sentenceCount = 1;
		for (de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence sentence : JCasUtil.select(aJCas,
				de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence.class)) {
			
//			logger.log(Level.ERROR,String.format("Sentence %s: >>%s<<", sentenceCount, sentence.getCoveredText()));
			int chunkCount = 1;
		
			String result = "";
			/* all Noun Phrases within that sentence */
			int count= 0;
			for (Token token : JCasUtil.selectCovered(aJCas, Token.class, sentence)) {
				count++;
			//	logger.log(Level.DEBUG, String.format("token %s: >>%s<< (pos:%s)", count, token.getCoveredText() ,token.getPos().getPosValue()));
				result += token.getCoveredText() + " ";
//	            logger.log(Level.ERROR,String.format("Token %s: >>%s<<", sentenceCount, token.getCoveredText()));

				
			} /* for each noun phrase within the sentence */
		//	result += "\n";
		//	if(!". \n".equals(result))
			aOut.println(result);
	

		} /* for each sentence */
	//	System.out.println(result);
		
//		for (de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk chunk : JCasUtil.select(aJCas,
//				de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk.class)) {
//			logger.log(Level.DEBUG,String.format("chunk :<<%s>>.((%s))", chunk.getCoveredText(), chunk.getChunkValue()));
//			
//		}
		
//		for (org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation chunk : JCasUtil.select(aJCas,
//				org.apache.ctakes.typesystem.type.textsem.IdentifiedAnnotation.class)) {
//			logger.log(Level.DEBUG,String.format("identified :<<%s>>.((%s))", chunk.getCoveredText(), chunk.getPolarity()));
//			
//		}
		
		
	
//		for ( TOP chunk : JCasUtil.selectAll(aJCas )) {
//			System.out.println(String.format("Type :<<%s>>", chunk.getType()));
//			
//		}
	
		
	
	}
	
	private String recode(String in){
		
		return in.replaceAll("[^\\p{Print}]", "");
	}
	

	private String getUri(JCas aJCas) {
		FSIterator<Annotation> a = aJCas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
		SourceDocumentInformation b = null;
		if (a.hasNext()) {
			b = (SourceDocumentInformation) a.next();
			return b.getUri();
		}
		//file:/tmp/input/CR-CONS_24081078.txt
		return DocumentMetaData.get(aJCas).getDocumentUri().replaceAll(".*?([^/]+).csv$", "$1");
	}

	private String getUriShort(JCas aJCas) {
		// CR-CONS_24081078
		// CR-CONS
		return getUri(aJCas).replaceAll("^(.*).csv$", "$1");
	}

}
