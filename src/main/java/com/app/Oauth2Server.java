package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


//curl -X POST -vu patartimotius:evievi123 http://localhost:8787/oauth/token -H "Accept: application/json" -d "password=evievi123&username=patartimotius&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp" 
/**
 * 
 * @author patar timotius
 * 1. Generate Token
 *    curl -X POST -vu clientapp:123456 http://localhost:8787/oauth/token -H "Accept: application/json" -d "password=evievi123&username=patartimotius&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
 * 2. Refresh Token   
 *    curl -X POST -vu clientapp:123456 http://localhost:8787/oauth/token -H "Accept: application/json" -d "grant_type=refresh_token&client_id=clientapp&refresh_token=533591a3-e08a-40df-84dc-108a66ba3848"
 * 3. Check token
 * 	  curl -X POST http://localhost:8787/oauth/check_token -d "token=0f3f7b13-60a1-4d9a-bef0-6cfce36aee1c"
 *
 * Basic Authorization
 * 1. Generate Token
 * 	  curl -X POST http://localhost:8787/oauth/token \
 *    -H "Accept: application/json" \ 
 *    -H  Authorization:Basic Y2xpZW50YXBwOjEyMzQ1Ng==" \
 *    -d "password=evievi123&username=patartimotius&grant_type=password&scope=read%20write&client_secret=123456&client_id=clientapp"
 * 	
 * 
 * 2. Refresh Token
 *    basic : base64(clientapp:123456)
 *  curl -X POST http://localhost:8787/oauth/token \
    --header "Authorization:Basic Y2xpZW50YXBwOjEyMzQ1Ng==" \
    -d "grant_type=refresh_token" \
    -d "refresh_token=533591a3-e08a-40df-84dc-108a66ba3848"
 *
 *
 */


@SpringBootApplication
public class Oauth2Server {
	
	public static void main(String args[]) {
		SpringApplication.run(Oauth2Server.class, args);
	}

}
