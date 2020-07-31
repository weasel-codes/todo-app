package co.nitin.todo.model.req;

public class TaskUpdateReq {

	private String taskName;
	private String taskDetails;
	private Long taskId;
	private Long taskListId;

	public TaskUpdateReq(String taskName, String taskDetails, Long taskId, Long taskListId) {
		super();
		this.taskName = taskName;
		this.taskDetails = taskDetails;
		this.taskId = taskId;
		this.taskListId = taskListId;
	}

	public Long getTaskListId() {
		return taskListId;
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
