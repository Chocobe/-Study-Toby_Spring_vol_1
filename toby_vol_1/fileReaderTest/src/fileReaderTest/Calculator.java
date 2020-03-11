package fileReaderTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public int sum(String filepath) throws IOException {
		int sum = 0;
		String line = null;
		
		File file = new File(filepath);		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		while((line = br.readLine()) != null) {
			sum += Integer.valueOf(line);
		}
		
		br.close();
		
		return sum;
	}
}
