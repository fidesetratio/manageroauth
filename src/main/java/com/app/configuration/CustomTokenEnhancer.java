package com.app.configuration;

import org.jboss.logging.Logger;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {

	
	private static Logger logger = Logger.getLogger(CustomTokenEnhancer.class);
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		// TODO Auto-generated method stub
		logger.info("token is executed :"+accessToken.getExpiresIn());
		return accessToken;
	}

}
