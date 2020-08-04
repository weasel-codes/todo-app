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
	}

	
}
