package co.nitin.todo.model.req;

public class TaskListUpdateReq {

	private String taskListName;
	private String taskListDetails;
	private Long taskListId;
	
	public TaskListUpdateReq(String taskName, String taskDetails, Long taskId) {
		super();
		this.taskListName = taskName;
		this.taskListDetails = taskDetails;
		this.taskListId = taskId;
	}

	public String getTaskName() {
		return taskListName;
	}

	public String getTaskDetails() {
		return taskListDetails;
	}

	public Long getTaskId() {
		return taskListId;
	}
}
