package com.cg.ofda.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.LoginEntity;

import com.cg.ofda.model.LoginModel;
import com.cg.ofda.repository.ILoginRepository;

@Service
public class EMParserLogin {
	@Autowired
	private ILoginRepository loginRepo;

	public EMParserLogin() {
		//default
	}
	
	
	public EMParserLogin(ILoginRepository loginRepo) {
		super();
		this.loginRepo = loginRepo;
	}


	public LoginModel parse(LoginEntity source) {
		return source == null ? null : new LoginModel(source.getUserid(), source.getUserName(), source.getPassword());

	}

	public LoginEntity parse(LoginModel source) {
		return source == null ? null : new LoginEntity(source.getUserid(), source.getUserName(), source.getPassword());
	}
}