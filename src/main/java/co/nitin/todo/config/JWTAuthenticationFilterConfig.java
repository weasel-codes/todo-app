package co.nitin.todo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.nitin.todo.constants.SecurityConstants;
import co.nitin.todo.model.entity.User;

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
 * @author weasel
 *
 */
public class JWTAuthenticationFilterConfig extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilterConfig.class);

	private AuthenticationManager authenticationManager;

	@Autowired
	public JWTAuthenticationFilterConfig(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(),
																								user.getPassword(), 
																								new ArrayList<>());
			return authenticationManager.authenticate(token);
			
		} catch (AuthenticationException | IOException e) {
			logger.error("[attemptAuthentication] : " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, 
											FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		String token = JWT.create()
				.withSubject(((User) auth.getPrincipal()).getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
	}

}
