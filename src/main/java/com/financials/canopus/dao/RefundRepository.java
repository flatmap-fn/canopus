package com.financials.canopus.dao;

import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.Refund;

public interface RefundRepository extends CrudRepository<Refund, Integer> {
    Refund findByExternalId(String externalId);
}
