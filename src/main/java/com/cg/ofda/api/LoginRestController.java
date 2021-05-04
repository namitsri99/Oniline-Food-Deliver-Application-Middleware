package com.cg.ofda.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.service.ILoginService;

@RestController
@RequestMapping(path="/login")
public class LoginRestController {
	
	@Autowired
	private ILoginService loginService;

	/*
	 * to login
	 * params : userId
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<String> Login(@PathVariable("userId") Long userId) throws OFDAException {
		
		String ValidUser = loginService.signIn(userId);
		
		ResponseEntity<String> response= new ResponseEntity<String>(ValidUser,HttpStatus.OK);
		
	return response;
	}
}
