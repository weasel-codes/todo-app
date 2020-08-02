package co.nitin.todo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import co.nitin.todo.service.TaskCrudService;
import co.nitin.todo.service.TaskListCRUDService;


class TodoAppApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(TodoAppApplication.class);
	
	@Autowired private TaskCrudService service;	//need for controller, else will not ffind
	@Autowired private TaskListCRUDService listService;	//need for controller, else will not ffind

	@Test
	void contextLoads() {
		logger.info("No of TaskList object returned : " + this.listService.fetchAllTaskList().size());
//		logger.info("No of User object returned : " + this.service.fetchAllUser().size());
		logger.info("No of Task object returned : " + this.service.fetchAllTask().size());
	}

	
}
