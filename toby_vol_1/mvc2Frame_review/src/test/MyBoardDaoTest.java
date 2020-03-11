package test;

import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.MyBoardDao;
import vo.MyBoard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/conf/applicationContext.xml")
public class MyBoardDaoTest {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MyBoardDao myBoardDao;
	
	private MyBoard myBoard_1;
	
	
	@Before
	public void setUp() {
		myBoard_1 = new MyBoard("Chocobe", "제목_1", "내용_1");
	}
	
	
// dataSource TEST
	@Test
	public void dataSource() {
		assertThat(dataSource, not(nullValue()));
	}
	
	@Test
	public void myBoardDao() {
		assertThat(myBoardDao, not(nullValue()));
	}
	
	
// insert & select TEST
	@Test
	public void myBoardDao_1() throws SQLException {
		myBoardDao.deleteAll();
		assertThat(myBoardDao.count(), is(0));
		
		assertThat(myBoardDao.insert(myBoard_1), is(1));
		MyBoard selectMyBoard = myBoardDao.selectLatest(myBoard_1.getWriterID(), myBoard_1.getTitle(), myBoard_1.getContent());

		assertThat(selectMyBoard.getWriterID(), is(myBoard_1.getWriterID()));
		assertThat(selectMyBoard.getTitle(), is(myBoard_1.getTitle()));
		assertThat(selectMyBoard.getContent(), is(myBoard_1.getContent()));
	}
	
	
// deleteAll & count TEST
	@Test
	public void deleteAllAndCount() throws SQLException {
		myBoardDao.deleteAll();
		assertThat(myBoardDao.count(), is(0));
		
		assertThat(myBoardDao.insert(myBoard_1), is(1));
		assertThat(myBoardDao.count(), is(1));
		
		assertThat(myBoardDao.insert(myBoard_1), is(1));
		assertThat(myBoardDao.count(), is(2));
		
		assertThat(myBoardDao.insert(myBoard_1), is(1));
		assertThat(myBoardDao.count(), is(3));
	}
}
