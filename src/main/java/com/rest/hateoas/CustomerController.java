package com.rest.hateoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value = "/customer/{customerId}", method = RequestMethod.GET,produces={"application/json"})
    public Resource<Customer> getCustomerById(@PathVariable String customerId) {
		
		Customer customer = customerService.getCustomerDetail(customerId);
		
		customer.add(ControllerLinkBuilder.linkTo(CustomerController.class).slash("/customer/"+customer.getCustomerId()).withSelfRel());
		customer.add(ControllerLinkBuilder.linkTo(CustomerController.class).slash("/customer/"+customerId).withRel("update"));
		customer.add(ControllerLinkBuilder.linkTo(CustomerController.class).slash("/customer/"+customerId).withRel("delete"));
		customer.add(ControllerLinkBuilder.linkTo(CustomerController.class).slash("/customer/").withRel("create"));
		
		
		Resource<Customer> resource=new Resource<Customer>(customer);
		return resource;
    }

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
