package com.hlx.juinfo.task;

import java.io.File;

import com.alibaba.citrus.service.resource.support.ResourceLoadingSupport;
import com.alibaba.citrus.springext.support.context.AbstractXmlApplicationContext;
import com.alibaba.citrus.springext.support.context.XmlApplicationContext;
import com.alibaba.common.logging.spi.log4j.DOMConfigurator;
import com.alibaba.service.resource.BootstrapResourceLoaderService;
import com.alibaba.service.resource.ResourceNotFoundException;

public class WebxEnvMain {
	private static final String SERVICE_FILE = "classpath:/task/webx/webx.xml";

    private static final String LOG_CONFIG = "classpath/task/log4j.xml";
    
    private AbstractXmlApplicationContext context;
	
	protected WebxEnvMain() {
	    File basedir = new File(getBaseDir());
	    System.setProperty("application.codeset","GBK");
        System.setProperty("database.codeset","ISO-8859-1");

        basedir.mkdirs();

        if (!basedir.isDirectory() || !basedir.exists()) {
            throw new IllegalArgumentException("Base directory does not exist: " + basedir);
        }

        // ´´½¨bootstrap resource loader service
        BootstrapResourceLoaderService resourceLoader = new BootstrapResourceLoaderService();
        resourceLoader.setApplicationRoot(basedir.getAbsolutePath());
        
        try {
            DOMConfigurator.configure(resourceLoader.getResourceAsURL(LOG_CONFIG));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException("Failed to load log4j configuration", e);
        }
        
        context = new XmlApplicationContext();
        context.setParent(null);
        context.setConfigLocation(SERVICE_FILE);
        context.setResourceLoadingExtender(new ResourceLoadingSupport(context));
        context.refresh();
	}
	
	private static String getBaseDir() {
        String basedir = System.getProperty("project.home");

        if (basedir == null) {
            System.setProperty("project.home", System.getProperty("user.dir", ""));
        }

        basedir = System.getProperty("project.home");
        if (!basedir.endsWith(File.separator)) {
            basedir += File.separator;
        }

        return basedir;
    }
	
	protected Object getBean(String beanName) {
    	return context.getBean(beanName);
    }
	
	public void exit(){
		context.getBeanFactory().freezeConfiguration();
		context.destroy();
    }
}
