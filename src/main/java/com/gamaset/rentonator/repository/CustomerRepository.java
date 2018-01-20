package com.gamaset.rentonator.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gamaset.rentonator.repository.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
