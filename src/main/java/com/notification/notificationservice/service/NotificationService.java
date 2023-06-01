package com.notification.notificationservice.service;

import javax.mail.MessagingException;

import com.notification.notificationservice.dto.MailDto;

public interface NotificationService {

	void sendMail(MailDto mailDto) throws MessagingException;

}
