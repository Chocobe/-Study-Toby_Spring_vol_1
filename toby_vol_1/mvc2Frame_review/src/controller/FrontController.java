package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, SubController> subControllers;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		@SuppressWarnings("unchecked")
		Map<String, SubController> initControllers = (Map<String, SubController>)config.getServletContext().getAttribute("subControllers");
		
		this.subControllers = initControllers;
	}
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		
		SubController subController = this.subControllers.get(servletPath);
		
		System.out.println("요청 servletPath : " + servletPath);
		
		if(subController == null) {
			subController = this.subControllers.get("/index.do");
		}
		
		subController.execute(request, response);
	}
}
