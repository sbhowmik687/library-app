package edu.utdallas.sxb170035.library_app.data.jpa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {
	public static  <T> ResponseEntity<T> buildSucessResponse(Object response) {
		ResponseEntity<Object> responseEntity = new ResponseEntity(response,HttpStatus.OK);
		return null;
	}

	public static ResponseEntity<Object> buildErrorResponse(String error) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		return responseEntity;
	}
}
