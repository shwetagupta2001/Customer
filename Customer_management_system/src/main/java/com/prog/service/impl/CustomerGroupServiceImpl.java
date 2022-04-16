package com.prog.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.prog.entity.Customer;
import com.prog.entity.CustomerGroup;
import com.prog.repository.CustomerGroupRepository;
import com.prog.service.CustomerGroupService;

@Service
public class CustomerGroupServiceImpl implements CustomerGroupService {
	
	
    private CustomerGroupRepository customerGroupRepository;

    public CustomerGroupServiceImpl(CustomerGroupRepository customerGroupRepository) {
	     super();
	     this.customerGroupRepository = customerGroupRepository;
    }
	
    @Override
	public CustomerGroup getCustomerGroupBygroupName(String groupName) {
		return customerGroupRepository.findBygroupName(groupName);
	}
    
   
}
