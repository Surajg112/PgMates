package com.pgmates.services;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pgmates.models.Admin;
import com.pgmates.repository.AdminRepository;

@Service
public class AdminService {
	@Autowired AdminRepository dao;
	
	public void saveAdmin(Admin admin) {
		dao.save(admin);
	}

	public Admin validate(String userid, String pwd) {
		// TODO Auto-generated method stub
		Optional<Admin> admin=dao.findById(userid);
		if(admin.isPresent() && admin.get().getPwd().equals(encrypt(pwd))) {
			return admin.get();
		}
		return null;
	}
	


	public void updateAdmin(Admin admin) {
		if(admin.getPwd().equals("") || admin.getPwd()==null) {
			admin.setPwd(dao.getById(admin.getUserid()).getPwd());
		}
		dao.save(admin);		
	}

	public long countAdmin() {
		// TODO Auto-generated method stub
		return dao.count();
	}
	
	public String encrypt(String text) {
		try {
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		byte[] md=messageDigest.digest();
		BigInteger bi=new BigInteger(1,md);
		return bi.toString(16);
		}catch(Exception ex) {
			System.err.println("Error "+ex.getMessage());
		}
		return  null;
	}
}
