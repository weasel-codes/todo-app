package co.nitin.todo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.nitin.todo.service.TaskCrudService;

@SpringBootTest
class TodoAppApplicationTests {

	@Autowired
	private TaskCrudService service;
	private static final Logger logger = LoggerFactory.getLogger(TodoAppApplication.class);
	
	@Test
	void contextLoads() {
		logger.info("No of TaskList object returned : " + this.service.fetchAllTaskList().size());
		logger.info("No of User object returned : " + this.service.fetchAllUser().size());
		logger.info("No of Task object returned : " + this.service.fetchAllTask().size());
	}

}
