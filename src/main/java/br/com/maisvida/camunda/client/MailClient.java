package br.com.maisvida.camunda.client;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.maisvida.camunda.dto.MailDTO;

@Service
public class MailClient {

	@Resource
	private JavaMailSender mailSender;

	@Autowired
	private MailContentBuilder mailContentBuilder;

	public void prepareAndSend(String recipient, String message) {
		MimeMessagePreparator messagePreparator = mimeMessage -> {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("suporte.maisvida@gmail.com");
			messageHelper.setTo(recipient);
			messageHelper.setSubject("[Solicitação de Férias] - Reprovação :( ");
			String content = mailContentBuilder.build(message);
			messageHelper.setText(content, true);
		};
		try {
			mailSender.send(messagePreparator);
		} catch (MailException e) {
			e.printStackTrace();
		}
	}

	@Async
	public void send(MailDTO mail) {
		this.prepareAndSend(mail.to, mail.message);

	}

}