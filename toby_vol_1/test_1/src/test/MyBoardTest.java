package test;

import javax.sql.DataSource;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.sql.SQLException;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dao.MyBoardDao;
import vo.MyBoard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/myConf/applicationContext.xml")
public class MyBoardTest {
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MyBoardDao dao;
	
	private MyBoard board_1;
	private MyBoard board_2;
	private MyBoard board_3;
	
	
	@Before
	public void setUp() {
		this.board_1 = new MyBoard("토비", "스프링", "토비의 스프링");
		this.board_2 = new MyBoard("자바", "스크립트", "자바스크립트");
		this.board_3 = new MyBoard("이름", "제목", "내용");
	}
	
	
// dataSource TEST
	@Test
	public void dataSource() {
		assertThat(dataSource, not(nullValue()));
	}
	
	
// dao TEST
	@Test
	public void dao() {
		assertThat(dao, not(nullValue()));
	}
	
	
// insert & select TEST
	@Test
	public void insertAndSelect() throws SQLException {
		dao.deleteAll();
		assertThat(dao.count(), is(0));
		
		assertThat(dao.insert(board_1), is(1));
		
		MyBoard selectBoard = dao.select(board_1.getId(), board_1.getTitle(), board_1.getContent());
		assertThat(selectBoard.getId(), is(board_1.getId()));
		assertThat(selectBoard.getTitle(), is(board_1.getTitle()));
		assertThat(selectBoard.getContent(), is(board_1.getContent()));
	}
	
	
// deleteAll & count TEST
	@Test
	public void deleteAllAndCount() throws SQLException {
		dao.deleteAll();
		assertThat(dao.count(), is(0));
		
		assertThat(dao.insert(board_1), is(1));
		assertThat(dao.count(), is(1));
		
		assertThat(dao.insert(board_2), is(1));
		assertThat(dao.count(), is(2));
		
		assertThat(dao.insert(board_3), is(1));
		assertThat(dao.count(), is(3));
	}
}
