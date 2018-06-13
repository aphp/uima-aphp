run:
	java -cp  target/uima-anno-1.0-standalone.jar fr.aphp.wind.uima.anno.apps.LernerApp $(input) $(output)
clean:
	find $(output) -type f  -delete

mesure_perf:
	python3 

package:
	rm -f lerner.zip && zip -r lerner.zip target/uima-anno-1.0-standalone.jar  Makefile dict1.xml dict2.xml LernerDictionaryAnnotator.xml README.rst
