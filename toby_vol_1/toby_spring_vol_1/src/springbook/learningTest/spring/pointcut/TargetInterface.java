package springbook.learningTest.spring.pointcut;

public interface TargetInterface {
	abstract public void hello();
	abstract public void hello(String a);
	abstract public int minus(int a, int b) throws RuntimeException;
	abstract public int plus(int a, int b);
}
