package com.sec.prac.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/hi")
public class SubResource {
	Logger logger = LoggerFactory.getLogger(SubResource.class);
	
	@GET
	@Path("/{diabolic}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sub(@PathParam("diablic") String user) {
		System.out.println("dd:@@@"+Response.serverError().entity("Entity not found for User: " + user).build());
		if(user == null || user.trim().length() == 0) {
			return Response.serverError().entity("Entity not found for User: " + user).build();
		}
		String json = "";
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
	}
	
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public Viewable getSource() {
//    	System.out.println("ddasdasdasd@@@@@@@");
//        Map<String, Object> model = new HashMap<String, Object>();
//        model.put("title", "Hello!");
//        model.put("message", "Hello, World!");
//        return new Viewable("/helloworld.jsp", model);
//    }
    
    @GET
    @Path("/ho")
    @Produces(MediaType.TEXT_PLAIN)
    public String helloWrold() {
    	System.out.println("ddasdasdasd@@@@@@@");
        return "Hello, World!";
    }
    
    @GET
    @Path("/error")
    @Produces(MediaType.APPLICATION_JSON)
    public Response errorCheck() throws IOException {
    	
    	ResponseBuilder rb = Response.status(1);
    	ErrorResponseFilter error = new ErrorResponseFilter();
		
    	logger.debug("error:  2@"+ error);
    	    	
    	return rb.build();
    }
    
}

