package br.com.drogaria.email.service;

import br.com.drogaria.email.dto.EmailDTO;

public interface EmailService {

	public String enviarEmail(EmailDTO emailDto);
	
}
