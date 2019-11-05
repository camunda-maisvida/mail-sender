package br.com.maisvida.camunda.client;

import javax.annotation.Resource;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.maisvida.camunda.dto.EmailDTO;

@Service
public class EmailClient {

	@Resource
	private JavaMailSender mailSender;

	public void prepareAndSend(final EmailDTO mailDTO, String content) {

		final MimeMessagePreparator messagePreparator = mimeMessage -> {
			final MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom(mailDTO.getFrom());
			messageHelper.setTo(mailDTO.getTo().toArray(new String[] {}));
			messageHelper.setSubject(mailDTO.getSubject());
			if (!mailDTO.getCc().isEmpty()) {
				messageHelper.setCc(mailDTO.getCc().toArray(new String[] {}));
			}
			if (!mailDTO.getBcc().isEmpty()) {
				messageHelper.setBcc(mailDTO.getBcc().toArray(new String[] {}));
			}
			messageHelper.setText(content, true);
		};
		try {
			this.mailSender.send(messagePreparator);
		} catch (final MailException e) {
			e.printStackTrace();
		}
	}

	@Async
	public void sendAsync(final EmailDTO mail, String content) {

		this.prepareAndSend(mail, content);

	}

}
