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
import co.nitin.todo.model.entity.TaskList;
import co.nitin.todo.model.req.TaskCreateReq;
import co.nitin.todo.model.req.TaskListCreateReq;
import co.nitin.todo.model.response.TaskListCreateResp;
import co.nitin.todo.service.TaskCrudService;

/**
 * @Controller and @ResponseBody : these both are equivalent too @RestController
 * @author weasel
 *
 */
@RestController
public class TaskCRUDController {

	private static final Logger logger = LoggerFactory.getLogger(TaskCRUDController.class);
	private TaskCrudService service;
	
	@Autowired
	public TaskCRUDController(TaskCrudService service) {
		this.service = service;
	}

	@RequestMapping(path = APIConstants.TASK_LIST_FETCH_ALL, method = RequestMethod.GET)
	public List<TaskList> getTaskList() {	
		
		logger.info("Fetch all task");
		List<TaskList> list = this.service.fetchAllTaskList();
		logger.info("[getTaskList] : Returning List : " + list);
		return this.service.fetchAllTaskList();
	}
	
	@PostMapping(path = APIConstants.TASK_LIST_CREATE)
	public TaskListCreateResp createTaskList(@RequestBody TaskListCreateReq req) {
		
		logger.info("[createTaskList] : Request received : " + req);
		TaskListCreateResp resp = this.service.createTaskList(req);
		logger.info("[createTaskList] : Returning response : " + resp);
		return resp;
	}

	@RequestMapping(path = APIConstants.TASK_CREATE, method = RequestMethod.POST)
	public TaskCreateReq insertTask(@RequestBody TaskCreateReq req) {
		
		return req;
	}

	@RequestMapping(path = APIConstants.TASK_UPDATE, method = RequestMethod.POST)
	public boolean updateTask(@RequestBody Object object) {
		return true;
	}
}
