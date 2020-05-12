package tdd.kata;

public class StringCalculator {

	public int add(String string) {
		if(string.length()==0)
			return 0;
		
		int sum=findSum(string);
		
		return sum;
	}
	
	private int findSum(String string) {
		String[] splits=string.split(",|\n");
		
		int sum=0;
		for(String num:splits) {
			int num_i=Integer.parseInt(num);
			sum+=num_i;
		}
		
		return sum;
	}
}
