package com.cg.ofda.service;

import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.LoginModel;

public interface ILoginService {

	/* definition of signIn method for signing in */
	public String signIn(Long userId) throws OFDAException;

	/* definition of signOut method for signing out */
	public boolean signOut(LoginModel login) throws OFDAException;
}