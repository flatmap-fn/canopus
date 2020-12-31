package com.financials.canopus.dao;

import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.PaymentIntent;

public interface PaymentIntentRepository extends CrudRepository<PaymentIntent, Integer> {
    PaymentIntent findByExternalId(String externalId);
}
