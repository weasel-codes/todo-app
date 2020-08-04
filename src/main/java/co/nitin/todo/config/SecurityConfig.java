package co.nitin.todo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import co.nitin.todo.constants.APIConstants;
import co.nitin.todo.constants.SecurityConstants;
import co.nitin.todo.service.SecurityUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired private UserDetailsService userDetailsService;
	@Autowired private PasswordEncoder passwordEncoder;
	
	//two beans needed everyuwhere
	
	@Bean(name = "passwordEncoder")
    public PasswordEncoder bCryptPasswordEncoder() {
		return new Pbkdf2PasswordEncoder(	SecurityConstants.PBKDF2_HASH_SECRET, 
				SecurityConstants.PBKDF2_HASH_ITERATION, 
				SecurityConstants.PBKDF2_HASH_WIDTH);
    }

	@Bean(name = "userDetailsService")
	public UserDetailsService userDetailsService() {
		return new SecurityUserDetailsService();
	}

	
	//main two methods to start from here for authentication and authorization
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	logger.info("[configure] : HttpSecurity");
    	 http.cors().and().csrf().disable().authorizeRequests()
         .antMatchers(HttpMethod.POST, APIConstants.SIGN_UP_URL).permitAll()
         .anyRequest().authenticated()
         .and()
         .addFilter(new JWTAuthenticationFilterConfig(this.passwordEncoder, this.userDetailsService))
         .addFilter(new JWTAuthorizationFilterConfig(authenticationManager()));
//         // this disables session creation on Spring Security
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
