package edu.utdallas.sxb170035.library_app.data.jpa.controller.exception;

public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String code;

	public ApplicationException(String code, String message) {
		super(message);
		this.setCode(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
