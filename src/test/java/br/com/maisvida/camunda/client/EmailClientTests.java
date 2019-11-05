package br.com.maisvida.camunda.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetupTest;

import br.com.maisvida.camunda.dto.EmailDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailClientTests {

	@Autowired
	private SolicitacaoDeFeriasMailService solicitacaoDeFeriasMailService;

	private GreenMail smtpServer;

	@BeforeEach
	public void iniciar() throws Exception {

		// Test properties
		smtpServer = new GreenMail(ServerSetupTest.SMTP);
		// GreenMail server startup using test configuration
		smtpServer.start();
	}

	@Test
	public void test_deveEnviarEmail() throws Exception {

		// given
		final EmailDTO mailDTO = EmailDTO.EmailBuilder.newBuild()
				.from("rodolfo.maisvida@gmail.com")
				.to("rodolfocruz121@gmail.com")
				.subject("Teste unitário")
				.variable("message", "Testando conteúdo de mensagem")
				.build();

		// when
		solicitacaoDeFeriasMailService.send(mailDTO);

		// then
		final String content = mailDTO.getVariables().get("message").toString();
		assertReceivedMessageContains(content);
	}

	private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {

		MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
		assertEquals(1, receivedMessages.length);
		String content = (String) receivedMessages[0].getContent();
		assertTrue(content.contains(expected));
	}

	@AfterEach
	public void pararServidor() throws Exception {

		smtpServer.stop();
	}

}
