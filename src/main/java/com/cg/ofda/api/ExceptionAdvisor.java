package com.cg.ofda.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.ofda.exception.BillException;
import com.cg.ofda.exception.CartException;
import com.cg.ofda.exception.CategoryException;
import com.cg.ofda.exception.CustomerException;
import com.cg.ofda.exception.ItemException;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.exception.OrderException;
import com.cg.ofda.exception.RestaurantException;

@RestControllerAdvice
public class ExceptionAdvisor {
	
	/*For OFDA Exception*/
	@ExceptionHandler(OFDAException.class)
	public ResponseEntity<String> handleLoanExceptionAction(OFDAException excep) {
		return new ResponseEntity<>(excep.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	/*For internal server error*/
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleExceptionAction(Exception excep) {
		return new ResponseEntity<>(excep.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
