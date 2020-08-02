package co.nitin.todo.utils;

import co.nitin.todo.constants.APIResponse;
import co.nitin.todo.model.response.BaseResponse;

public class APIResponseBuilder {

	public static <T> BaseResponse<T> buildResponse(String message, T response){
		if (response == null)
			return new BaseResponse<T>(APIResponse.FAILURE_CODE, message, response);
		else 
			return new BaseResponse<T>(APIResponse.SUCCESS_CODE, message, response);			
	}
}
