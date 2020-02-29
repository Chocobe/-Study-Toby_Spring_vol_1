package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {
	private String encoding;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		String initEncoding= config.getInitParameter("encoding");
		
		if(initEncoding == null || initEncoding.length() < 1) {
			initEncoding = "URF-8";
		}
		
		this.encoding = initEncoding;
		config.getServletContext().setAttribute("encoding", this.encoding);		
	}
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
					throws IOException, ServletException {
		request.setCharacterEncoding(this.encoding);
		response.setContentType("text/html;charset=" + this.encoding);
		
		System.out.println("EncodingFilter - 설정 char : " + this.encoding);
		
		chain.doFilter(request, response);
	}
	
	
	@Override
	public void destroy() { }
}
