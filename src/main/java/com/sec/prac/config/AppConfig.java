package com.sec.prac.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

@ApplicationPath("/rest")
public class AppConfig extends ResourceConfig {

    public AppConfig() {
    	System.out.println("ddasdasdasd@@@@@@@");
    	packages("com.sec.prac.resource");
    	//packages(this.getClass().getPackage().getName());
        register(JspMvcFeature.class);
    }
}
