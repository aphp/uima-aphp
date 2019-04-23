package fr.aphp.wind.uima.anno.apps;

import fr.aphp.wind.uima.anno.pojo.LernerPojo;

/**
 * @author nps
 *
 */
public class LernerApp {
    public static void main(String[] args) throws Exception {

	String inputPath = args[0];
	String outputPath = args[1];
	Boolean multiCore = "true".equals(args[2]) ? true : false;
	String lang = args[3];
	String sectionFilePath = null;
	Boolean exportTokens = false;
	Boolean exportSentence =  false;
	Boolean exportSection = false;
	System.out.println(args.length);
	if (args.length == 6) {
	    sectionFilePath = args[5];
	}
	if(args.length > 4) {
	 exportTokens = (args[4].contains("token")) ? true : false;
	 exportSentence = (args[4].contains("sentence")) ? true : false;
	 exportSection =(args[4].contains("section")) ? true : false;
	}
	LernerPojo lp = new LernerPojo();
	lp.run(inputPath, outputPath, multiCore, exportTokens, exportSentence, exportSection,
		sectionFilePath, lang);
    }
}
