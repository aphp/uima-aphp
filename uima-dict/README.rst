ABOUT
+++++

This UIMA annotator is based on the official dictionary annotator. While it
keeps the original implementation advantages such speed and flexibility, this
implementation adds:
- stopwords removal
- accent removal
- snowball stemmer

Also, it comes with a dictionary generator that auto-configure an xml dictionary.

.. code-block:: bash

        java -cp  target/uima-dict-1.0-SNAPSHOT-standalone.jar org.apache.uima.annotator.dict_annot.dictionary.impl.DictionaryCreatorStanford -input dictionaryInput.csv -output dictionaryOutput.xml -encoding utf-8 -casesensitive true -accentnormalization true -stopwords 'de|le' -dictionarytype "fr.ae" -snowball french


HOw TO
++++++


For each dictionary file, configure according to:
- multiWordSeparator="caseSensitive='true';multiWord='true';multiWordSeparator=' ';accentNormalization='true';snowballStemmer='french';stopWords='de|le'"

:NOTE: this is a workaround since all the configuration mooves to
       "multiWordSeparator" which is a string and can contain an infinite
       number of configuration.

Configuration
++++++++++++++

- multiWord : true or false
- caseSensitive : true or false
- accentNormalization : true or false
- multiWordSeparator : true or false
- stopWords : pipe separated list
- snowballStemmer: english, french or empty

Be carefull
+++++++++++

the ; is the separator of the configuration file. Then it cannot be a stop word right now. 

the ' is the separator of content

The | is the separator for the stopwords.
