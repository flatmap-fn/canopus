package com.financials.canopus.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.financials.canopus.domain.Person;
import com.financials.canopus.domain.Price;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    Person findByExternalId(String externalId);
    List<Person> findByAccount(String account);
    Person findByExternalIdAndAccount(String externalId, String account);

}