package co.nitin.todo.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import co.nitin.todo.constants.SecurityConstants;

public class JWTAuthorizationFilterConfig extends BasicAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilterConfig.class);
	
	public JWTAuthorizationFilterConfig(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

    	logger.info("[doFilterInternal] : Request : " + req.getHeaderNames());
		String header = req.getHeader(SecurityConstants.AUTH_HEADER_STRING);
    	logger.info("[doFilterInternal] : Header @@@@@@@@@@@@@@ : " + header);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			logger.info("[doFilterInternal] : empty header");
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);		
	}

	/**
	 * This method reads the JWT from the Authorization header, and then uses JWT to validate the token. 
	 * If everything is in place, we set the user in the SecurityContext and allow the request to move on.
	 * 
	 * @param request
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

    	logger.info("[getAuthentication] : returns UsernamePasswordAuthenticationToken");

		String token = request.getHeader(SecurityConstants.AUTH_HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes())).build()
							.verify(token.replace(SecurityConstants.TOKEN_PREFIX, "")).getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
			return null;
		}
		return null;
	}

}
