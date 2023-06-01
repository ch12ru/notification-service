package com.notification.notificationservice.exceptionhandler;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class HandleException extends RuntimeException {

	private static final long serialVersionUID = -6787510521361859225L;

	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseEntity<Object> exception(IllegalArgumentException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> exception(Exception exception, WebRequest requset) {
		ExceptionMessage exceptionMessageObj = new ExceptionMessage();

		if (exception instanceof MethodArgumentNotValidException) {
			StringBuilder sb = new StringBuilder();
			List<FieldError> fieldErrors = ((MethodArgumentNotValidException) exception).getBindingResult()
					.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				sb.append(fieldError.getDefaultMessage());
				sb.append(";");
			}
			exceptionMessageObj.setMessage(sb.toString());
		} else {
			exceptionMessageObj.setMessage(exception.getLocalizedMessage());
		}

		exceptionMessageObj.setError(exception.getClass().getCanonicalName());
		exceptionMessageObj.setPath(((ServletWebRequest) requset).getRequest().getServletPath());

		return new ResponseEntity<>(exceptionMessageObj, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
