package tdd.kata;

public class StringCalculator {

	public int add(String string) {
		if(string.length()==0)
			return 0;
		
		String splitRegex=",|\n"; //Default regex for split
		if(string.startsWith("//")) {
			splitRegex=string.substring(2,3);
			string=string.substring(4);
		}
		
		int sum=findSum(string, splitRegex);
		
		return sum;
	}
	
	private int findSum(String string, String splitRegex) {
		String[] splits=string.split(splitRegex);
		
		int sum=0;
		for(String num:splits) {
			int num_i=Integer.parseInt(num);
			sum+=num_i;
		}
		
		return sum;
	}
}
