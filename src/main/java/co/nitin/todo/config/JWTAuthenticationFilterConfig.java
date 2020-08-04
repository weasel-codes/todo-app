package co.nitin.todo.config;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.nitin.todo.constants.SecurityConstants;
import co.nitin.todo.model.entity.User;
import co.nitin.todo.service.SecurityUserDetailsService;

/**
 * By extending the filter provided within the security framework, Spring can
 * automatically identify the best place to put it in the security chain.
 * 
 * <br>
 * 
 * Our custom authentication filter overwrites two methods of the base class:
 * <ul>
 * attemptAuthentication: where we parse the user's credentials and issue them
 * to the AuthenticationManager.
 * </ul>
 * <ul>
 * successfulAuthentication: which is the method called when a user successfully
 * logs in. We use this method to generate a JWT for this user.
 * </ul>
 * 
 * <br>
 * JWTAuthenticationFilter class extends UsernamePasswordAuthenticationFilter. 
 * This filter, which is provided by Spring Security, registers itself as the responsible for /login endpoint. 
 * As such, whenever your backend API gets a request to /login, your specialization of this filter 
 * (i.e., JWTAuthenticationFilter) goes into action and handles the authentication attempt 
 * (through the attemptAuthentication method).
 * 
 * @author weasel
 *
 */
public class JWTAuthenticationFilterConfig extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilterConfig.class);

	private PasswordEncoder passwordEncoder;
	private UserDetailsService userDetailsService;

	public JWTAuthenticationFilterConfig(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * JWTAuthenticationFilter class extends UsernamePasswordAuthenticationFilter. 
	 * This filter, which is provided by Spring Security, registers itself as the responsible for /login endpoint. 
	 * As such, whenever your backend API gets a request to /login, your specialization of this filter 
	 * (i.e., JWTAuthenticationFilter) goes into action and handles the authentication attempt 
	 * (through the attemptAuthentication method).
	 * 
	 * <br>
	 * <br>
	 * 
	 * Make {@link SecurityUserDetailsService} return decrypted password for comparison or encrypt here before sending 
	 * for authentication
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){

    	logger.info("[attemptAuthentication] : returns UsernamePasswordAuthenticationToken in Authentication");
    	User user = null;
		try {
			user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			if(user==null)
				throw new IOException();
			else 
				logger.info("[attemptAuthentication] : User : " + user);
		} catch (IOException e) {
			logger.error("Error Serializing request");
			e.printStackTrace();
		}
		
		UserDetails authUser = this.userDetailsService.loadUserByUsername(user.getUsername());
		logger.info("[attemptAuthentication] : User fetched from db..." + authUser);
		
		System.out.println("verified : " + passwordEncoder.matches(user.getPassword(), authUser.getPassword()));
		
		if(passwordEncoder.matches(user.getPassword(), authUser.getPassword())) {
			logger.info("[attemptAuthentication] : User authenticated...");
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			return token;
		} else {
			throw new RuntimeException("Password verification failed");
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, 
											FilterChain chain, Authentication auth) throws IOException, ServletException {

    	logger.info("[successfulAuthentication] : Generates token JWT for : " + auth.getPrincipal());
		String token = JWT.create()
				.withSubject((String)auth.getPrincipal()) //"sub" claim
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)) //"exp" claim
				.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
		
    	logger.info("[successfulAuthentication] : Generated token JWT for header " 
				+ SecurityConstants.AUTH_HEADER_STRING +  " : " + token);

		res.addHeader(SecurityConstants.AUTH_HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	}
}
