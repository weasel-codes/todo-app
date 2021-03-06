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

	public String getTaskListName() {
		return taskListName;
	}

	public String getTaskListDetails() {
		return taskListDetails;
	}

	public Long getTaskListId() {
		return taskListId;
	}

	@Override
	public String toString() {
		return "TaskListUpdateReq [taskListName=" + taskListName + ", taskListDetails=" + taskListDetails
				+ ", taskListId=" + taskListId + "]";
	}
	
	
}
