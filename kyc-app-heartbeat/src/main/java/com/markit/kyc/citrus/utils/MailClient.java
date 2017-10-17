package com.markit.kyc.citrus.utils;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.Assert;
import org.testng.TestListenerAdapter;

public class MailClient extends TestListenerAdapter implements InitializingBean 
{

	private static final Logger logger = LoggerFactory.getLogger(MailClient.class.getName());

	@Resource public JavaMailSender mailSender ;
	
	@Value("${failTestMsg.from}") private String msgFrom;
	@Value("${failTestMsg.to}") private String msgTo;
	 
	public void sendMessage(String subject, String body) {
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
        try{
        	MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		    helper.setFrom(msgFrom);
	    	helper.setTo(msgTo);
		    helper.setSubject(subject);
		    
			helper.setText(body, true);
		
            mailSender.send(mimeMessage);
        }
        catch (MailException e) {
        	logger.error("Fail to send message", e);
        } catch (MessagingException e) {
        	logger.error("Fail to create message", e);
		}
    }
	
	/* SPRING RELATED METHODS*/
	@Override
	public void afterPropertiesSet() throws Exception {
        Assert.notNull(mailSender);
    }
}