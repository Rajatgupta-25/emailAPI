package com.boot.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.email.entities.Email;
import com.boot.email.services.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/sendemail", method = RequestMethod.POST)
	public ResponseEntity<?> sendEmail(@RequestBody Email email){
		boolean res = emailService.sendEmail(email.getSubject(), email.getMessage(), email.getTo());
		if(res) {
			return ResponseEntity.ok("Email sent Successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not sent");
	}
	
}
