package springbook.learningTest.template;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CalcSumTest {
	private Calculator calculator;
	private String numFilepath;
	
	
	@Before public void setUp() {
		this.calculator = new Calculator();
		numFilepath = this.getClass().getResource("numbers.txt").getPath();
	}
	
	
	@Test public void sumOfNumbers() throws IOException {
		assertThat(calculator.calcSum(numFilepath), is(10));
	}
	
	
	@Test public void multiplyOfNumbers() throws IOException {
		assertThat(calculator.calcMultiply(numFilepath), is(24));
	}
	
	
	@Test public void concatenateStrings() throws IOException {
		assertThat(calculator.concatenate(numFilepath), is("1234"));
	}
}
