package br.com.drogaria.email.dto;

import java.io.Serializable;
import java.util.Set;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1641282696536995654L;

	private String assunto;
	private String mensagem;
	private Set<String> destinatarios;
	
	public Set<String> getDestinatarios() {
		return destinatarios;
	}
	public void setDestinatarios(Set<String> destinatarios) {
		this.destinatarios = destinatarios;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
