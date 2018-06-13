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
import static org.apache.uima.fit.util.JCasUtil.select;
import static org.apache.uima.fit.util.JCasUtil.selectCovered;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.cas.Feature;
import org.apache.uima.cas.Type;
import org.apache.uima.fit.descriptor.ConfigurationParameter;
import org.apache.uima.fit.descriptor.TypeCapability;
import org.apache.uima.fit.util.JCasUtil;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;

import de.tudarmstadt.ukp.dkpro.core.api.io.IobEncoder;
import de.tudarmstadt.ukp.dkpro.core.api.io.JCasFileWriter_ImplBase;
import de.tudarmstadt.ukp.dkpro.core.api.lexmorph.type.pos.POS;
import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;
import de.tudarmstadt.ukp.dkpro.core.api.parameter.ComponentParameters;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Sentence;
import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.core.api.syntax.type.chunk.Chunk;
import fr.aphp.wind.uima.core.casutils.CasTools;
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
public class Conll2000WriterDeid extends JCasFileWriter_ImplBase {
	private static final String UNUSED = "_";

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

	public static final String PARAM_WRITE_POS = ComponentParameters.PARAM_WRITE_POS;
	@ConfigurationParameter(name = PARAM_WRITE_POS, mandatory = true, defaultValue = "true")
	private boolean writePos;

	public static final String PARAM_WRITE_CHUNK = ComponentParameters.PARAM_WRITE_CHUNK;
	@ConfigurationParameter(name = PARAM_WRITE_CHUNK, mandatory = true, defaultValue = "true")
	private boolean writeChunk;

	public static final String PARAM_WRITE_INDEX = "writeIndex";
	@ConfigurationParameter(name = PARAM_WRITE_INDEX, mandatory = false, defaultValue = "true")
	private boolean writeIndex;

	public static final String PARAM_WRITE_FEATURE = "writeFeature";
	@ConfigurationParameter(name = PARAM_WRITE_FEATURE, mandatory = false, defaultValue = "true")
	private boolean writeFeature;

	@Override
	public void process(JCas aJCas) throws AnalysisEngineProcessException {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new OutputStreamWriter(getOutputStream(aJCas, filenameSuffix), encoding));
			convert(aJCas, out);
		} catch (Exception e) {
			throw new AnalysisEngineProcessException(e);
		} finally {
			closeQuietly(out);
		}
	}

	private void convert(JCas aJCas, PrintWriter aOut) {
		Type chunkType = JCasUtil.getType(aJCas, Chunk.class);
		Feature chunkValue = chunkType.getFeatureByBaseName("chunkValue");
		int tokCount = 0;
		for (Sentence sentence : select(aJCas, Sentence.class)) {
			tokCount++;
			HashMap<Token, Row> ctokens = new LinkedHashMap<Token, Row>();

			// Tokens
			List<Token> tokens = selectCovered(Token.class, sentence);

			// Chunks
			IobEncoder encoder = new IobEncoder(aJCas.getCas(), chunkType, chunkValue);

			for (int i = 0; i < tokens.size(); i++) {

				Row row = new Row();
				row.id = i + 1;
				row.token = tokens.get(i);
				row.chunk = encoder.encode(tokens.get(i));
				ctokens.put(row.token, row);
			}

			// Write sentence in CONLL 2006 format
			for (Row row : ctokens.values()) {
				String pos = UNUSED;
				if (writePos && (row.token.getPos() != null)) {
					POS posAnno = row.token.getPos();
					pos = posAnno.getPosValue();
				}


				String chunk = UNUSED;

				if (writeChunk && (row.chunk != null)) {
					chunk = encoder.encode(row.token);
				}

				if (writeIndex) {
					aOut.printf("%s_%s_%s\t", getUri(aJCas), tokCount, row.id);
				}

				aOut.printf("%s\t", row.token.getCoveredText());

				if (writeFeature) {
					aOut.printf("%s\t", pos);
//Common
					if (CasTools.hasType(aJCas, "fr.aphp.wind.uima.type.GeneralCommon", row.token)) {
						aOut.printf("%s\t", "commun");
					}else{aOut.printf("%s\t", "nothing");}
					if (CasTools.hasType(aJCas, "fr.aphp.wind.uima.type.GeneralMedicalTerm", row.token)) {
						aOut.printf("%s\t", "medical");
					}else{aOut.printf("%s\t", "nothing");}
					if (CasTools.hasType(aJCas, "fr.aphp.wind.uima.type.GeneralVerb", row.token)) {
						aOut.printf("%s\t", "verb");
					}else{aOut.printf("%s\t", "nothing");}
					aOut.printf("%s\t", getUriShort(aJCas));

				}

				aOut.printf("%s\n", chunk);

			}
//			if (writeIndex) {// let join on this
//				aOut.printf("%s_%s_%s\n", getUri(aJCas), tokCount, "end");
//			} else {
				aOut.println();
//			}

			
		}
	}

	private static final class Row {
		int id;
		Token token;
		String chunk;
	}

	private String getUri(JCas aJCas) {
		FSIterator<Annotation> a = aJCas.getAnnotationIndex(SourceDocumentInformation.type).iterator();
		SourceDocumentInformation b = null;
		if (a.hasNext()) {
			b = (SourceDocumentInformation) a.next();
			return b.getUri();
		}
		//file:/tmp/input/CR-CONS_24081078.txt
		return DocumentMetaData.get(aJCas).getDocumentUri().replaceAll(".*?([^/]+).txt$", "$1");
	}

	private String getUriShort(JCas aJCas) {
		// CR-CONS_24081078
		// CR-CONS
		return getUri(aJCas).replaceAll("^(.*)_(.*?)$", "$1");
	}
}
