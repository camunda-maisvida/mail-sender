package br.com.maisvida.camunda.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailClientTests {

	@Autowired
	private MailClient mailClient;
	
	@Resource
	private JavaMailSender mailSender;

	private GreenMail smtpServer;

	@BeforeEach
	public void setUp() throws Exception {
		 //Test properties      
		smtpServer = new GreenMail(ServerSetupTest.SMTP);      
		 //GreenMail server startup using test configuration      
		smtpServer.start();      
	}

	@Test
	public void shouldSendMail() throws Exception {
		// given
		String recipient = "rodolfo.maisvida@gmail.com";
		String message = "Test message content";
		// when
		mailClient.prepareAndSend(recipient, message);
		// then
		String content = "<span>" + message + "</span>";
		assertReceivedMessageContains(content);
	}

	private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
		MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
		assertEquals(1, receivedMessages.length);
		String content = (String) receivedMessages[0].getContent();
		assertTrue(content.contains(expected));
	}

	@AfterEach
	public void tearDown() throws Exception {
		smtpServer.stop();
	}

}
