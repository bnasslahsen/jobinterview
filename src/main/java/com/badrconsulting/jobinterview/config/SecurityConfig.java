package com.badrconsulting.jobinterview.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.badrconsulting.jobinterview.controller.LoggingAccessDeniedHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/resources/**", "/js/**", "/error/**", "/css/**", "/img/**", "/webjars/**")
				.permitAll().antMatchers("/user/**").hasRole("USER").anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
				.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password")).roles("USER")
				.and().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}