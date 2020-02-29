package listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import controller.SubController;
import controller.index.IndexViewController;
import controller.myBoard.MyBoardViewController;

public class ContextListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent event) {
		Map<String, SubController> subControllers = new HashMap<String, SubController>();
		
	// index.jsp 페이지 요청 컨트롤러
		subControllers.put("/index.do", new IndexViewController());

		
	// myBoard
		// myBoardView 페이지 요청 컨트롤러
		subControllers.put("/myBoardView.do", new MyBoardViewController());
		
		
		event.getServletContext().setAttribute("subControllers", subControllers);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) { }
}
