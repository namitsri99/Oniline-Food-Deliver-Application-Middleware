package com.cg.ofda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.LoginEntity;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.LoginModel;
import com.cg.ofda.repository.ILoginRepository;
import com.cg.ofda.util.EMParserLogin;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginRepository loginRepository;

	@Autowired
	private EMParserLogin parser;

	/*
	 * Implementation of signIn method to signIn a user
	 */

	public LoginServiceImpl() {
		this.parser = new EMParserLogin();
	}

	public LoginServiceImpl(ILoginRepository loginRepository) {
		super();
		this.loginRepository = loginRepository;
		this.parser = new EMParserLogin();
	}

	@Override
	public String signIn(Long id) throws OFDAException {

		LoginEntity logEn = loginRepository.findById(id).orElse(null);
		LoginModel logMod = parser.parse(logEn);

		if (logMod == null)
			throw new OFDAException("Invalid User");

		Long userId = logMod.getUserid();
		String userName = logMod.getUserName();
		String password = logMod.getPassword();

		Long entityUserId = logEn.getUserid();
		String entityUserName = logEn.getUserName();
		String entityPassword = logEn.getPassword();

		if (userId == entityUserId && userName.equals(entityUserName) && password.equals(entityPassword))
			return "Welcome User Login Successfull";

		else
			throw new OFDAException("Couldn't SignIn");
	}

	/*
	 * Implementation of signOut method to signOut a user
	 */

	@Override
	public boolean signOut(LoginModel login) throws OFDAException {
		// implementation is done during front end
		return false;
	}

}