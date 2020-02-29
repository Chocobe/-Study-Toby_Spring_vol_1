package controller.index;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControllerUtil;
import controller.SubController;

public class IndexViewController implements SubController {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resultPagePath = "/index.jsp";
		
		ControllerUtil.forward(request, response, resultPagePath);
	}
}
