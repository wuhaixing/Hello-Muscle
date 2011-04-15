package com.xinlv;

import org.apache.wicket.javascript.NoOpJavascriptCompressor;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Application object for your web application. If you want to run this application without deploying, run the Start class.
 * 
 * @see com.xinlv.Start#main(String[])
 */
public class PortalApplication extends WebApplication
{    
    /**
     * Constructor
     */
	public PortalApplication()
	{
	}
    @Override
    protected void init() {
        super.init();
        configureWicket();

    }
    
    public ApplicationContext getSpringContext() {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }
    
    private void configureWicket() {
        addComponentInstantiationListener(new SpringComponentInjector(this, getSpringContext(), true));
    }
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	public Class<HomePage> getHomePage()
	{
		return HomePage.class;
	}

}
