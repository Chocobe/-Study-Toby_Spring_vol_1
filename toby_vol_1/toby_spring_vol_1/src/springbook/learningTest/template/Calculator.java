package springbook.learningTest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	// 템플릿
	public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filepath));
			T result = initVal;
			String line = null;
			
			while((line = br.readLine()) != null) {
				result = callback.doSomethingWithLine(line, result);
			}
			
			return result;
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
			throw e;
			
		} finally {
			if(br != null) {
				try { br.close(); }
				catch(IOException e) { System.out.println(e.getMessage()); }
			}
		}
	}
	
	
	// 더하기
	public Integer calcSum_2(String filepath) throws IOException {
		LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value + Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(filepath, sumCallback, 0);
	}
	
	
	// 곱하기
	public Integer calcMultiply_2(String filepath) throws IOException {
		LineCallback<Integer> multiplyCallback = new LineCallback<Integer>() {
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value * Integer.valueOf(line);
			}
		};
		
		return lineReadTemplate(filepath, multiplyCallback, 1);
	} 
	
	
	// 문자열 더하기
	public String concatenate(String filepath) throws IOException {
		LineCallback<String> concatenateCallback = new LineCallback<String>() {
			@Override
			public String doSomethingWithLine(String line, String value) {
				return value + line;
			}
		};
		
		return lineReadTemplate(filepath, concatenateCallback, "");
	}
	
	
	// 부족한 템플릿 작성시 예
	public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filepath));
			int ret = callback.doSomethingReader(br);
			
			return ret;
			
		} catch(IOException e) {
			System.out.println(e.getMessage());
			throw e;
			
		} finally {
			if(br != null) { 
				try { br.close(); } 
				catch(IOException e) { System.out.println(e.getMessage()); } 
			}
		}
	}
	
	
	public Integer calcSum(String filepath) throws IOException {
		BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
			@Override
			public Integer doSomethingReader(BufferedReader br) throws IOException {
				Integer sum = 0;
				String line = null;
				
				while((line = br.readLine()) != null) {
					sum += Integer.valueOf(line);
				}
				
				return sum;
			}
		};
		
		return fileReadTemplate(filepath, sumCallback);
	}
	
	
	public Integer calcMultiply(String filepath) throws IOException {
		BufferedReaderCallback multiplyCallback = new BufferedReaderCallback() {
			@Override
			public Integer doSomethingReader(BufferedReader br) throws IOException {
				Integer multiply = 1;
				String line = null;
				
				while((line = br.readLine()) != null) {
					multiply *= Integer.valueOf(line);
				}
				
				return multiply;
			}
		};
		
		
		return fileReadTemplate(filepath, multiplyCallback);
	}
}
