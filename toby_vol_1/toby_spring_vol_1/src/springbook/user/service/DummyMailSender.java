package springbook.user.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class DummyMailSender implements MailSender {
	@Override
	public void send(SimpleMailMessage mailMessage) throws MailException {
		System.out.println("mailMessage : " + mailMessage);
	}
	
	@Override
	public void send(SimpleMailMessage[] mailMessage) throws MailException {
		
	}
}
