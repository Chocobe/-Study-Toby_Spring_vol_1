package springbook.learningTest.template;

public interface LineCallback<T> {
	abstract public T doSomethingWithLine(String line, T value);
}
