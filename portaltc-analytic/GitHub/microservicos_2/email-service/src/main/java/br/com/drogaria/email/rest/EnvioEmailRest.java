package br.com.drogaria.email.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.drogaria.email.dto.EmailDTO;
import br.com.drogaria.email.service.EmailService;

@RestController
public class EnvioEmailRest {

    @Autowired
    private EmailService emailService;

    @RequestMapping(path = "/sendEmail", method = RequestMethod.POST)
    public String sendMail(@Valid @RequestBody EmailDTO emailDto) {
    	return emailService.enviarEmail(emailDto);
    }
}
