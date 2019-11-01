package br.com.maisvida.camunda.dto;

import java.io.Serializable;

public class MailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	public String to;
	public String message;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MailDTO [to=" + to + ", message=" + message + "]";
	}
}
