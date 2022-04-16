package com.prog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

	

}
