package com.se.security.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {	

	@Autowired
	private DataSource securityDataSource;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			
		http.cors();
		
		http.csrf().disable();
		
		
		
		

		
		http
		.authorizeRequests()
		.antMatchers("accountmanagement*").hasAuthority("ADMIN")
		.antMatchers("/css/**").permitAll()
        .antMatchers("/bootstrap-4.0.0-dist/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/framework/**").permitAll()
        .antMatchers("/img/**").permitAll()
        .antMatchers("/upload/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/item/").permitAll()
		.antMatchers("/timSanPham").permitAll()
		.antMatchers("/registration").permitAll()
		.antMatchers("/forgotpassword").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/", true)
		.failureUrl("/login?fails")
		.permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/403")
		.and().logout().logoutSuccessUrl("/").permitAll();
		

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**");
	}
	
	protected void configure( AuthenticationManagerBuilder auth) throws Exception {
//	    auth.inMemoryAuthentication()
//	        .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
//	        .and()
//	        .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//	        .and()
//	        .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
		auth.jdbcAuthentication().dataSource(securityDataSource)
		.usersByUsernameQuery("select ten_tai_khoan, mat_khau, da_xoa from Tai_Khoan where ten_tai_khoan=?")
		.authoritiesByUsernameQuery("select ten_tai_khoan, role from Tai_Khoan where ten_tai_khoan=?");
	}

}
