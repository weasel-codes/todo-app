package co.nitin.todo.model.req;
/**
 * For creation of a task
 * 
 * Assumes user is already logged in and has a TaskList already created for it.
 * 
 * If not created, can add task to default index 0;
 * @author weasel
 */
public class TaskCreateReq {

	private Long mobile;
	private String taskName;
	private String taskDetails;
	private Long taskListId;
	
	public TaskCreateReq(Long mobile, String taskName, String taskDetails, Long taskListId) {
		super();
		this.mobile = mobile;
		this.taskName = taskName;
		this.taskDetails = taskDetails;
		this.taskListId = taskListId;
	}

	public Long getMobile() {
		return mobile;
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

	@Override
	public String toString() {
		return "TaskCreateReq [mobile=" + mobile + ", taskName=" + taskName + ", taskDetails=" + taskDetails
				+ ", taskListId=" + taskListId + "]";
	}
}
