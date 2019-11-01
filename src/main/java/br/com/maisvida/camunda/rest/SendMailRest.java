package br.com.maisvida.camunda.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.maisvida.camunda.client.MailClient;
import br.com.maisvida.camunda.dto.MailDTO;

@RestController
public class SendMailRest {

	@Autowired
	private MailClient mailClient;

	@PostMapping(value = "/email/send")
	public ResponseEntity<Object> sendMail(@RequestBody MailDTO mail) {

		mailClient.send(mail);

		return new ResponseEntity<>("O email está sendo enviado!", HttpStatus.ACCEPTED);
	}
}
