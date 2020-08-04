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
	
	/**
	 * Will generate an oauthtoken that can be used for authentication in APIs
	 * @return
	 */
	@PostMapping(path = APIConstants.LOGIN_URL)
	public BaseResponse<Boolean> userLogin() {
		BaseResponse<Boolean> response = null;
		try {

//			logger.info("[userSignup] : request received : " + req);
//			response = APIResponseBuilder.buildResponse(APIResponse.SUCCESS_MESSAGE, true);		
//			logger.info("[userSignup] : Returning response : " + response);

		} catch (Exception e) {			
			logger.error("[insertTask] : " + e.getMessage());
			e.printStackTrace();
			response = APIResponseBuilder.buildResponse(e.getMessage(), false);
		}
		return response;
	}
}
