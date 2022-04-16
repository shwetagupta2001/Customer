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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="customers")
public class Customer implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "FirstName can not be empty!!")
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@NotEmpty(message = "LastName can not be empty!!")
	@Column(name = "last_name")
	private String lastName;
	
	@NotEmpty(message = "Gender can not be empty!!")
	@Column(name = "Gender")
	private String Gender;
	
	@Column(name = "email")
	@Email(regexp="^(.+)@(.+)$", message="Invalid email")
	private String email;
	
	@Column(name = "Phone")
	@NotBlank(message="Field should not be empty")
	@Size(min=10,max=10, message="Invalid Phone_no")
	private String Phone;
	
	@Column(name = "Age")
	private Long Age;
	
	
	@Column(name = "Geography")
	@NotEmpty(message = "Geography can not be empty!!")
	private String Geography;
	
	@Column(name = "Income")
	private Long Income;
	
	@Column(name = "Household")
	@NotEmpty(message = "Household can not be empty!!")
	private String Household;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "CustomerGroupMember",
		joinColumns = { @JoinColumn(name = "customer_id")},
		inverseJoinColumns = { @JoinColumn (name = "CustomerGroup_id")})
	private Set<CustomerGroup> customerGroup = new HashSet<>();

	
	private Boolean isGrouped;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}


    public Long getAge() {
		return Age;
	}

	public void setAge(Long age) {
		Age = age;
	}

	public String getGeography() {
		return Geography;
	}

	public void setGeography(String geography) {
		Geography = geography;
	}

	public Long getIncome() {
		return Income;
	}

	public void setIncome(Long income) {
		Income = income;
	}

	public String getHousehold() {
		return Household;
	}

	public void setHousehold(String household) {
		Household = household;
	}

	public Set<CustomerGroup> getCustomerGroup() {
		return customerGroup;
	}

	public void setCustomerGroup(Set<CustomerGroup> customerGroup) {
		this.customerGroup = customerGroup;
	}
	
	

    public Boolean getIsGrouped() {
		return isGrouped;
	}

	public void setIsGrouped(Boolean isGrouped) {
		this.isGrouped = isGrouped;
	}

	public Customer() {
		
	}

	public Customer(Long id,  String firstName, String lastName, String gender,String email, String phone,
			Long age, String geography, Long income, String household, Set<CustomerGroup> customerGroup,
			Boolean isGrouped) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		Gender = gender;
		this.email = email;
		Phone = phone;
		Age = age;
		Geography = geography;
		Income = income;
		Household = household;
		this.customerGroup = customerGroup;
		this.isGrouped = isGrouped;
	}

	
    
	
	

}
