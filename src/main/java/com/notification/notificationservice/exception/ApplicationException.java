package com.notification.notificationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationException {
	
	private HttpStatus httpStatus;
	
	public ApplicationException(String message,HttpStatus httpStatus) {
		super();
		this.httpStatus = httpStatus;
	}
	

}
