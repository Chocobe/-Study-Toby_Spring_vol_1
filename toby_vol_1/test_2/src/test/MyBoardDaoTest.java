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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.MyBoardDao;
import vo.MyBoard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class MyBoardDaoTest {
	@Autowired
	private MyBoardDao dao;
	
	@Autowired
	private DataSource dataSource;
	
	private MyBoard myBoard_1;
	private MyBoard myBoard_2;
	private MyBoard myBoard_3;
	
	
	@Before
	public void setUp() {
		this.myBoard_1 = new MyBoard("chocobe", "안녕하세요", "첫번째 테스트 글 내용 입니다");
		this.myBoard_2 = new MyBoard("Lucid", "글 제목2", "두번째 테스트 글 내용 입니다.");
		this.myBoard_3 = new MyBoard("Moon", "글 제목~~~ 3", "세번째 테스트 글 내용 입니다.");
	}
	
	
	@Test
	public void dao() {
		assertThat(dao, not(nullValue()));
	}
	
	@Test
	public void dataSource() {
		assertThat(dataSource, not(nullValue()));
	}
	
	
// insert & select TEST
	@Test
	public void insertAndSelect() throws SQLException {
		dao.deleteAll();
		assertThat(dao.count(), is(0));
		
		assertThat(dao.insert(myBoard_1), is(1));
		
		MyBoard selectMyBoard = dao.select(myBoard_1);
		assertThat(selectMyBoard.getId(), is(myBoard_1.getId()));
		assertThat(selectMyBoard.getTitle(), is(myBoard_1.getTitle()));
		assertThat(selectMyBoard.getContent(), is(myBoard_1.getContent()));
	}
	
	
// deleteAll & count TEST
	@Test
	public void deleteAllAndCount() throws SQLException {
		dao.deleteAll();
		assertThat(dao.count(), is(0));
		
		assertThat(dao.insert(myBoard_1), is(1));
		assertThat(dao.count(), is(1));
		
		assertThat(dao.insert(myBoard_2), is(1));
		assertThat(dao.count(), is(2));
		
		assertThat(dao.insert(myBoard_3), is(1));
		assertThat(dao.count(), is(3));
	}
}
