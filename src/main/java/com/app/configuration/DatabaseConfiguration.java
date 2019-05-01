package com.app.configuration;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import com.app.custom.store.CustomJdbcTokenStore;

@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Order(99)
public class DatabaseConfiguration {
	
	@Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource oauthDataSource() {
        System.out.println("database configuration");
		return DataSourceBuilder.create().build();
    }

	  @Bean(name="jdbctokenstore") 
	  public TokenStore tokenStore() { 
		  TokenStore  tokenStore = new  CustomJdbcTokenStore(oauthDataSource()); 
		 
		//  tokenStore = new  JdbcTokenStore(oauthDataSource());
		  return tokenStore; 
	  }
	
	
	  @Bean(name="memorytokenstore") 
	  public TokenStore inMemorytokenStore() { 
		  TokenStore  tokenStore = new  JdbcTokenStore(oauthDataSource()); 
		  return tokenStore; 
	  }
	

}
