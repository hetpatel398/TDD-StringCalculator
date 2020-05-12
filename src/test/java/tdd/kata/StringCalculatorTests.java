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
	
	@Test
	public void add_threeNumberSimple_returnSum() {
		testAddMethod("1,2,3", 6);
	}
	
	@Test
	public void add_threeNumberWithNewLineDelimiter_returnSum() {
		testAddMethod("1\n2,3", 6);
	}
	
	@Test
	public void add_twoNumberWithCustomDelimiter_returnSum() {
		testAddMethod("//;\n1;2", 3);
	}
	
	@Test
	public void add_negativeNumber_raiseException() {
		StringCalculator stringCalculator = new StringCalculator();
		try {
			stringCalculator.add("-1,2");
		}
		catch(IllegalArgumentException e) {
			return;
		}
		assertTrue(false);
	}
	
	@Test
	public void add_numberGreaterThan1000_returnSumIgnoringNumberGreaterThan1000() {
		testAddMethod("1,1001", 1);
	}
	
	@Test
	public void add_threeNumbersWithMultiLengthDelimiter_returnSum() {
		testAddMethod("//[***]\n1***2***3", 6);
	}
	
	@Test
	public void add_threeNumbersWithMultipleDelimiters_returnSum() {
		testAddMethod("//[*][+]\n1*2+3", 6);
	}
}
