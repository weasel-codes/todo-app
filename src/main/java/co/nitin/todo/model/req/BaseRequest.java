package co.nitin.todo.model.req;

public class BaseRequest<T> {

	private Long mobile;
	private T request;

	public BaseRequest(Long mobile, T request) {
		super();
		this.mobile = mobile;
		this.request = request;
	}

	public Long getMobile() {
		return mobile;
	}

	public T getRequest() {
		return request;
	}
}
