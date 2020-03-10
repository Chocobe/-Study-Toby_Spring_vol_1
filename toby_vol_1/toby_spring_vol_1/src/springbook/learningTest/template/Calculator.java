package springbook.learningTest.template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public Integer calcSum(String filepath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		
		Integer sum = 0;
		String line = null;
		
		while((line = br.readLine()) != null) {
			sum += Integer.valueOf(line);
		}
		
		br.close();
		
		return sum;
	}
	
	
	public Integer calcSum(File file) throws IOException {
		Integer sum = 0;
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		
		while((line = br.readLine()) != null) {
			sum += Integer.valueOf(line);
		}
		
		return sum;
	}
}
