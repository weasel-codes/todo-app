package co.nitin.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.nitin.todo.constants.APIConstants;
import co.nitin.todo.constants.APIResponse;
import co.nitin.todo.model.req.BaseRequest;
import co.nitin.todo.model.req.UserSignup;
import co.nitin.todo.model.response.BaseResponse;
import co.nitin.todo.service.UserService;
import co.nitin.todo.utils.APIResponseBuilder;

/**
 * You might be wondering what class is dealing with the requests issued to the /login endpoint. 
 * The answer to this question is simple, the JWTAuthenticationFilter class that you created previously extends 
 * UsernamePasswordAuthenticationFilter. This filter, which is provided by Spring Security, registers itself as 
 * the responsible for this endpoint. As such, whenever your backend API gets a request to /login, your specialization 
 * of this filter (i.e., JWTAuthenticationFilter) goes into action and handles the authentication attempt (through the
 *  attemptAuthentication method).
 *  
 * @author weasel
 *
 */
@RestController
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserService userService;
	
	@Autowired	
	public UserController(co.nitin.todo.service.UserService userService) {
		super();
		this.userService = userService;
	}

	/**
	 * User Signs up > account is created > Need to login to use it
	 * @param req
	 * @return
	 */
	@PostMapping(path = APIConstants.SIGN_UP_URL)
	public BaseResponse<Boolean> userSignup(@RequestBody BaseRequest<UserSignup> req) {
		
		BaseResponse<Boolean> response = null;
		try {

			logger.info("[userSignup] : request received : " + req);
			this.userService.createUser(req);
			response = APIResponseBuilder.buildResponse(APIResponse.SUCCESS_MESSAGE, true);		
			logger.info("[userSignup] : Returning response : " + response);

		} catch (Exception e) {			
			logger.error("[insertTask] : " + e.getMessage());
			e.printStackTrace();
			response = APIResponseBuilder.buildResponse(e.getMessage(), false);
		}
		return response;
	}
	
//	/**
//	 * Will generate an oauthtoken that can be used for authentication in APIs
//	 * @return
//	 */
//	@PostMapping(path = APIConstants.LOGIN_URL)
//	public BaseResponse<Boolean> userLogin() {
//		BaseResponse<Boolean> response = null;
//		try {
//
////			logger.info("[userSignup] : request received : " + req);
////			response = APIResponseBuilder.buildResponse(APIResponse.SUCCESS_MESSAGE, true);		
////			logger.info("[userSignup] : Returning response : " + response);
//
//		} catch (Exception e) {			
//			logger.error("[insertTask] : " + e.getMessage());
//			e.printStackTrace();
//			response = APIResponseBuilder.buildResponse(e.getMessage(), false);
//		}
//		return response;
//	}
}
