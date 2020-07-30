package co.nitin.todo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.nitin.todo.controller.TaskCRUDController;
import co.nitin.todo.model.req.TaskCreateReq;
import co.nitin.todo.model.req.TaskListCreateReq;
import co.nitin.todo.model.response.TaskListCreateRes;
import co.nitin.todo.service.TaskCrudService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TaskCRUDController.class)
public class TaskCRUDControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(TaskCRUDControllerTest.class);
	@Autowired private MockMvc mvc;
	@MockBean private TaskCrudService service;	//need for controller, else will not ffind
	
	@Test
	void testFetch() {
		
		try {
			mvc.perform(get("/task/fetchall").contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testInsertTask() {

//		//create tasklist
//		TaskListCreateReq listReq = new TaskListCreateReq(8989813163l, "TODO Project", "Basic Demo TODO project work");
//		try {
//			mvc.perform(post("task-list/create", listReq).contentType(MediaType.APPLICATION_JSON))
//					.andDo(print())
//					.andExpect(status().isOk());
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//insert task
		TaskCreateReq req = 
				new TaskCreateReq(8989813163l, "TODO App document", "create new todoapp document to guide users", 123456789l);
		try {
			mvc.perform(post("/task/save")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsBytes(req))
						.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
