package com.prog.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.prog.entity.Customer;
import com.prog.entity.CustomerGroup;
import com.prog.service.CustomerGroupService;
import com.prog.service.CustomerService;





@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerGroupService customerGroupService;
	
	List<String> genderList;
	@ModelAttribute
	public void gender(Model model){
	  genderList = new ArrayList<>();
	  genderList.add("Male");
	  genderList.add("Female");
	 
	}
	
	List<String> geographyList;
	@ModelAttribute
	public void geography(Model model){
	  geographyList = new ArrayList<>();
	  geographyList.add("Urban");
	  geographyList.add("Suburban");
	  geographyList.add("Rural");
	 
	}
	
	List<String> householdList;
	@ModelAttribute
	public void household(Model model){
	  householdList = new ArrayList<>();
	  householdList.add("Individual");
	  householdList.add("Family");
	 
	}
	
	@GetMapping("/customers")
	public String listStudents(Model model) {
		model.addAttribute("customers", customerService.getAllCustomers());
		return "customers";
	}
	
	
	@GetMapping("/customers/new")
	public String createStudentForm(Model model) {
		
		model.addAttribute("genderList", genderList);
		model.addAttribute("householdList", householdList);
		model.addAttribute("geographyList", geographyList);
		
		// create student object to hold student form data
		Customer customer = new Customer();
		model.addAttribute("customer", customer);
		return "create_customer";
		
	}
	
	@PostMapping("/customers")
	public String saveStudent(@Valid @ModelAttribute("customer") Customer customer,BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "create_customer";
        } 
        
            // TODO set the flag as false in the stat
		customer.setIsGrouped(false);
		customerService.saveCustomer(customer);
		return "redirect:/customers";
	}
	
	@Scheduled(cron = "0 0/5 * * * ?")
	public void updateCustomer() {
		System.out.println("Updated");
		
		List<Customer> customers = customerService.getAllCustomers();
		for(Customer c:customers) {
			if(c.getIsGrouped()==false) {
				
				groupCustomerGender(c.getId());
				groupCustomerAge(c.getId());
				groupCustomerHousehold(c.getId());
				groupCustomerIncome(c.getId());
				groupCustomerGeography(c.getId());
			}
		}
	}
	
	public void groupCustomerAge(Long id) {
		Customer existingCustomer = customerService.getCustomerById(id);
		Set<CustomerGroup> c = existingCustomer.getCustomerGroup();
		System.out.println("age");
		if (existingCustomer.getAge()>0 && existingCustomer.getAge()<18){
			c.add(customerGroupService.getCustomerGroupBygroupName("Age(0-18)"));
			existingCustomer.setCustomerGroup(c);
			customerService.saveCustomer(existingCustomer);
		}else if(existingCustomer.getAge()>18 && existingCustomer.getAge()<60){
			c.add(customerGroupService.getCustomerGroupBygroupName("Age(19-60)"));
			existingCustomer.setCustomerGroup(c);
			customerService.saveCustomer(existingCustomer);
		}else {
			c.add(customerGroupService.getCustomerGroupBygroupName("Age(60+)"));
			existingCustomer.setCustomerGroup(c);
			customerService.saveCustomer(existingCustomer);
		}
	}
	
	public void groupCustomerGender(Long id) {
		System.out.println("gender");
		Customer existingCustomer = customerService.getCustomerById(id);
		Set<CustomerGroup> a = existingCustomer.getCustomerGroup();
		if (existingCustomer.getGender().equals("Male")) {
			a.add(customerGroupService.getCustomerGroupBygroupName("Gender(Male)"));
			existingCustomer.setCustomerGroup(a);
			customerService.saveCustomer(existingCustomer);
		}else {
			a.add(customerGroupService.getCustomerGroupBygroupName("Gender(Female)"));
			existingCustomer.setCustomerGroup(a);
			customerService.saveCustomer(existingCustomer);
		}
	}
	
	public void groupCustomerGeography(Long id) {
		System.out.println("geog");
		
		Customer existingCustomer = customerService.getCustomerById(id);
		existingCustomer.setIsGrouped(true);
		Set<CustomerGroup> b = existingCustomer.getCustomerGroup();
		if (existingCustomer.getGeography().equals("Urban")){
			b.add(customerGroupService.getCustomerGroupBygroupName("Geography(Urban)"));
			existingCustomer.setCustomerGroup(b);
			customerService.saveCustomer(existingCustomer);
		}else if(existingCustomer.getGeography().equals("Suburban")){
			b.add(customerGroupService.getCustomerGroupBygroupName("Geography(Suburban)"));
			existingCustomer.setCustomerGroup(b);
			customerService.saveCustomer(existingCustomer);
		}else {
			b.add(customerGroupService.getCustomerGroupBygroupName("Geography(Rural)"));
			existingCustomer.setCustomerGroup(b);
			customerService.saveCustomer(existingCustomer);
		}	
		existingCustomer.setIsGrouped(true);
	}
	
	public void groupCustomerHousehold(Long id) {
		Customer existingCustomer = customerService.getCustomerById(id);
		Set<CustomerGroup> e = existingCustomer.getCustomerGroup();
		System.out.println("ind");
		if (existingCustomer.getHousehold().equals("Individual")) {
			e.add(customerGroupService.getCustomerGroupBygroupName("Household(Individual)"));
			existingCustomer.setCustomerGroup(e);
			customerService.saveCustomer(existingCustomer);
		}else {
			e.add(customerGroupService.getCustomerGroupBygroupName("Household(Family)"));
			existingCustomer.setCustomerGroup(e);
			customerService.saveCustomer(existingCustomer);
		}
		
	}
	
	public void groupCustomerIncome(Long id) {
		Customer existingCustomer = customerService.getCustomerById(id);
		Set<CustomerGroup> d = existingCustomer.getCustomerGroup();
		System.out.println("inc");
		if (existingCustomer.getIncome()>0 && existingCustomer.getIncome()<40000){
			d.add(customerGroupService.getCustomerGroupBygroupName("Income(0-40000)"));
			existingCustomer.setCustomerGroup(d);
			customerService.saveCustomer(existingCustomer);
		}else if(existingCustomer.getIncome()>40000 && existingCustomer.getIncome()>60000){
			d.add(customerGroupService.getCustomerGroupBygroupName("Income(40000-60000)"));
			existingCustomer.setCustomerGroup(d);
			customerService.saveCustomer(existingCustomer);
		}else {
			d.add(customerGroupService.getCustomerGroupBygroupName("Income(60000+)"));
			existingCustomer.setCustomerGroup(d);
			customerService.saveCustomer(existingCustomer);
		}
		
	}
	
	@GetMapping("/customers/show/{id}")
	public String getStudent(@PathVariable Long id, Model model) {
		model.addAttribute("customers", customerService.getCustomerById(id));
		return "customers";
	}
}
