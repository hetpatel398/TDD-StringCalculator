package tdd.kata;

import java.util.ArrayList;
import java.util.Arrays;

public class StringCalculator {

	public int add(String string) {
		if(string.length()==0)
			return 0;
		
		String splitRegex=",|\n"; //Default regex for split
		if(string.startsWith("//")) {
			if(string.startsWith("//[")) {
				String[] splitRegexes = string.substring(3, string.indexOf("]\n")).split("\\]\\[");
				splitRegex = String.join("|", Arrays.asList(splitRegexes));
				splitRegex = splitRegex.replaceAll("\\*", "\\\\*").replaceAll("\\+", "\\\\+");
				string = string.substring(string.indexOf('\n')+1);
			}
			else {
				splitRegex=string.substring(2,3);
				string=string.substring(4);
			}
		}
		
		int sum=findSum(string, splitRegex);
		
		return sum;
	}
	
	private int findSum(String string, String splitRegex) {
		String[] splits=string.split(splitRegex);
		
		ArrayList<String> negatives=new ArrayList<String>();
		boolean isNegative = false;
		
		int sum=0;
		for(String num:splits) {
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
