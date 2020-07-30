package co.nitin.todo.model.req;

public class TaskListCreateReq {

	private Long mobile;
	private String taskListName;
	private String taskListDetails;
	
	public TaskListCreateReq(Long mobile, String taskListName, String taskListDetails) {
		super();
		this.mobile = mobile;
		this.taskListName = taskListName;
		this.taskListDetails = taskListDetails;
	}

	public Long getMobile() {
		return mobile;
	}
	public String getTaskName() {
		return taskListName;
	}
	public String getTaskDetails() {
		return taskListDetails;
	}

	@Override
	public String toString() {
		return "TaskListCreateReq [mobile=" + mobile + ", taskListName=" + taskListName + ", taskListDetails="
				+ taskListDetails + "]";
	}
}
