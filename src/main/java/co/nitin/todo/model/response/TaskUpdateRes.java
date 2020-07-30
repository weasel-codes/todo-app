package co.nitin.todo.model.response;

public class TaskUpdateRes {

	private String taskName;
	private String taskDetails;
	private Long taskId;
	
	public TaskUpdateRes(String taskName, String taskDetails, Long taskId) {
		super();
		this.taskName = taskName;
		this.taskDetails = taskDetails;
		this.taskId = taskId;
	}
	
	public String getTaskName() {
		return taskName;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public Long getTaskId() {
		return taskId;
	}
}
