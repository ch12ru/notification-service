package com.notification.notificationservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessMessage {
	private String status;
	private String message;

	public SuccessMessage(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

}
