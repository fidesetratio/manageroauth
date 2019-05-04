package com.app.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;


@Configuration
@EnableAuthorizationServer
@Order(100)
public class AuthorizationConfiguration extends AuthorizationServerConfigurerAdapter{
	private static final String RESOURCE_ID = "restservice";

	@Autowired
	@Qualifier("jdbctokenstore")
	private TokenStore tokenStore;
	
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	 @Autowired 
	 private DataSource dataSource;
	 
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	
	
	@Override 
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception { 
	      oauthServer.checkTokenAccess("permitAll()");
	   
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
	 clients.jdbc(dataSource);
		
	}

	
	
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	
		endpoints.authenticationManager(this.authenticationManager).tokenStore(tokenStore);
		/*
		.tokenEnhancer(new CustomTokenEnhancer())
		.userDetailsService(userDetailsService);
		endpoints.pathMapping("/oauth/check_token", "/oauth/check_token");
*/	}
	
	
	
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setTokenStore(tokenStore);
		return tokenServices;
	}

	
	 
}
