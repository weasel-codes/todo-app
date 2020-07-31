package co.nitin.todo.model.response;

public class TaskListUpdateRes {

	private String taskListName;
	private String taskListDetails;
	private Long taskListId;
	
	public TaskListUpdateRes(String taskListName, String taskListDetails, Long taskListId) {
		super();
		this.taskListName = taskListName;
		this.taskListDetails = taskListDetails;
		this.taskListId = taskListId;
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

	@Override
	public String toString() {
		return "TaskListUpdateRes [taskListName=" + taskListName + ", taskListDetails=" + taskListDetails
				+ ", taskListId=" + taskListId + "]";
	}
	
	
}
