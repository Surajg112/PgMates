package com.pgmates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgmates.dtos.CustomerDTO;
import com.pgmates.services.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired private CustomerService cservice;
	
	//create user
	@PostMapping
	public ResponseEntity<?> save(CustomerDTO dto) {	
		if(cservice.checkExist(dto.getUserid())) {
			return ResponseEntity.badRequest().body("Email already registered");
		}
		cservice.saveCustomer(dto);
		return ResponseEntity.ok("user registered successfully");
	}
	
	//get all user
	@GetMapping
	public ResponseEntity<?> findAll() {		
		return ResponseEntity.ok(cservice.listAll());
	}
	
	//get single user
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") int id) {		
		return ResponseEntity.ok(cservice.findById(id));
	}
	

}
