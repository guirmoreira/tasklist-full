package br.gov.pi.sefaz.tasklist.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/tasks").permitAll()
		.antMatchers(HttpMethod.GET, "/tasks/*").permitAll()
		.antMatchers(HttpMethod.DELETE, "/tasks/*").permitAll()
		.antMatchers(HttpMethod.POST, "/tasks").permitAll()
		.antMatchers(HttpMethod.PUT, "/tasks/*").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable();
	}
	
	// Configurações de recursos estáticos (requisições para Javascript, css, imagens...)
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	

}
