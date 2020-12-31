package com.financials.canopus.dao;

import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.Account;
import com.financials.canopus.domain.Price;

public interface AccountRepository extends CrudRepository<Account, Integer> {
    Account findByExternalId(String externalId);
}