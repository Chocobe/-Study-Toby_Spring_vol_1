package tester;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/tester/applicationContext.xml")
public class ContextTester {
	@Autowired
	private ApplicationContext context;
	private static ApplicationContext prevContext;
	
	private static Set<ContextTester> testerSet = new HashSet<ContextTester>();
	
	
// context TEST
	@Test
	public void context_1() {
		assertThat(context, not(nullValue()));
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		prevContext = context;
	}
	
	@Test
	public void context_2() {
		assertThat(context, not(nullValue()));
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		prevContext = context;
	}
	
	@Test
	public void context_3() {
		assertThat(context, not(nullValue()));
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		prevContext = context;
	}
	
	
// testerClass TEST
	@Test
	public void tester_1() {
		assertThat(testerSet, not(hasItem(this)));
		testerSet.add(this);
	}
	
	@Test
	public void tester_2() {
		assertThat(testerSet, not(hasItem(this)));
		testerSet.add(this);
	}
	
	@Test
	public void tester_3() {
		assertThat(testerSet, not(hasItem(this)));
		testerSet.add(this);
	}
	
}