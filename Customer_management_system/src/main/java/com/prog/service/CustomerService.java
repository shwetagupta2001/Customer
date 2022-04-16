package com.prog.service;

import java.util.List;

import com.prog.entity.Customer;




public interface CustomerService {
	
	List<Customer> getAllCustomers();

	Customer saveCustomer(Customer customer);
	
	Customer getCustomerById(Long id);

}
