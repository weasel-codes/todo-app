package co.nitin.todo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.nitin.todo.constants.APIConstants;
import co.nitin.todo.constants.APIResponse;
import co.nitin.todo.model.entity.TaskList;
import co.nitin.todo.model.req.BaseRequest;
import co.nitin.todo.model.req.TaskListCreateReq;
import co.nitin.todo.model.req.TaskListUpdateReq;
import co.nitin.todo.model.response.BaseResponse;
import co.nitin.todo.model.response.TaskListCreateRes;
import co.nitin.todo.model.response.TaskListUpdateRes;
import co.nitin.todo.service.TaskListCRUDService;

@RestController
public class TaskListController {

	private static final Logger logger = LoggerFactory.getLogger(TaskListController.class);
	private TaskListCRUDService service;
	
	@Autowired
	public TaskListController(TaskListCRUDService service) {
		this.service = service;
	}
	
	@RequestMapping(path = APIConstants.TASK_LIST_FETCH_ALL, method = RequestMethod.GET)
	public BaseResponse<List<TaskList>> getTaskList() {
		
		BaseResponse<List<TaskList>> response = null;
		try {

			logger.info("Fetch all task");
			List<TaskList> list = this.service.fetchAllTaskList();
			response = this.buildResponse(APIResponse.SUCCESS_MESSAGE, list);
			logger.info("[getTaskList] : Returning List : " + response);

		} catch (Exception e) {	
			logger.error("[getTaskList] : " + e.getMessage());
			e.printStackTrace();
			response = this.buildResponse(e.getMessage(), null);
		}
		return response;
	}
	
	@PostMapping(APIConstants.TASK_LIST_CREATE)
	public BaseResponse<TaskListCreateRes> createTaskList(@RequestBody TaskListCreateReq req) {
		
		BaseResponse<TaskListCreateRes> response = null;
		try {

			logger.info("[createTaskList] : Request received : " + req);
			TaskListCreateRes resp = this.service.createTaskList(req);
			response = this.buildResponse(APIResponse.SUCCESS_MESSAGE, resp);		
			logger.info("[createTaskList] : Returning response : " + response);

		} catch (Exception e) {			
			logger.error("[createTaskList] : " + e.getMessage());
			e.printStackTrace();
			response = this.buildResponse(e.getMessage(), null);
		}
		return response;
	}

	@PostMapping(path = APIConstants.TASK_LIST_UPDATE)	
	public BaseResponse<TaskListUpdateRes> updateTaskList(@RequestBody BaseRequest<TaskListUpdateReq> req) {
		BaseResponse<TaskListUpdateRes> response = null;
		try {

			logger.info("[updateTaskList] : request received : " + req);
			TaskListUpdateRes res = this.service.updateTaskList(req);
			response = this.buildResponse(APIResponse.SUCCESS_MESSAGE, res);
			logger.info("[updateTaskList] : Returning response : " + response);

		} catch (Exception e) {			
			logger.error("[updateTaskList] : " + e.getMessage());
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
