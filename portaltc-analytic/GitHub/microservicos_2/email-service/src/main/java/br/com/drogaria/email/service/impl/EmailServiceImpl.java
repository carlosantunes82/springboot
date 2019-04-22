package br.com.drogaria.email.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.com.drogaria.email.dto.EmailDTO;
import br.com.drogaria.email.service.EmailService;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired 
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.from}")
    private String emailFrom;
    
	public String enviarEmail(EmailDTO emailDto) {
		
		String retorno = "Por favor preencha os dados para enviar e-mail.";
		try {
			if(emailDto != null) {
				
				MimeMessage mail = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper( mail );
				if(!CollectionUtils.isEmpty(emailDto.getDestinatarios())){
					for(String enderecoEmail : emailDto.getDestinatarios()){
						helper.addTo(enderecoEmail);
					}
				}
				helper.setSubject(emailDto.getAssunto());
				helper.setText(emailDto.getMensagem(), true);
				helper.setFrom(this.emailFrom);
				mailSender.send(mail);
				
				retorno = "E-mail enviao com sucesso!";
			} 
			
			return retorno;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
	}
}
