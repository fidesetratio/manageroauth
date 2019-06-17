package com.app.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.ClientDetails;
import com.app.model.OauthUsers;
import com.app.repo.UserDetailRepo;

@RestController
@RequestMapping("/manager")
public class UserDetailRest {
	
	@Autowired
	private UserDetailRepo userDetailRepo;
	
	
	@RequestMapping(value="/listusers",method=RequestMethod.GET)
	public ResponseEntity<List<OauthUsers>> listUsers(){
		List<OauthUsers> list = new ArrayList<OauthUsers>();
		list = (List<OauthUsers>) userDetailRepo.findAll();
		return new ResponseEntity<List<OauthUsers>>(list,HttpStatus.OK);

	}
	
}
