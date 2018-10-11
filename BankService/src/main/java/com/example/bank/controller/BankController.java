package com.example.bank.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankController {
	
	@RequestMapping("banks/customer/{id}")
	public String getCustomerDetails(@PathVariable String id) {
		return "Bank-"+id;
	}

}
