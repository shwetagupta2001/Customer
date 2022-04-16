package com.prog.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.prog.entity.Customer;

import com.prog.repository.CustomerRepository;
import com.prog.service.CustomerService;



@Service
@CacheConfig(cacheNames={"Customers"})
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerRepository customerRepository;
	
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		super();
		this.customerRepository = customerRepository;
		
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		
		return customerRepository.save(customer);
	}
	
	
	@Override
	@Cacheable(key="#id")
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).get();
	}


}
