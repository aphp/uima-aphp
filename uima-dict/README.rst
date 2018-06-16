HOw TO
++++++


For each dictionary file, configure according to:
- multiWordSeparator="caseSensitive='true';multiWord='true';multiWordSeparator=' ';accentNormalization='true';stopWords='de|le'"

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

Be carefull
+++++++++++

the ; is the separator of the configuration file. Then it cannot be a stop word right now. 

the ' is the separator of content

The | is the separator for the stopwords.
