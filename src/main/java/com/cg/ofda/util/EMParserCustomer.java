package com.cg.ofda.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.BillEntity;
import com.cg.ofda.entity.CustomerEntity;
import com.cg.ofda.model.BillModel;
import com.cg.ofda.model.CustomerModel;
import com.cg.ofda.repository.IBillRepository;
import com.cg.ofda.repository.ICustomerRepository;

@Service
public class EMParserCustomer {

	@Autowired
	private ICustomerRepository customerRepo;
	
	public EMParserCustomer() {
		this.customerRepo = customerRepo;
	}
	
	
	
	public EMParserCustomer(ICustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
	}



	public CustomerModel parse(CustomerEntity source) {
		return source==null ? null:
			new CustomerModel(source.getCustomerId(),
					source.getFirstName(),
					source.getLastName(),
					source.getGender(),
					source.getAge(),
					source.getMobileNumber(),
					source.getAddress(),
					source.getEmail());
	}
	
	public CustomerEntity parse(CustomerModel source) {
		return source==null ? null:
			new CustomerEntity(source.getCustomerId(),
					source.getFirstName(),
					source.getLastName(),
					source.getGender(),
					source.getAge(),
					source.getMobileNumber(),
					source.getAddress(),
					source.getEmail());
	}
	
	

}