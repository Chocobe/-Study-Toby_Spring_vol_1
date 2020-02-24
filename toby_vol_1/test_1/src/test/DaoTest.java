package test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.BookDao;
import vo.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/bookConfig/applicationConfig.xml")
public class DaoTest {
	@Autowired
	private BookDao dao;
	
	@Autowired
	private DataSource dataSource;
	
	private Book book_1;
	private Book book_2;
	private Book book_3;
	
	
	@Before
	public void setUp() {
		book_1 = new Book("토비의 스프링", "이일민", 50000);
		book_2 = new Book("오브젝트", "조영호", 40000);
		book_3 = new Book("자바스크립트", "이선 브라운", 30000);
	}
	
	
	@Test
	public void dao() {
		assertThat(dao, not(nullValue()));
	}
	
	
	@Test
	public void insertAndSelect() throws SQLException {
		dao.deleteAll();
		assertThat(dao.count(), is(0));
		
		dao.insertBook(book_1);
		
		Book selectBook = dao.selectBook(book_1.getTitle());
		assertThat(selectBook.getTitle(), is(book_1.getTitle()));
		assertThat(selectBook.getAuth(), is(book_1.getAuth()));
		assertThat(selectBook.getPrice(), is(book_1.getPrice()));
	}
	
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void selectFailure() throws SQLException {
		dao.selectBook("unkown_title");
	}
	
	
	@Test
	public void deleteAllAndCount() throws SQLException {
		dao.deleteAll();
		assertThat(dao.count(), is(0));
		
		dao.insertBook(book_1);
		assertThat(dao.count(), is(1));
		
		dao.insertBook(book_2);
		assertThat(dao.count(), is(2));
		
		dao.insertBook(book_3);
		assertThat(dao.count(), is(3));
	}
}
