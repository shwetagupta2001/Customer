package com.prog.entity;



import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;



@Entity
@Table(name="customer_group")
public class CustomerGroup implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotEmpty(message = "GroupName can not be empty!!")
	@Column(name = "groupName", nullable = false) 
    private String groupName;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "customerGroup")
	private Set<Customer> group = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    
	 

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Set<Customer> getGroup() {
		return group;
	}

	public void setGroup(Set<Customer> group) {
		this.group = group;
	}

	public CustomerGroup() {
		
	}
	
	public CustomerGroup(Long id,  String groupName) {
		super();
		this.id = id;
		this.groupName = groupName;
	}

	
	
	
	
}