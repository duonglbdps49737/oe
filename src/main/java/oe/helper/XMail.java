package oe.helper;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class XMail {
	/**
	 * Gửi email qua GMail Server
	 * @param from email người gửi
	 * @param to email người nhận
	 * @param subject tiêu đề email
	 * @param content nội dung email
	 */
	static public void send(String from, String to, String subject, String content) {
		var properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		var authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				final String username = "songlong2k@gmail.com";
				final String password = "ytfb fhmk shys wuvr";
				return new PasswordAuthentication(username, password);
			}
		};
		var session = Session.getInstance(properties, authenticator);

		try {
			var mail = new MimeMessage(session);
			mail.setFrom(new InternetAddress(from));
			mail.setRecipients(Message.RecipientType.TO, to);
			mail.setSubject(subject, "utf8");
			mail.setContent(content, "text/html; charset=utf8");
			
			mail.setReplyTo(mail.getFrom());
			Transport.send(mail);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	static public void send(String to, String subject, String content) {
		var from = "FPT Polytechnic <poly@gmail.com>";
		send(from, to, subject, content);
	}
}