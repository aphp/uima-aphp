# USAGE
# 1. Add multiple csv dictionary into input
# 2. Tokenize them by **make tokenizeDict**
# 3. Create an xml dictionary for each of them **make createDict**
# 4. Mention them in the LernerDictionaryAnnotator.xml
# 5. Run the pipeline (multicore true or false) **make run**


# ARGS:
# 1: input
# 2: output
# 3: lang (en or fr)
# 4: multicore : true/false
# 5: [facultatif] toexport: sentence;token;section (one or all or them)
# 6: [facultatif] sectionPathCsv : a csv separated by , quoted, headers
#

run:
	java -cp  uima-anno-1.0-standalone.jar fr.aphp.wind.uima.anno.apps.LernerApp $(input) $(output) false en section ref_doc_section.csv

tokenizeDict:
	java -cp  uima-anno-1.0-standalone.jar fr.aphp.wind.uima.anno.apps.DictionaryTokenizer $(input) $(output)

createDict:
	java -cp  uima-anno-1.0-standalone.jar org.apache.uima.annotator.dict_annot.dictionary.impl.DictionaryCreatorStanford -input $(output)/dico.csv -output $(output)/dict1.xml -encoding utf-8 -casesensitive true -accentnormalization true -stopwords 'de|le' -dictionarytype "fr.aphp.wind.uima.type.dict1" -snowballstemmer french

clean:
	find $(output) -type f  -delete

mesure_perf:
	python3 

package:
	rm -f lerner.zip && zip -rj lerner.zip target/uima-anno-1.0-standalone.jar  Makefile dict1.xml dict2.xml ref_doc_section.csv LernerDictionaryAnnotator.xml  ../uima-dict/README.rst 
