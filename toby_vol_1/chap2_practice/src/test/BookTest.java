package test;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.BookDao;
import vo.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/myConf/applicationContext.xml")
public class BookTest {
	@Autowired
	private BookDao dao;
	
	private Book book_1;
	private Book book_2;
	private Book book_3;
	
	
	@Before
	public void setUpt() {
		this.book_1 = new Book("토비의 스프", "링이일민", 50000);
		this.book_2 = new Book("자바", "의정석", 40000);
		this.book_3 = new Book("자바스", "크립트", 30000);
	}
	
	
// insertBook & selectBook TEST
	@Test
	public void insertAndSelect() throws SQLException {
		dao.deleteAll();
		assertThat(dao.count(), is(0));
		
		assertThat(dao.insertBook(book_1), is(1));
		
		Book select = dao.selectBook(book_1.getTitle());
		assertThat(select.getTitle(), is(book_1.getTitle()));
		assertThat(select.getAuth(), is(book_1.getAuth()));
		assertThat(select.getPrice(), is(book_1.getPrice()));
	}
	
	
// deleteAll & count TEST
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
