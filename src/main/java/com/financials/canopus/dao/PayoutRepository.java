package com.financials.canopus.dao;

import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.Payout;

public interface PayoutRepository extends CrudRepository<Payout, Integer> {
    Payout findByExternalId(String externalId);
}