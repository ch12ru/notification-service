package com.notification.notificationservice.controller;

import static com.notification.notificationservice.common.Constant.*;
import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notification.notificationservice.dto.MailDto;
import com.notification.notificationservice.dto.SuccessMessage;
import com.notification.notificationservice.service.NotificationService;

/**
 * @author Chandhru M
 *
 */
@RestController
@RequestMapping("/notify/v1")
public class NotificationController {

	@Autowired
	NotificationService notificationService;

	/**
	 * Description : Send email api
	 * 
	 * @param mailDto
	 * @return SuccessMessage
	 * @throws MessagingException
	 */
	@PostMapping("/send-mail")
	public ResponseEntity<SuccessMessage> sendMail(@Valid @RequestBody MailDto mailDto) throws MessagingException {

		notificationService.sendMail(mailDto);

		return new ResponseEntity<>(new SuccessMessage(SUCCESS, MAIL_SENT_SUCCESSFULLY), HttpStatus.OK);

	}
}
