package edu.utdallas.sxb170035.library_app.data.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import edu.utdallas.sxb170035.library_app.data.jpa.controller.exception.ApplicationException;
import edu.utdallas.sxb170035.library_app.data.jpa.controller.exception.ExceptionResponse;
import edu.utdallas.sxb170035.library_app.data.jpa.util.ValidationUtil;

@ControllerAdvice
public class ExceptionHandlingController {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ExceptionResponse> resourceNotFound(ApplicationException ex) {
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode(ex.getCode());
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		ExceptionResponse response = new ExceptionResponse();
		response.setErrorCode("Validation Error");
		response.setErrorMessage(ex.getMessage());
		response.setErrors(ValidationUtil.fromBindingErrors(result));
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
	}
}
