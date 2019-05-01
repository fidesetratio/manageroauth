package com.app.configuration;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;

@Configuration
@EnableWebSecurity
@Order(101)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	
	 @Autowired 
	 private DataSource dataSource;
	 
	@Autowired
	private ConsumerTokenServices tokenService;
	
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.jdbcAuthentication().dataSource(dataSource)
		 * .usersByUsernameQuery("select username, password, " +
		 * "active as enabled from oauth_users where username=?")
		 * .authoritiesByUsernameQuery("select u.username, p.user_role  " +
		 * "from oauth_users u  " +
		 * "inner join oauth_permissions p on u.id = p.id_user " +
		 * "where u.username=?");
		 */
		
		auth.inMemoryAuthentication().withUser("patartimotius").password("evievi123").roles("USER");
		
	}
	
	
	
	
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// .TODO Auto-generated method stub
		http.csrf().disable()
		.formLogin().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
            .anonymous()
        .and()
            .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
           .and()
        .logout()
   	     .logoutUrl("/oauth/logout")
   	     .logoutSuccessHandler(new OauthCustomLogoutHandler(tokenService))
   	     .deleteCookies("JSESSIONID")
   	     .permitAll()
   	     .and()
         .authorizeRequests()
         .anyRequest().authenticated();
		
		
	}
	
	

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

}
