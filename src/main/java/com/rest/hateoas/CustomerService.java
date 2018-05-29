package com.rest.hateoas;


import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	public Customer getCustomerDetail(String customerId) {
		Customer customer=new Customer();
		customer.setCustomerId(customerId);
		customer.setCustomerName("Jackson");
		customer.setCompanyName("OBSS");
		return customer;
	}
	

}
