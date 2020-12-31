package com.financials.canopus.dao;

import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.Price;
import com.financials.canopus.domain.Product;



public interface PriceRepository extends CrudRepository<Price, Integer> {
    Price findByExternalId(String externalId);
}