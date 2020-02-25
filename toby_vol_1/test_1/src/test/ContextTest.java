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

import dao.MyBoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/myConf/applicationContext.xml")
public class ContextTest {
	@Autowired
	private ApplicationContext context;
	private static ApplicationContext prevContext;
	
	private Set<ContextTest> tester = new HashSet<ContextTest>();
	
	@Autowired
	private DataSource dataSource;
	private static DataSource prevDataSource;
	
	@Autowired
	private MyBoardDao dao;
	private static MyBoardDao prevDao;
	
	
// context TEST
	@Test
	public void context_1() {
		assertThat(context, not(nullValue()));
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		prevContext = this.context;
	}
	
	@Test
	public void context_2() {
		assertThat(context, not(nullValue()));
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		prevContext = this.context;
	}
	
	@Test
	public void context_3() {
		assertThat(context, not(nullValue()));
		assertThat(prevContext, either(nullValue()).or(sameInstance(context)));
		this.prevContext = this.context;
	}
	
	
// tester class TEST
	@Test
	public void tester_1() {
		assertThat(tester, not(hasItem(this)));
		tester.add(this);
	}
	
	@Test
	public void tester_2() {
		assertThat(tester, not(hasItem(this)));
		tester.add(this);
	}
	
	@Test
	public void tester_3() {
		assertThat(tester, not(hasItem(this)));
		tester.add(this);
	}
	
	
// dataSource TEST
	@Test
	public void dataSource_1() {
		assertThat(dataSource, not(nullValue()));
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = this.dataSource;
	}
	
	@Test
	public void dataSource_2() {
		assertThat(dataSource, not(nullValue()));
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = this.dataSource;
	}
	
	@Test
	public void dataSource_3() {
		assertThat(dataSource, not(nullValue()));
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = this.dataSource;
	}
	
	
// myBoard TEST
	@Test
	public void myBoard_1() {
		assertThat(dao, not(nullValue()));
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
	}
	
	@Test
	public void myBoard_2() {
		assertThat(dao, not(nullValue()));
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
	}
	
	@Test
	public void myBoard_3() {
		assertThat(dao, not(nullValue()));
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
	}
}
