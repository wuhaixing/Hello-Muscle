package com.xinlv;

import javax.servlet.ServletContext;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xinlv.test.MockContextLoader;
import com.xinlv.test.PortalTestUtil;

/**
 * Simple test using the WicketTester
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = MockContextLoader.class,
        locations = {"/xinlv-test-context.xml" })
public class TestHomePage
{
	@Autowired
    private ServletContext servletContext;
	
	private WicketTester tester;
	
	@Before
    public void setUp() throws Exception {
        tester = PortalTestUtil.createWicketTester(servletContext);
    }
	
	@After
    public void tearDown() throws Exception {
        PortalTestUtil.destroy(tester);
    }
	
	@Test
    public void testRenderDefaultPage() {
		//start and render the test page
		tester.startPage(HomePage.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
		//assert rendered label component
		tester.assertLabel("message", "If you see this message wicket is properly configured and running");

    }
	
}
