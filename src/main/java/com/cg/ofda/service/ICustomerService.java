package com.cg.ofda.service;

import java.util.List;

import com.cg.ofda.exception.OFDAException;
import com.cg.ofda.model.CustomerModel;

public interface ICustomerService {

	/* definition of addCustomer method for adding new customer */
	CustomerModel addCustomer(CustomerModel customer) throws OFDAException;

	/* definition of updateCustomer method for updating customer */
	CustomerModel updateCustomer(CustomerModel customer) throws OFDAException;

	/* definition of removeCustomer method for removing customer */
	public boolean removeCustomer(Long customerid) throws OFDAException;

	/* definition of viewCustomer method for viewing particular customer */
	public CustomerModel findCustomer(Long customerid) throws OFDAException;

	/* definition of viewAllCustomer method for viewing all customers */
	public List<CustomerModel> findAllCustomer() throws OFDAException;

}
