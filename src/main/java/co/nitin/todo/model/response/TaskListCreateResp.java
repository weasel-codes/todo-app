package co.nitin.todo.model.response;

public class TaskListCreateResp {
	
	private Long id;
	private String name;
	private String description;
	
	public TaskListCreateResp(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return "TaskListCreateResp [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
}
