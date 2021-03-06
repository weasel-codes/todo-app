package co.nitin.todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.nitin.todo.dao.repo.TaskListRepo;
import co.nitin.todo.model.entity.TaskList;
import co.nitin.todo.model.req.BaseRequest;
import co.nitin.todo.model.req.TaskListCreateReq;
import co.nitin.todo.model.req.TaskListUpdateReq;
import co.nitin.todo.model.response.TaskListCreateRes;
import co.nitin.todo.model.response.TaskListUpdateRes;

@Service
public class TaskListCRUDService {

	private static final Logger logger = LoggerFactory.getLogger(TaskListCRUDService.class);
	
	private TaskListRepo taskListRepo;
	
	@Autowired
	public TaskListCRUDService(TaskListRepo taskListRepo){
		this.taskListRepo = taskListRepo;
	}
			
	public TaskListCreateRes createTaskList(TaskListCreateReq req){
		TaskList list = new TaskList(req.getTaskName(), req.getTaskDetails());
		list = this.taskListRepo.saveAndFlush(list);
		
		TaskListCreateRes res = new TaskListCreateRes(list.getId(), list.getName(), list.getDescription());
		logger.info("[createTaskList] : Returning response : " + res);
		return res;
	}
	
	@Transactional
	public TaskListUpdateRes updateTaskList(BaseRequest<TaskListUpdateReq> req) {
		
		TaskList list = this.taskListRepo.getOne(req.getRequest().getTaskListId());
		logger.info("[updateTaskList] : Task List before update : " + list);
		list.setName(req.getRequest().getTaskListName());
		list.setDescription(req.getRequest().getTaskListDetails());

		this.taskListRepo.save(list);
		logger.info("[updateTaskList] : Task List after update : " + list);
		
		TaskListUpdateRes res = new TaskListUpdateRes(list.getName(), list.getDescription(), list.getId());
		return res;
	}
	
}
