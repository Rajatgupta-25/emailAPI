package com.boot.email.services;

import java.io.File;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	//It is responsible to send text message
	public boolean sendEmail(String subject, String message, String to) {
		boolean flag = false;
		String host = "smtp.gmail.com";
		
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("1900300100174@ipec.org.in", "<rajat0174>");
			}
			
		});
		
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);
		
		try {
			m.setFrom("1900300100174@ipec.org.in");
			
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			m.setSubject(subject);
			
			m.setText(message);
			
			Transport.send(m);
			flag = true;
			
			System.out.println("Sent  Successfully............");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	//This is responsible to send the message with attachments
	public boolean sendEmailWithAttachment(String subject, String message, String to, String from) {
		boolean flag = false;
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication("1900300100174@ipec.org.in", "<rajat0174>");
			}
			
		});
		
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);
		
		try {
			m.setFrom(from);
			
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			m.setSubject(subject);
			
			String path = "F:\\images\\download.png";
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			
			MimeBodyPart textMime = new MimeBodyPart();
			
			MimeBodyPart fileMime = new MimeBodyPart();
			
			try {
				textMime.setText(message);
				
				File file =  new File(path);
				fileMime.attachFile(file);
				
				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			m.setContent(mimeMultipart);
			
			Transport.send(m);
			
			flag = true;
			
			System.out.println("Sent  Successfully............");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
}
