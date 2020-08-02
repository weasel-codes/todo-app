package co.nitin.todo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import co.nitin.todo.constants.SecurityConstants;
import co.nitin.todo.service.SecurityUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired private UserDetailsService userDetailsService;
	@Autowired private PasswordEncoder passwordencoder;
	
	//main two methods to start from here
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    	logger.info("[configure] : AuthenticationManagerBuilder");
        auth.authenticationProvider(authenticationProvider());
    }
		
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.info("[configure] : HttpSecurity");
        http.authorizeRequests()
	        .antMatchers("/login","/signup").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
	        .antMatchers("/task/**","/task-list/**").hasRole("USER")	        
	        .antMatchers("/**").hasAnyRole("ADMIN", "USER")
	        .and().csrf().disable();
    }
    
	@Bean(name = "authenticationProvider")
	public AuthenticationProvider authenticationProvider() {

    	logger.info("[authenticationProvider] : returns AuthenticationProvider");
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(this.userDetailsService);
		authProvider.setPasswordEncoder(this.passwordencoder);
		return authProvider;
	}
	
    //Methods returning bean required
	@Bean(name = "userDetailsService")
	public UserDetailsService userDetailsService() {
		return new SecurityUserDetailsService();
	}
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new Pbkdf2PasswordEncoder(	SecurityConstants.PBKDF2_HASH_SECRET, 
											SecurityConstants.PBKDF2_HASH_ITERATION, 
											SecurityConstants.PBKDF2_HASH_WIDTH);
	}
}
