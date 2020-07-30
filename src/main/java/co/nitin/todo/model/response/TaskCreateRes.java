package co.nitin.todo.model.response;

public class TaskCreateRes {

	private Long mobile;
	private String taskId;
	private String taskName;
	private String taskDetails;
	private Long taskListId;
	private String taskListName;
	private String taskListDetails;
	
	public TaskCreateRes(Long mobile, String taskId, String taskName, String taskDetails, Long taskListId,
			String taskListName, String taskListDetails) {
		super();
		this.mobile = mobile;
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDetails = taskDetails;
		this.taskListId = taskListId;
		this.taskListName = taskListName;
		this.taskListDetails = taskListDetails;
	}

	public Long getMobile() {
		return mobile;
	}

	public String getTaskId() {
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

	public String getTaskListName() {
		return taskListName;
	}

	public String getTaskListDetails() {
		return taskListDetails;
	}

	@Override
	public String toString() {
		return "TaskCreateRes [mobile=" + mobile + ", taskId=" + taskId + ", taskName=" + taskName + ", taskDetails="
				+ taskDetails + ", taskListId=" + taskListId + ", taskListName=" + taskListName + ", taskListDetails="
				+ taskListDetails + "]";
	}
	
}
