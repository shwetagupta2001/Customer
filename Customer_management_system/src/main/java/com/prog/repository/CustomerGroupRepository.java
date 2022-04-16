package com.prog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prog.entity.CustomerGroup;



public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Long>{

	CustomerGroup findBygroupName(String groupName);

	

}
