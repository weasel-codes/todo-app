package co.nitin.todo.model.response;

public class BaseResponse<T> {

	private String respCode;
	private String respMessage;
	private T response;
	
	public BaseResponse(String respCode, String respMessage, T response) {
		super();
		this.respCode = respCode;
		this.respMessage = respMessage;
		this.response = response;
	}

	public String getRespCode() {
		return respCode;
	}
	
	public String getRespMessage() {
		return respMessage;
	}
	
	public T getResponse() {
		return response;
	}	
}
