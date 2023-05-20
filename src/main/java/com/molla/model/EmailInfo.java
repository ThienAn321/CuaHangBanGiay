package com.molla.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class EmailInfo {
	private String from;
	private String to;
	private String[] cc;
	private String[] bcc;
	private String subject;
	private String body;
	private String[] attachments;

	public EmailInfo(String to, String subject, String body) {
		this.from = "Cửa hàng bán giày Molla";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
