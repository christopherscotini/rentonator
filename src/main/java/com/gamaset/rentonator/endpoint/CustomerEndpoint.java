package com.gamaset.rentonator.endpoint;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gamaset.rentonator.dto.CustomerDTO;
import com.gamaset.rentonator.service.CustomerService;

@RestController
public class CustomerEndpoint {

	@Autowired
	private CustomerService service;

	@GetMapping(value = "/customers", produces = { APPLICATION_JSON_VALUE })
	@ResponseBody
	public List<CustomerDTO> listAll() {
		return service.listCustomers();
	}

	@GetMapping(value = "/customers/{customerId}", produces = { APPLICATION_JSON_VALUE })
	@ResponseBody
	public CustomerDTO get(@PathVariable("customerId") Long customerId) {
		return service.getCustomer(customerId);
	}

	@PostMapping(value = "/customers", consumes = { APPLICATION_JSON_VALUE })
	public void add(@RequestBody CustomerDTO customer) {
		service.addCustomer(customer);
	}

	@PutMapping(value = "/customers/{customerId}", consumes = { APPLICATION_JSON_VALUE })
	public void update(@PathVariable("customerId") Long customerId, @RequestBody CustomerDTO customer) {
		service.updateCustomer(customerId, customer);
	}
}
