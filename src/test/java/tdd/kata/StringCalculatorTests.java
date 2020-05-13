package tdd.kata;

import static org.junit.Assert.*;

import java.io.PrintStream;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.http.WebSocket;

import org.junit.Test;
import org.mockito.Mockito;

public class StringCalculatorTests {

	private void testAddMethod(String input, int expectedReturnValue) {
		StringCalculator stringCalculator=new StringCalculator(Mockito.mock(Logger.class), Mockito.mock(WebSocket.class));
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
		StringCalculator stringCalculator = new StringCalculator(Mockito.mock(Logger.class), Mockito.mock(WebSocket.class));
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
	
	@Test
	public void add_fourNumbersWithMultipleMultiLengthDelimiters_returnSum() {
		testAddMethod("//[***][++][,]\n1***2,3++4", 10);
	}
	
	@Test
	public void add_testingLogger_shouldLogResult() {
		Logger mockLogger = Mockito.mock(Logger.class);
		StringCalculator stringCalculator=new StringCalculator(mockLogger, Mockito.mock(WebSocket.class));
		
		stringCalculator.add("1,2");
		
		Mockito.verify(mockLogger).log(Level.INFO, "Sum = 3");
	}
	
	@Test
	public void add_loggerThrowsExceptionEveryTime_callWebServiceAfterException() {
		Logger stubLogger = Mockito.mock(Logger.class);
		Mockito.doThrow(new RuntimeException("RuntimeException from logger")).when(stubLogger).log((Level) Mockito.any(), Mockito.anyString());
		
		WebSocket mockWebServiceInterface = Mockito.mock(WebSocket.class);
		StringCalculator stringCalculator=new StringCalculator(stubLogger, mockWebServiceInterface);
		
		stringCalculator.add("1");
		Mockito.mock(PrintStream.class);
		Mockito.verify(mockWebServiceInterface).sendText(Mockito.contains("RuntimeException"), Mockito.anyBoolean());
	}
}
