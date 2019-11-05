package br.com.maisvida.camunda.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.maisvida.camunda.dto.EmailDTO;

@Service
public class SolicitacaoDeFeriasMailService {

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private EmailClient mailClient;

	public void send(EmailDTO mailDTO) {

		final String content = this.getContent(mailDTO.getVariables());
		mailClient.sendAsync(mailDTO, content);
	}

	public String getContent(Map<String, Object> variables) {

		Context context = new Context();
		if (variables != null && !variables.isEmpty()) {
			variables.forEach((key, value) -> context.setVariable(key, value));
		}
		return templateEngine.process("solicitacaoFeriasTemplate", context);
	}

}
