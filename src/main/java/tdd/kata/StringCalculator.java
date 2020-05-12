package tdd.kata;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.http.WebSocket;
import java.util.ArrayList;

public class StringCalculator {

	private Logger logger;
	private WebSocket webSocket;
	
	public StringCalculator(Logger logger, WebSocket webSocket) {
		this.logger=logger;
		this.webSocket=webSocket;
	}

	private String[] getSplit(String string) {
		String splitRegex=",|\n";
		
		if(string.startsWith("//")) {
			if(string.startsWith("//[")) {
				splitRegex = cleanRegex(string.substring(3, string.indexOf("]\n")).split("\\]\\["));
				string = string.substring(string.indexOf('\n')+1);
			}
			else {
				splitRegex=string.substring(2,3);
				string=string.substring(4);
			}
		}
		return string.split(splitRegex);
	}
	
	private String cleanRegex(String[] split) {
		String[] regexSpecialChars= {"*", "+", "|", ".", "$", "^", "?"};
		for(int i=0; i<split.length; i++) {
			for(String specialChar:regexSpecialChars)
				split[i]=split[i].replaceAll("\\"+specialChar, "\\\\"+specialChar);
		}
		return String.join("|", split);
	}

	public int add(String string) {
		if(string.length()==0)
			return 0;
		
		String[] nums=getSplit(string);
		
		int sum=findSum(nums);
		try {
			this.logger.log(Level.INFO, "Sum = "+sum);
		}
		catch (RuntimeException e) {
			webSocket.sendText("RuntimeException while logging", true);
		}
		
		return sum;
	}
	
	private int findSum(String[] nums) {
		
		ArrayList<String> negatives=new ArrayList<String>();
		boolean isNegative = false;
		
		int sum=0;
		for(String num:nums) {
			int num_i=Integer.parseInt(num);
			if(num_i<0) {
				isNegative=true;
				negatives.add(Integer.toString(num_i));
				continue;
			}
			else if(num_i>1000)
				continue;
			sum+=num_i;
		}
		
		if(isNegative)
			throw new IllegalArgumentException("Negatives not allowd : ["+String.join(",", negatives)+"]");
		return sum;
	}
}
