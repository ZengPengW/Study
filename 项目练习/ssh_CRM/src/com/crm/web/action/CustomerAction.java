package com.crm.web.action;

import com.crm.domain.Customer;
import com.crm.service.CustomerService;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction implements ModelDriven<Customer> {
	private Customer customer=new Customer();
	@Override
	public Customer getModel() {
		return customer;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public String saveUI() {
		return "saveUI";
	}
	
}
