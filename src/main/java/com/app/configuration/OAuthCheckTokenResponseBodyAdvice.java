package com.app.configuration;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.CheckTokenEndpoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice(assignableTypes = {CheckTokenEndpoint.class})
public class OAuthCheckTokenResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	private static Logger logger = Logger.getLogger(OAuthCheckTokenResponseBodyAdvice.class);
	@Autowired
	private DefaultTokenServices tokenServices;
	
	@Override
	  public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
	    return true;
	  }

	  @Override
	  public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
	      Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
	      ServerHttpResponse response) {
	    if (body instanceof Exception) {
	      // If check_token fails, respons;e is an Exception instead of Map, so skip the method.
	      return body;
	    }
	    
	    HttpServletRequest servletRequest = ((ServletServerHttpRequest) request).getServletRequest();
	    String token = servletRequest.getParameter("token");
	    OAuth2AccessToken accesssToken = tokenServices.readAccessToken(token);
	    System.out.println("isExpired:"+accesssToken.isExpired());
	    System.out.println("isExpiresIn:"+accesssToken.getExpiresIn());
	    
	    
	    @SuppressWarnings("unchecked")
	    Map<String, Object> response1 = (Map<String, Object>) body;
	    logger.info("sebenarnya apa sih"+response1);

	    // modify response as you please
	    return response1;
	  }
}
