package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerUtil {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String resultPagePath) 
					throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher(resultPagePath);
		dispatcher.forward(request, response);
	}
}
