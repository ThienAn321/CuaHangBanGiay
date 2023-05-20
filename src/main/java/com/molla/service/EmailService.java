package com.molla.service;

import com.molla.model.EmailInfo;

import jakarta.mail.MessagingException;

public interface EmailService {
//	public void sendMail(EmailInfo emailInfo) throws MessagingException;
	public void sendMail(String from, String to, String subject, String message) throws MessagingException;
	public String buildEmail(String name, String link);
}
