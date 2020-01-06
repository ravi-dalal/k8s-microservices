package com.example.customer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.customer.configuration.BankServiceConfiguration;

@RestController
public class CustomerController {
	
	@Autowired
	BankServiceConfiguration bankServiceConfiguration;
	
	@RequestMapping("/customers/{id}")
	public String getCustomerDetails(@PathVariable String id) {
		return "Customer-"+id;
	}

	@RequestMapping("/customers/{id}/bank")
	public String getCustomerBankDetails(@PathVariable String id) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", id);
		String bankServiceUrl = (bankServiceConfiguration.getId() != null && !bankServiceConfiguration.getId().isEmpty()) 
									? bankServiceConfiguration.getId() 
									: bankServiceConfiguration.getHost()+":"+bankServiceConfiguration.getPort();
		ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(
				"http://"+bankServiceUrl+"/banks/customer/{id}", String.class, uriVariables);
		return "Bank of Customer-"+id+" is: "+responseEntity.getBody();
	}

}
