package com.sec.prac.config;



import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	@Qualifier("dataSource")
	@Lazy
	DataSource dataSource;
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user").password("1234").roles("USER")
//		.and()
//		.withUser("admin").password("1234").roles("ADMIN");
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").permitAll()
		.and()
		.authorizeRequests().antMatchers("/user**").hasRole("USER")
		.and()
		.authorizeRequests().antMatchers("/admin**").hasRole("ADMIN")
		.and()
		.authorizeRequests().antMatchers("/rest**").permitAll()
		.and()
		.authorizeRequests().antMatchers("/angular**").hasRole("USER")
		.and()
		.formLogin()
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	/**중요 체화 db에서 roll 판단 하는 메서드 
	 * 2019.01.01*/
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select name, passward from front_user_info where id = ?")
		.authoritiesByUsernameQuery("select role, name from front_user_info where id = ?");
	}
	
}
