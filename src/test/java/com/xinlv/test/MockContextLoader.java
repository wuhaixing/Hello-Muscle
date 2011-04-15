/*
 * Copyright 2009-2011 Carsten Hufe devproof.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xinlv.test;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextLoader;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;



/**
 * @author Carsten Hufe
 */
public class MockContextLoader implements ContextLoader {

    @Override
    public String[] processLocations(Class<?> clazz, String... locations) {
        return locations;
    }

    @Override
    public ConfigurableApplicationContext loadContext(String... locations) throws Exception {

        ConfigurableWebApplicationContext context = new XmlWebApplicationContext();
        MockServletContext servletContext = new MockServletContext("") {
            @Override
            public String getRealPath(String arg0) {
                return System.getProperty("java.io.tmpdir");
            }
        };
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);
        context.setServletContext(servletContext);
        String configLocation = "/xinlv-test-context.xml";
        context.setConfigLocation(configLocation);
        /*String[] configLocations = PortalContextLoaderListener.locateConfigLocations(locations);
        context.setConfigLocations(configLocations);*/
        context.refresh();
        context.registerShutdownHook();
        return context;
    }


}
