package com.biz.common;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class SendMailGoogle
{
	private final String HOST = "smtp.gmail.com";
	private final String HOST_ID = "whatyousay77@gmail.com";
	private final String HOST_PW ="dlekdus1234!@";
	private final String HOST_PORT ="587";
	private final String HOST_STARTLS ="true";
	private final String HOST_AUTH ="true";
	
//	public static void main(String [] args){
//		SendMailGoogleTest g = new SendMailGoogleTest();
//		g.sendMail();
//	}	
		
	public void sendMail(String to, String subject, String body) {	
		// Recipient's email ID needs to be mentioned.
		//String to = "lss910412@gmail.com";
		//String to = "coms.korea@gmail.com";
		
		// Get system properties
		Properties properties = System.getProperties();
		// Setup mail server
		properties.put("mail.smtp.starttls.enable", HOST_STARTLS);
		properties.put("mail.smtp.host", HOST);
		properties.put("mail.smtp.user", HOST_ID);
		properties.put("mail.smtp.password", HOST_PW);
		properties.put("mail.smtp.port", HOST_PORT);
		properties.put("mail.smtp.auth", HOST_AUTH);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(HOST_ID));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO,
					new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);
			message.setContent(body, "text/html");
			//message.setText(htmlStr);
			
			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect(HOST, HOST_ID, HOST_PW);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
