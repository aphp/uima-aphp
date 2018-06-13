- apache ruta needs a very good pom, in particular, resources folders
- the types shall be into resources
- it seems not compatible with uima 3.0.0 yet
- in order to test, each script shall have a input/output folder
- the input should contain the xmi (or the test data)
- the output should contain the resulting xmi (or the gold standard)
This tool is a pipeline for note deidentication. It is intended to have a high
recall, by passing large dicionnaries, wide regular expressions.  It produces
BRAT annotated results.

![Process](https://github.com/parisni/UimaNoteDeid/blob/master/deid-process.png)

- opennlp model should be placed in `model/opennlp`
- texts should be placed into `data`  as `.txt` suffixed files
- metadata should be placed together with texts, same name but suffixed `.json`

input
=====

The pipelines takes informations from json files. All the json need to be
stored in one file; one json per line.The json needs to be valid:

```json
{
"Demographics": {//this is not mendatory
	"firstName": <String>,
	"lastName": <String>
},
"Text": <String> // Required; valid json string, if multiline needs to be escaped
}
```


metadata
========

They come from the database within the json. The type system is mapped to the json structure.

- Demographics 
	- firstName
	- lastName
	- TODO
If possible, the names are taken from [cTakes](http://ctakes.apache.org/apidocs/3.1.1/ctakes-side-effect/org/apache/ctakes/typesystem/type/structured/Demographics.html). The annotation will also be interoperable with cTakes.

Use
===

```
mvn clean install
java -jar target/NoteDeid-1.0-SNAPSHOT-standalone.jar
```

To be done
=========

- implement structured data detection (in progress)
- implement time tracker (heideltime)


Distribute over spark [TO BE IMPROVED]
======================================

See: [UimaOnSpark](https://github.com/parisni/UimaOnSpark)

The use of the pojo & transcient variable comes from [https://spark-summit.org/2014/wp-content/uploads/2014/07/Leveraging-UIMA-in-Spark-Philip-Ogren.pdf](Philip Ogren)


Notes
=====

- `resources/dictionary.xsd` is necessary in order the dictionary. xmlbeans copy them into the target folder..


UimaSectionExtractor
====================

- The SectionSegmenterPogo is instanciated together with a 3 column table and regex for sections. 
- Each text has a type that subsets the regex patterns.
- the analyseText methods takes a text and its metadata as argument. 
- it produces a csv as a string, for each text

NOTEDEID
=========

Input:
------
- specify an input folder
- put several couple of files 1001.json / 1001.txt  ; etc...

Output:
------
- specify an output folder
- get several brat annotated files 1001.ann ; etc....

Features:
---------
- Dates
- damereau-levenstein ponderated
- regex (phone number, street, numbers):w
- capture regex (sa soeur, son frêre...)

Notice:
-------
- dicoBuild.R generates the dictionnaries from raw csv
- their is a hack to use the dictionnary with both accent & case normalisation
- the noteHeader & noteFooter in the regexCapture file should be removed since this is done before while extracting the data.
- the makefile contains receipe for deploying the tool. 
- the notedeid.sh file allows to run it then
