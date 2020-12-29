package com.financials.canopus.dao;

import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
    Payment findByExternalId(String externalId);
}
