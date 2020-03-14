package springbook.proxy;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertThat;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

public class ProxyTest {
	@Test
	public void simpleProxy() {
		// 타겟 검사
		Hello hello = new HelloTarget();
		assertThat(hello.sayHello("Toby"), is("Hello Toby"));
		assertThat(hello.sayHi("Toby"), is("Hi Toby"));
		assertThat(hello.sayThankYou("Toby"), is("Thank you Toby"));
		
		// 프록시 검사
		Hello proxiedHello = (Hello)Proxy.newProxyInstance(getClass().getClassLoader(),
														   new Class[] {Hello.class}, 
														   new UppercaseHandler(new HelloTarget()));
		assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
		assertThat(proxiedHello.sayHi("Toby"), is("Hi Toby"));
		assertThat(proxiedHello.sayThankYou("Toby"), is("Thank YOU TOBY"));
	}
	
	
	static interface Hello {
		abstract public String sayHello(String name);
		abstract public String sayHi(String name);
		abstract public String sayThankYou(String name);
	}
	
	
	static class HelloTarget implements Hello {
		@Override
		public String sayHello(String name) {
			return "Hello " + name;
		}
		
		
		@Override
		public String sayHi(String name) {
			return "Hi " + name;
		}
		
		
		@Override
		public String sayThankYou(String name) {
			return "Thank you " + name;
		}
	}
	
	
	static class HelloUppercase implements Hello {
		private Hello hello;
		
		
		public HelloUppercase(Hello hello) {
			this.hello = hello;
		}
		
		
		@Override
		public String sayHello(String name) {
			return hello.sayHello(name).toUpperCase();
		}
		
		
		@Override
		public String sayHi(String name) {
			return hello.sayHi(name).toUpperCase();
		}
		
		
		@Override
		public String sayThankYou(String name) {
			return hello.sayThankYou(name).toUpperCase();
		}
	}
	
	
	static class UppercaseHandler implements InvocationHandler {
		private Object target;
		
		
		public UppercaseHandler(Object target) {
			this.target = target;
		}
		
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			Object ret = (String)method.invoke(target, args);
			
//			if(ret instanceof String) {
//				return ((String)ret).toUpperCase();
//				
//			} else {
//				return ret;
//			}
			
			if(ret instanceof String && method.getName().contains("Hello")) {
				return ((String)ret).toUpperCase();
				
			} else {
				return ret;
			}
		}
	}
}


