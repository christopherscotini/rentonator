package com.gamaset.rentonator.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamaset.rentonator.dto.CustomerDTO;
import com.gamaset.rentonator.dto.converter.DTOConverter;
import com.gamaset.rentonator.repository.CustomerRepository;
import com.gamaset.rentonator.repository.entity.Customer;
import com.gamaset.rentonator.repository.entity.converter.EntityConverter;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	public List<CustomerDTO> listCustomers() {
		return DTOConverter.convertList((List<Customer>) repository.findAll());
	}

	public CustomerDTO getCustomer(Long customerId) {
		return null;
	}

	public void addCustomer(CustomerDTO customerDto) {
		Objects.requireNonNull(customerDto, "customer must not be null");
		Objects.requireNonNull(customerDto.getName(), "customer.name must not be null");
		Objects.requireNonNull(customerDto.getEmail(), "customer.email must not be null");
		Objects.requireNonNull(customerDto.getTaxId(), "customer.taxId must not be null");

		Customer entity = EntityConverter.convert(customerDto);
		repository.save(entity);
	}

	public void updateCustomer(Long customerId, CustomerDTO customer) {
		
	}

}
