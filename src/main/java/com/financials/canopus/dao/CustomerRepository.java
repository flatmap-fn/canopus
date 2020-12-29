package com.financials.canopus.dao;

import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.Customer;
import com.financials.canopus.domain.Payment;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findByExternalId(String externalId);
}
