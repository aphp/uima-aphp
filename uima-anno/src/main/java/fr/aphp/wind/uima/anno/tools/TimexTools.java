package fr.aphp.wind.uima.anno.tools;

import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import de.unihd.dbs.uima.types.heideltime.Timex3;
public class  TimexTools {
	private static Logger logger = Logger.getRootLogger();
	
	public static Boolean isDeidTimex(Timex3 timex){
		Pattern dPattern = Pattern.compile("\\d+");
		if(        
				   timex.getTimexType().equals("DATE") 
				&& timex.getFoundByRule().indexOf("date_r9a_1") == -1
				&& timex.getFoundByRule().indexOf("date_r9d_1") == -1
				&& timex.getFoundByRule().indexOf("date_r6a_1") == -1
				&& timex.getFoundByRule().indexOf("date_r5b") == -1
				&& timex.getFoundByRule().indexOf("date_r5ac") == -1
				&& timex.getFoundByRule().indexOf("date_r20") == -1
				&& timex.getFoundByRule().indexOf("date_r21") == -1
				&& timex.getFoundByRule().indexOf("date_r22") == -1
				&& timex.getFoundByRule().indexOf("date_r16d") == -1
				&& timex.getFoundByRule().indexOf("date_r16c") == -1
				&& timex.getFoundByRule().indexOf("date_r16e") == -1
				&& timex.getFoundByRule().indexOf("date_r13a2") == -1
				&& timex.getFoundByRule().indexOf("date_r13a1") == -1
				&& timex.getFoundByRule().indexOf("date_r13c") == -1
				&& timex.getFoundByRule().indexOf("date_r13b") == -1
				&& timex.getFoundByRule().indexOf("date_r13d") == -1
				&& timex.getFoundByRule().indexOf("date_r10a") == -1
				&& timex.getFoundByRule().indexOf("date_r10b") == -1
				&& timex.getFoundByRule().indexOf("date_r10c") == -1
				&& timex.getFoundByRule().indexOf("date_r10d") == -1
				&& timex.getFoundByRule().indexOf("date_r11a") == -1
				&& timex.getFoundByRule().indexOf("date_r11b") == -1
				&& timex.getFoundByRule().indexOf("date_r23a") == -1
				&& timex.getFoundByRule().indexOf("date_r23b") == -1
				&& timex.getFoundByRule().indexOf("date_r12c") == -1
				&& timex.getFoundByRule().indexOf("date_r14a") == -1
				&& timex.getFoundByRule().indexOf("date_r14b") == -1
				&& timex.getFoundByRule().indexOf("date_r14c") == -1
				&& timex.getFoundByRule().indexOf("date_r14c") == -1
				&& timex.getFoundByRule().indexOf("date_r14d") == -1
				&& timex.getFoundByRule().indexOf("date_r15a") == -1
				&& timex.getFoundByRule().indexOf("date_r15b") == -1
				&& timex.getFoundByRule().indexOf("date_r17a") == -1
				&& timex.getFoundByRule().indexOf("date_r18") == -1
				&& timex.getFoundByRule().indexOf("date_r19") == -1
				&& timex.getFoundByRule().indexOf("date_r30a") == -1
				&& timex.getFoundByRule().indexOf("date_r30ab") == -1
				&& timex.getFoundByRule().indexOf("date_r30b") == -1
				&& timex.getFoundByRule().indexOf("date_r30d") == -1
				&& timex.getFoundByRule().indexOf("date_r30e") == -1
				&& timex.getFoundByRule().indexOf("date_r30f") == -1
				&& timex.getFoundByRule().indexOf("date_r31") == -1
				&& timex.getFoundByRule().indexOf("date_r30ab") == -1 
				&& timex.getFoundByRule().indexOf("date_r30g")  == -1 
				&& timex.getFoundByRule().indexOf("date_r30h")  == -1 
				&& timex.getFoundByRule().indexOf("date_r30i")  == -1 
				&& timex.getFoundByRule().indexOf("date_r30j")  == -1 
				&& timex.getFoundByRule().indexOf("date_r30a")  == -1 
				&& timex.getFoundByRule().indexOf("date_r30e")  == -1 
				&& timex.getFoundByRule().indexOf("date_r30f")  == -1
				&& timex.getFoundByRule().indexOf("date_r19c")  == -1 
//						date_r23b
				&& (timex.getFoundByRule().indexOf("date_historic_4c") >= 0 || //this one is relevant
						timex.getFoundByRule().indexOf("historic") == -1 )
				&& dPattern.matcher(timex.getCoveredText()).find()
				){
			
			return true;
		}
		logger.debug(String.format("Del Timex: >%s< (%s)", timex.getCoveredText(), timex.getFoundByRule()));
		return false;
	}

}
