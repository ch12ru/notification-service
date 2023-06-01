package com.notification.notificationservice.service.serviceImpl;

import static com.notification.notificationservice.common.Constant.*;
import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.notification.notificationservice.dto.MailDto;
import com.notification.notificationservice.service.NotificationService;

/**
 * @author Chandhru
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	@Value("${spring.mail.username}")
	private String from;
	private final JavaMailSender emailService;
	private final SpringTemplateEngine templateEngine;

	public NotificationServiceImpl(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
		super();
		this.emailService = emailSender;
		this.templateEngine = templateEngine;
	}

	
	@Override
	public void sendMail(MailDto mailDto) throws MessagingException {

		MimeMessage message = emailService.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name());
		Context context = new Context();
		context.setVariable(DATA, mailDto);

		helper.setFrom(from);
		helper.setTo(mailDto.getSendToAddress());
		helper.setSubject(mailDto.getSubject());

		String html = templateEngine.process(WELCOME_EMAIL, context);
		helper.setText(html, true);

		emailService.send(message);
	}

}
