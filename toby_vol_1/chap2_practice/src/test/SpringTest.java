package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.BookDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/myConf/applicationContext.xml")
public class SpringTest {
	private static Set<SpringTest> testerSet = new HashSet<SpringTest>();
	
	@Autowired
	private ApplicationContext context;
	private static ApplicationContext prevContext;
	
	@Autowired
	private DataSource dataSource;
	private static DataSource prevDataSource;
	
	@Autowired
	private BookDao dao;
	private static BookDao prevDao;
	
	
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
	
	
// context TEST
	@Test
	public void context_1() {
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		prevContext = context;
	}
	
	@Test
	public void context_2() {
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		prevContext = context;
	}
	
	@Test
	public void context_3() {
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		prevContext = context;
	}
	
	
// dataSource TEST
	@Test
	public void dataSource_1() {
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = dataSource;
	}
	
	@Test
	public void dataSource_2() {
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = dataSource;
	}
	
	@Test
	public void dataSource_3() {
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = dataSource;
	}
	
	
// dao TEST
	@Test
	public void dao_1() {
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
		prevDao = dao;
	}
	
	@Test
	public void dao_2() {
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
		prevDao = dao;
	}
	
	@Test
	public void dao_3() {
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
		prevDao = dao;
	}
}
