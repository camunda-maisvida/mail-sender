package br.com.maisvida.camunda.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.maisvida.camunda.client.SolicitacaoDeFeriasMailService;
import br.com.maisvida.camunda.dto.EmailDTO;

@RestController
public class EnviadorDeEmailRest {

	@Autowired
	private SolicitacaoDeFeriasMailService solicitacaoDeFeriasMailService;

	@PostMapping(value = "/email/solicitacao-ferias/enviar")
	public ResponseEntity<Object> enviar(@RequestBody(required = true) @Valid EmailDTO mail) {

		solicitacaoDeFeriasMailService.send(mail);

		return new ResponseEntity<>("O email está sendo enviado!", HttpStatus.ACCEPTED);
	}
}
