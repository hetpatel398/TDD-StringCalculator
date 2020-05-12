package tdd.kata;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringCalculatorTests {

	@Test
	public void add_emptyString_returns0() {
		StringCalculator stringCalculator=new StringCalculator();
		int result = stringCalculator.add("");
		assertEquals(0, result);
	}

}
