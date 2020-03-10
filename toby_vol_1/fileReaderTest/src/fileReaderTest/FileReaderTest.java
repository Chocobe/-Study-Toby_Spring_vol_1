package fileReaderTest;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;

import org.junit.Test;

public class FileReaderTest {
	@Test
	public void sumTest_1() throws IOException {
		Calculator calculator = new Calculator();
		String filepath = "D:\\study_programming\\java\\toby_spring\\toby_vol_1\\fileReaderTest\\numbers.txt";
		
		int sum = calculator.sum(filepath);
		
		assertThat(sum, is(10));
	}
}
