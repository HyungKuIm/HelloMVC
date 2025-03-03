package ServletStudy;

import java.io.IOException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

	private BeanFactory beanFactory;
	
	public DispatcherServlet() {
		
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		ServletContext application = getServletContext();
		Object beanFactoryObj = application.getAttribute("beanFactory");
		if (beanFactoryObj != null) {
			beanFactory = (BeanFactory) beanFactoryObj;
		} else {
			throw new RuntimeException("IOC 팩토리 구성 실패");
		}
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		servletPath = servletPath.substring(1);
		int lastDotIndex = servletPath.lastIndexOf(".do");
		servletPath = servletPath.substring(0,  lastDotIndex);
		Object controllerBeanObj = beanFactory.getBean(servletPath);
		String operate = request.getParameter("operate");
	}
}





















