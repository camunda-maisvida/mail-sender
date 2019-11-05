package br.com.maisvida.camunda.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String from;

	@NotEmpty
	private List<String> to = new ArrayList<>();

	private List<String> cc = new ArrayList<>();

	private List<String> bcc = new ArrayList<>();

	@NotBlank
	private String subject;

	private Map<String, Object> variables = new HashMap<>();

	private EmailDTO() {

	}

	public String getFrom() {

		return from;
	}

	public void setFrom(String from) {

		this.from = from;
	}

	public List<String> getTo() {

		return to;
	}

	public void setTo(List<String> to) {

		this.to = to;
	}

	public void addTo(String to) {

		this.to.add(to);
	}

	public List<String> getCc() {

		return cc;
	}

	public void setCc(List<String> cc) {

		this.cc = cc;
	}

	public void addCc(String cc) {

		this.cc.add(cc);
	}

	public List<String> getBcc() {

		return bcc;
	}

	public void setBcc(List<String> bcc) {

		this.bcc = bcc;
	}

	public void addBcc(String bcc) {

		this.bcc.add(bcc);
	}

	public String getSubject() {

		return subject;
	}

	public void setSubject(String subject) {

		this.subject = subject;
	}

	public Map<String, Object> getVariables() {

		return variables;
	}

	public void setVariables(Map<String, Object> variables) {

		this.variables = variables;
	}

	public void addVariable(String key, String value) {

		this.variables.put(key, value);
	}

	/**
	 * Default description: <br>
	 * <br>
	 *
	 * {@inheritDoc}
	 *
	 * @see java.lang.Object#toString()
	 */
	@JsonIgnore
	@Override
	public String toString() {

		final int maxLen = 10;
		return "EmailDTO [" + ( from != null ? "from=" + from + ", " : "" ) + ( to != null ? "to=" + toString(to, maxLen) + ", " : "" ) + ( cc != null ? "cc=" + toString(cc, maxLen) + ", " : "" ) + ( bcc != null ? "bcc=" + toString(bcc, maxLen) + ", " : "" ) + ( subject != null ? "subject=" + subject + ", " : "" ) + ( variables != null ? "variables=" + toString(variables.entrySet(), maxLen) : "" ) + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {

		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0) {
				builder.append(", ");
			}
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	public static class EmailBuilder {

		private EmailDTO instance;

		private EmailBuilder() {

			this.instance = new EmailDTO();
		}

		public static EmailBuilder newBuild() {

			return new EmailBuilder();
		}

		public EmailBuilder from(String from) {

			if (from != null) {
				this.instance.setFrom(from);
			}
			return this;
		}

		public EmailBuilder to(String... to) {

			if (to != null && to.length > 0) {
				this.instance.setTo(Arrays.asList(to));
			}
			return this;
		}

		public EmailBuilder cc(String... cc) {

			if (cc != null && cc.length > 0) {
				this.instance.setCc(Arrays.asList(cc));
			}
			return this;
		}

		public EmailBuilder bcc(String... bcc) {

			if (bcc != null && bcc.length > 0) {
				this.instance.setBcc(Arrays.asList(bcc));
			}
			return this;
		}

		public EmailBuilder subject(String subject) {

			if (subject != null) {
				this.instance.setSubject(subject);
			}
			return this;
		}

		public EmailBuilder variable(String key, String value) {

			if (key != null && !key.isEmpty() && value != null && !value.isEmpty()) {
				this.instance.addVariable(key, value);
			}
			return this;
		}

		public EmailDTO build() {

			return this.instance;
		}

	}

}
