package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.MyBoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/conf/applicationContext.xml")
public class ContextTest {
	@Autowired
	public ApplicationContext context;
	public static ApplicationContext prevContext;
	
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
	
	
// dataSource
	@Test
	public void dataSource_1() {
		assertThat(dataSource, not(nullValue()));
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = dataSource;
	}
	
	@Test
	public void dataSource_2() {
		assertThat(dataSource, not(nullValue()));
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = dataSource;
	}
	
	@Test
	public void dataSource_3() {
		assertThat(dataSource, not(nullValue()));
		assertThat(prevDataSource, either(nullValue()).or(sameInstance(dataSource)));
		prevDataSource = dataSource;
	}
	
	
// dao
	@Test
	public void dao_1() {
		assertThat(dao, not(nullValue()));
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
		prevDao = dao;
	}
	
	@Test
	public void dao_2() {
		assertThat(dao, not(nullValue()));
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
		prevDao = dao;
	}
	
	@Test
	public void dao_3() {
		assertThat(dao, not(nullValue()));
		assertThat(prevDao, either(nullValue()).or(sameInstance(dao)));
		prevDao = dao;
	}
}
