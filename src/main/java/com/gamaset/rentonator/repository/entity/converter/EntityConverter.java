package com.gamaset.rentonator.repository.entity.converter;

import com.gamaset.rentonator.dto.CustomerDTO;
import com.gamaset.rentonator.repository.entity.Customer;

public class EntityConverter {

	public static Customer convert(CustomerDTO dto) {
		Customer entity = new Customer(dto.getId(), dto.getName(), dto.getEmail(), dto.getTaxId());
		return entity;
	}

}
