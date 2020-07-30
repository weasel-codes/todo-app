package co.nitin.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.nitin.todo.dao.repo.TaskListRepo;
import co.nitin.todo.dao.repo.TaskRepo;
import co.nitin.todo.dao.repo.UserRepo;
import co.nitin.todo.model.entity.Task;
import co.nitin.todo.model.entity.TaskList;
import co.nitin.todo.model.entity.User;
import co.nitin.todo.model.req.TaskListCreateReq;
import co.nitin.todo.model.response.TaskListCreateResp;

@Service
public class TaskCrudService {
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
		TaskList list = new TaskList();
		list.setName(req.getTaskName());
		list.setDescription(req.getTaskDetails());
		list = this.taskListRepo.saveAndFlush(list);
		
		return new TaskListCreateResp(list.getId(), list.getName(), list.getDescription());
	}
}
