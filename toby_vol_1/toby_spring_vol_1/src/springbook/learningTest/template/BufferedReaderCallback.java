package springbook.learningTest.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
	abstract public Integer doSomethingReader(BufferedReader br) throws IOException;
}
