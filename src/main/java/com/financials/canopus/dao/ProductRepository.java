package com.financials.canopus.dao;

import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Product findByExternalId(String externalId);
}
