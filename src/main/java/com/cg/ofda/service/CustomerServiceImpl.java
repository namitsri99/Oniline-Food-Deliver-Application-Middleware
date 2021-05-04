package com.cg.ofda.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ofda.entity.CustomerEntity;
import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.CustomerModel;
import com.cg.ofda.repository.ICustomerRepository;
import com.cg.ofda.util.EMParserCustomer;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepo;

	@Autowired
	private EMParserCustomer parser;

	public CustomerServiceImpl() {
		this.parser = new EMParserCustomer();
	}

	/*
	 * Parameterized for assigning
	 */

	public CustomerServiceImpl(ICustomerRepository customerRepo) {
		super();
		this.customerRepo = customerRepo;
		this.parser = new EMParserCustomer();
	}

	/*
	 * Implementation of addCustomer method to add a customer
	 */

	@Transactional
	@Override
	public CustomerModel addCustomer(CustomerModel customer) throws OFDAException {
		if (customer != null) {
			if (customerRepo.existsById(customer.getCustomerId())) {
				throw new OFDAException("Customer with this id already exists");
			}

			
				customer = parser.parse(customerRepo.save(parser.parse(customer)));	
		}

		return customer;
	}

	/*
	 * Implementation of updateCustomer method to update existing customer
	 */

	@Transactional
	@Override
	public CustomerModel updateCustomer(CustomerModel customer) throws OFDAException {
		CustomerEntity oldCustomer = customerRepo.findById(customer.getCustomerId()).orElse(null);
		if (oldCustomer == null) {
			throw new OFDAException("no customer with id #" + customer.getCustomerId() + " present");
		} else {
	
			customer = parser.parse(customerRepo.save(parser.parse(customer)));
		}
		return customer;
	}

	/*
	 * Implementation of removeCustomer method to remove existing customer
	 */

	@Transactional
	@Override
	public boolean removeCustomer(Long customerId) throws OFDAException {
		CustomerEntity oldCustomer = customerRepo.findById(customerId).orElse(null);
		boolean isDeleted = false;
		if (oldCustomer == null) {
			throw new OFDAException("no customer with id #" + customerId + " present");
		} else {
			customerRepo.deleteById(customerId);
			isDeleted = true;
		}
		return isDeleted;
	}

	/*
	 * Implementation of findCustomer method to view a customer
	 */

	@Override
	public CustomerModel findCustomer(Long id) throws OFDAException {
		CustomerEntity oldCustomer = customerRepo.findById(id).orElse(null);
		if (oldCustomer == null) {
			throw new OFDAException("no customer with id #" + id + " present");
		}
		return parser.parse(customerRepo.findById(id).orElse(null));
	}

	/*
	 * Implementation of findAllCustomer method to view all the customers
	 */

	@Override
	public List<CustomerModel> findAllCustomer() throws OFDAException {

		return customerRepo.findAll().stream().map(parser::parse).collect(Collectors.toList());

	}

}
