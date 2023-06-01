package com.notification.notificationservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Chandhru
 *
 */
@Getter
@Setter
@Builder
public class MailDto {

	private String from;

	@Email(message = "Invalid email address")
	@NotNull(message = "sendToAddress must not be null")
	private String sendToAddress;

	@NotEmpty(message = "subject must not be empty")
	@NotNull(message = "subject must not be null")
	private String subject;

	@NotEmpty(message = "templateName must not be empty")
	@NotNull(message = "templateName must not be null")
	private String templateName;
	
	@NotNull(message = "email body must not be null")
	private Object body;
	
	private String cc;
	
	private String bccEmailList;

}
