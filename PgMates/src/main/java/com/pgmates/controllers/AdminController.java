package com.pgmates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgmates.dtos.FeedbackDTO;
import com.pgmates.dtos.LoginDTO;
import com.pgmates.dtos.LoginResponse;
import com.pgmates.models.Admin;
import com.pgmates.models.Customer;
import com.pgmates.models.Owner;
import com.pgmates.services.AdminService;
import com.pgmates.services.CustomerService;
import com.pgmates.services.FeedbackService;
import com.pgmates.services.OwnerService;

@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired private AdminService adminService;
	@Autowired private CustomerService cservice;
	@Autowired private OwnerService oservice;
	@Autowired private FeedbackService fservice;
	
	@GetMapping("feedbacks")
	public ResponseEntity<?> findAll() {		
		return ResponseEntity.ok(fservice.listAll());
	}
	

	
	@PostMapping("feedbacks")
	public ResponseEntity<?> saveFeedback(@RequestBody FeedbackDTO dto) {
		fservice.saveFeedback(dto);
		return ResponseEntity.ok("Feedback submitted");
	}
	

	
	@PostMapping("/validate")
	public ResponseEntity<?> validateUser(@RequestBody LoginDTO dto) {
		System.out.println(dto);
		Customer cust=cservice.validate(dto);
		Owner owner=oservice.validate(dto);
		if(cust!=null) {
			return ResponseEntity.ok(new LoginResponse(cust.getId(), cust.getUserid(), cust.getName(), "Customer"));
		}
		if(owner!=null) {
			return ResponseEntity.ok(new LoginResponse(owner.getId(), owner.getUserid(), owner.getName(), "Owner")); 
		}
		return ResponseEntity.badRequest().body("Invalid username or password");
		
	}
	
	@PostMapping("/avalidate")
	public ResponseEntity<?> avalidateUser(@RequestBody LoginDTO dto) {
		System.out.println(dto);
		Admin admin=adminService.validate(dto.getUserid(),dto.getPwd());
		if(admin!=null) {			
			return ResponseEntity.ok(new LoginResponse(0, admin.getUserid(), admin.getUname(), "Admin"));
		} else {
			return ResponseEntity.badRequest().body("Invalid username or password");
		}
		
	}
	

	
}
