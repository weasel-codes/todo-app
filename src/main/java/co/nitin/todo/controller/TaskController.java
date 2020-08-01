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
import co.nitin.todo.model.req.TaskCreateReq;
import co.nitin.todo.model.req.TaskUpdateReq;
import co.nitin.todo.model.response.BaseResponse;
import co.nitin.todo.model.response.TaskCreateRes;
import co.nitin.todo.model.response.TaskUpdateRes;
import co.nitin.todo.service.TaskCrudService;

/**
 * @Controller and @ResponseBody : these both are equivalent to @RestController
 * @author weasel
 */
@RestController
public class TaskController {

	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
	private TaskCrudService service;
	
	@Autowired
	public TaskController(TaskCrudService service) {
		this.service = service;
	}

	@PostMapping(APIConstants.TASK_CREATE)
	public BaseResponse<TaskCreateRes> insertTask(@RequestBody TaskCreateReq req) {

		BaseResponse<TaskCreateRes> response = null;
		try {

			logger.info("[insertTask] : request received : " + req);
			TaskCreateRes resp = null;
			resp = this.service.createTask(req);
			response = this.buildResponse(APIResponse.SUCCESS_MESSAGE, resp);		
			logger.info("[insertTask] : Returning response : " + response);

		} catch (Exception e) {			
			logger.error("[insertTask] : " + e.getMessage());
			e.printStackTrace();
			response = this.buildResponse(e.getMessage(), null);
		}
		return response;
	}

	@PostMapping(path = APIConstants.TASK_UPDATE)	
	public BaseResponse<TaskUpdateRes> updateTask(@RequestBody BaseRequest<TaskUpdateReq> req) {
		BaseResponse<TaskUpdateRes> response = null;
		try {

			logger.info("[updateTask] : request received : " + req);
			TaskUpdateRes res = this.service.updateTask(req);
			response = this.buildResponse(APIResponse.SUCCESS_MESSAGE, res);
			logger.info("[updateTask] : Returning response : " + res);
			
		} catch (Exception e) {
			logger.error("[updateTask] : " + e.getMessage());
			e.printStackTrace();
			response = this.buildResponse(e.getMessage(), null);
		}
		return response;
	}

	private <T> BaseResponse<T> buildResponse(String message, T response){
		if (response == null)
			return new BaseResponse<T>(APIResponse.FAILURE_CODE, message, response);
		else 
			return new BaseResponse<T>(APIResponse.SUCCESS_CODE, message, response);			
	}
}
