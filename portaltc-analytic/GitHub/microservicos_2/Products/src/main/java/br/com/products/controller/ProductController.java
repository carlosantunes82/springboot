package br.com.products.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.products.dto.EmailDTO;
 
@RestController
@RequestMapping("/products")
public class ProductController {
 
    @GetMapping
    public String findAll() {
    	return "products";
    }
    
    @RequestMapping(path = "/sendEmail", method = RequestMethod.POST)
    public String sendMail(@Valid @RequestBody EmailDTO emailDto) {
    	
    	return "";
    }
}