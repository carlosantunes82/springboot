package br.com.customers.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.customers.dto.EmailDTO;
 
@RestController
@RequestMapping("/customers")
public class CustomerController {
 
	@Value("${spring.application.name}")
    private String appName;
    
    @Autowired
    private ApplicationContext context;
    
//    @GetMapping("/zuul-teste")
    @GetMapping
    public String getTestZuul() {
    	return "Application Spring Boot : " + appName + ":" + context.getEnvironment().getProperty("local.server.port");
    }
    
    
//	 @Autowired
//	 private RestTemplate restTemplate;
	
    /**
	 * Consuming a service by postForEntity method, this method is exposed as a post
	 * operation if user post a request object(JSON) it will be automatically mapped
	 * to Request parameter.
	 */
	@GetMapping("/consumer")
	public EmailDTO postConsumer() {

		EmailDTO emailDto = new EmailDTO();
		emailDto.setAssunto("assunto2");
		emailDto.setMensagem("mensagem2");
		Set<String> destinatarios = new HashSet<>();
		destinatarios.add("carlos@gmail");
		emailDto.setDestinatarios(destinatarios);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<EmailDTO> response = restTemplate.postForEntity("http://localhost:8080/api/products/sendEmail", emailDto, EmailDTO.class);
		return response.getBody();
	}
	
	
//    @GetMapping
//    public String findAll() {
//    	
//    	return "customers";
//    }
}

