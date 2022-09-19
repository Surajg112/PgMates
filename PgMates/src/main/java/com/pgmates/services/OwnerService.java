package com.pgmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.pgmates.dtos.ForgotPasswordDTO;
import com.pgmates.dtos.LoginDTO;
import com.pgmates.models.Owner;
import com.pgmates.repository.OwnerRepository;

@Service
public class OwnerService {

	@Autowired private OwnerRepository orepo;
	@Autowired private AdminService asrv;
	

	
	public List<Owner> listAll(){
		return orepo.findAll();
	}
	
	public boolean checkExist(String userid) {
		return orepo.findByUserid(userid)!=null;
	}
	
	public Owner findByUserId(String userid) {
		return orepo.findByUserid(userid);
	}
	
	public Owner findById(int id) {
		return orepo.getById(id);
		
	}
	
	public void updateStatus(int id) {
		Owner owner= orepo.getById(id);
		owner.setActive(!owner.isActive());
		orepo.save(owner);
	}
	
	public Owner validate(LoginDTO dto) {
		Owner cust=findByUserId(dto.getUserid());
		if(cust!=null && cust.getPwd().equals(asrv.encrypt(dto.getPwd())) && cust.isActive()) {
			return cust;
		}else {
			return null;
		}
	}
}
