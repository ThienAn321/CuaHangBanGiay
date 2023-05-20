package com.molla.service.impl;

import com.molla.model.EmailInfo;
import com.molla.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import jakarta.mail.MessagingException;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	JavaMailSender emailSender;

//	@Override
//	public void sendMail(EmailInfo emailInfo) throws MessagingException {
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		simpleMailMessage.setFrom(emailInfo.getFrom());
//		simpleMailMessage.setTo(emailInfo.getTo());
//		simpleMailMessage.setSubject(emailInfo.getSubject());
//		simpleMailMessage.setText(emailInfo.getBody());
//		emailSender.send(simpleMailMessage);
//	}

	@Override
	public void sendMail(String from, String to, String subject, String message) throws MessagingException {
		MimeMessage mineMessage = emailSender.createMimeMessage();
//		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//		simpleMailMessage.setFrom(from);
//		simpleMailMessage.setTo(to);
//		simpleMailMessage.setSubject(subject);
//		simpleMailMessage.setText(message);
		mineMessage.setFrom(new InternetAddress(from));
		mineMessage.setRecipients(MimeMessage.RecipientType.TO, to);
		mineMessage.setSubject(subject);
		mineMessage.setContent(message, "text/html; charset=utf-8");
		emailSender.send(mineMessage);

	}

	@Override
	 public String buildEmail(String name, String token) {
	        return 	"Chào bạn " + name +",<br>\r\n"
	        		+ "Cảm ơn bạn đã đăng kí tài khoản tại cửa hàng Molla. Vui lòng click vào đường link bên dưới để kích hoạt tài khoản :<br>\r\n"
	        		+ "<a href=\"http://localhost:8080/confirm?token=" + token + "\">Active account</a><br>\r\n"
	        		+ "Đường link sẽ hết hạn trong vòng 15 phút.<br>\r\n"
	        		+ "Cảm ơn bạn.";
	    }

}
