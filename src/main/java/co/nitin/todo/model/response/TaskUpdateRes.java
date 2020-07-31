package co.nitin.todo.model.response;

public class TaskUpdateRes {

	private Long taskId;
	private String taskName;
	private String taskDetails;
	private Long taskListId;
	
	public TaskUpdateRes( Long taskId, String taskName, String taskDetails, Long taskListId) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDetails = taskDetails;
		this.taskListId = taskListId;
	}
	
	public Long getTaskId() {
		return taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public Long getTaskListId() {
		return taskListId;
	}

}
