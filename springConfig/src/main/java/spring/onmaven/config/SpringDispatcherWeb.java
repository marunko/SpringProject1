package spring.onmaven.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringDispatcherWeb extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {SpringConfigContext.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};
	}
	
	@Override 
	public void onStartup(ServletContext servletcontext) throws ServletException{
		super.onStartup(servletcontext);
		hiddenFilter(servletcontext);
	}
	
	private  void hiddenFilter(ServletContext servletcontext) {
		servletcontext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).
		addMappingForUrlPatterns(null, isAsyncSupported(), "/*");;
	}
 
}
