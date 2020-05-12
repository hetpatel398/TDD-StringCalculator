package tdd.kata;

public class StringCalculator {

	public int add(String string) {
		if(string.length()==0)
			return 0;
		return Integer.parseInt(string);
	}

}
