package co.nitin.todo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nitin.todo.dao.repo.TaskListRepo;
import co.nitin.todo.dao.repo.TaskRepo;
import co.nitin.todo.dao.repo.UserRepo;
import co.nitin.todo.model.entity.Task;
import co.nitin.todo.model.entity.TaskList;
import co.nitin.todo.model.entity.User;
import co.nitin.todo.model.req.TaskCreateReq;
import co.nitin.todo.model.req.TaskListCreateReq;
import co.nitin.todo.model.response.TaskCreateRes;
import co.nitin.todo.model.response.TaskListCreateResp;

@Service
public class TaskCrudService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskCrudService.class);
	
	private TaskRepo taskRepo;
	private TaskListRepo taskListRepo;
	private UserRepo userRepo;
	
	@Autowired
	public TaskCrudService(TaskRepo taskRepo, TaskListRepo taskListRepo, UserRepo userRepo){
		this.taskRepo = taskRepo;
		this.taskListRepo = taskListRepo;
		this.userRepo = userRepo;
	}
	
	public List<Task> fetchAllTask() {
		return this.taskRepo.findAll();
	}
	
	public List<TaskList> fetchAllTaskList() {
		return this.taskListRepo.findAll();
	}

	public List<User> fetchAllUser() {
		return this.userRepo.findAll();
	}
	
	public TaskListCreateResp createTaskList(TaskListCreateReq req){
		TaskList list = new TaskList(req.getTaskName(), req.getTaskDetails());
		list = this.taskListRepo.saveAndFlush(list);
		
		TaskListCreateResp res = new TaskListCreateResp(list.getId(), list.getName(), list.getDescription());
		logger.info("[createTaskList] : Returning response : " + res);
		return res;
	}
	
	public TaskCreateRes createTask(TaskCreateReq req) throws Exception {
		
		List<User> users = this.userRepo.findByMobile(req.getMobile());
		if(users.size()>1) throw new Exception("Multiple users received for same phone number");
		
		User user = users.get(0);
		logger.info("[createTask] : user id : " + user.getUserId());
		
		TaskList list = this.taskListRepo.findById(req.getTaskListId()).get();
		logger.info("[createTask] : task list id : " + list.getId());
		
		Task task = new Task(req.getTaskName(), req.getTaskDetails(), user, list);
		task = this.taskRepo.saveAndFlush(task);
	
		TaskCreateRes res = new TaskCreateRes(user.getMobile(), task.getId().toString(), task.getName(), task.getDetails(), list.getId(), list.getName(), list.getDescription());
		
		logger.info("[createTask] : Returning response : " + res);
		return res;
	}
}
