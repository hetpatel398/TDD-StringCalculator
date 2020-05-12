package tdd.kata;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringCalculatorTests {

	private void testAddMethod(String input, int expectedReturnValue) {
		StringCalculator stringCalculator=new StringCalculator();
		int result = stringCalculator.add(input);
		assertEquals(expectedReturnValue, result);
	}
	@Test
	public void add_emptyString_returns0() {
		testAddMethod("", 0);
	}
	
	@Test
	public void add_singleNumber_returnThatNumber() {
		testAddMethod("1", 1);
	}
	
	@Test
	public void add_twoNumberSimple_returnSum() {
		testAddMethod("1,2", 3);
	}
}
