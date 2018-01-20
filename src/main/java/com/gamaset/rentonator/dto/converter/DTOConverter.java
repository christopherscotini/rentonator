package com.gamaset.rentonator.dto.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.gamaset.rentonator.dto.CustomerDTO;
import com.gamaset.rentonator.repository.entity.Customer;

public class DTOConverter {

	public static CustomerDTO convert(Customer entity) {
		CustomerDTO dto = new CustomerDTO(entity.getId(), entity.getName(), entity.getEmail(), entity.getTaxId());
		dto.setDateCreated(entity.getCreatedAt());
		dto.setDateUpdated(entity.getUpdatedAt());
		return dto;
	}

	public static List<CustomerDTO> convertList(List<Customer> entities) {
		if (!CollectionUtils.isEmpty(entities)) {
			List<CustomerDTO> dtos = new ArrayList<>();
			entities.forEach(entity -> {
				dtos.add(convert(entity));
			});
			return dtos;
		}
		return null;
	}
	
}
