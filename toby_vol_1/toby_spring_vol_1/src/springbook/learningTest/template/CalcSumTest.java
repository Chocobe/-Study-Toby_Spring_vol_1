package springbook.learningTest.template;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class CalcSumTest {
	@Test
	public void sumOfNumbers() throws IOException {
		Calculator calculator = new Calculator();
		int sum = calculator.calcSum(this.getClass().getResource("numbers.txt").getPath());
		assertThat(sum, is(10));
	}
	
	
	@Test
	public void sumNum() throws IOException {
		Calculator calculator = new Calculator();
		File file = new File("D:\\study_programming\\java\\toby_spring\\toby_vol_1\\toby_spring_vol_1\\numbers.txt");
		
		int sum = calculator.calcSum(file);
		
		assertThat(sum, is(10));
	}
	
	
	@Test
	public void sumNum2() throws IOException {
		Calculator calculator = new Calculator();
		File file = new File("C:\\Users\\nice_\\OneDrive\\바탕 화면\\numbers.txt");
		
		int sum = calculator.calcSum(file);
		
		assertThat(sum, is(10));
	}
}
