package com.sec.prac.resource;

import java.io.IOException;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.NotSupportedException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

//import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class ErrorResponseFilter implements ClientResponseFilter{
	//ObjectMapper mapper = new ObjectMapper();
	Logger logger = LoggerFactory.getLogger(ErrorResponseFilter.class);
	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		// TODO Auto-generated method stub
		if(responseContext.getStatus() != Response.Status.OK.getStatusCode()) {
			if(responseContext.hasEntity()) {
				String message = null;
						//mapper.readValue(responseContext.getEntityStream(), String.class);
				logger.debug("ErrorResponseFilter 에서 :"+message);
				Response.Status status = Response.Status.fromStatusCode(responseContext.getStatus());
				WebApplicationException webApplicationException;
				
				switch(status) {
				case BAD_REQUEST:
					webApplicationException = new BadRequestException(message);
				 case UNAUTHORIZED:
					 webApplicationException = new NotAuthorizedException(message);
                     break;
                 case FORBIDDEN:
                	 webApplicationException = new ForbiddenException(message);
                     break;
                 case NOT_FOUND:
                	 webApplicationException = new NotFoundException(message);
                     break;
                 case METHOD_NOT_ALLOWED:
                	 webApplicationException = new NotAllowedException(message);
                     break;
                 case NOT_ACCEPTABLE:
                	 webApplicationException = new NotAcceptableException(message);
                     break;
                 case UNSUPPORTED_MEDIA_TYPE:
                	 webApplicationException = new NotSupportedException(message);
                     break;
                 case INTERNAL_SERVER_ERROR:
                	 webApplicationException = new InternalServerErrorException(message);
                     break;
                 case SERVICE_UNAVAILABLE:
                	 webApplicationException = new ServiceUnavailableException(message);
                     break;
                 default:
                	 webApplicationException = new WebApplicationException(message);
                }
			    throw webApplicationException;	
			}
		}
	}
	
	
}

