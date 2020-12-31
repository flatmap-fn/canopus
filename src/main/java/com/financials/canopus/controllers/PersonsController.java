package com.financials.canopus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.financials.canopus.dao.AccountRepository;
import com.financials.canopus.dao.PersonRepository;
import com.financials.canopus.domain.Account;
import com.financials.canopus.domain.Customer;
import com.financials.canopus.domain.Person;
import com.financials.canopus.domain.views.CreatePersonRequest;
import com.financials.canopus.domain.views.UpdatePersonRequest;
import com.financials.canopus.domain.views.UpdatePriceRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class PersonsController {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonRepository personRepository;

    @ApiOperation (value = "Create a Create a person")
    @PostMapping ("/accounts/{accountId}/persons")
    public @ResponseBody
    Person createPerson(@PathVariable String accountId, @RequestBody CreatePersonRequest request) {
        Account account = accountRepository.findByExternalId(accountId);
        Person person = Person.fromRequest(request);
        person.setAccount(account.getExternalId());
        return personRepository.save(person);
    }

    @ApiOperation (value = "List all persons")
    @GetMapping ("/accounts/{accountId}/persons")
    public @ResponseBody
    Iterable<Person> listPersons(@PathVariable String accountId) {
        Account account = accountRepository.findByExternalId(accountId);
        return personRepository.findByAccount(account.getExternalId());
    }

    @ApiOperation (value = "Retrieve the details of a person")
    @GetMapping ("/accounts/{accountId}/persons/{id}")
    public @ResponseBody
    Person getPerson(@PathVariable String accountId, @PathVariable String id) {
        Account account = accountRepository.findByExternalId(accountId);
        return personRepository.findByExternalIdAndAccount(id, account.getExternalId());
    }

    @ApiOperation (value = "Update a person")
    @PutMapping ("/accounts/{accountId}/persons/{id}")
    public Person updatePerson(@PathVariable String accountId, @PathVariable String id, @RequestBody UpdatePersonRequest request) {
        Person person = personRepository.findByExternalIdAndAccount(id, accountId);
        person.setAddress(request.getAddress());
        person.setDateOfBirth(request.getDateOfBirth());
        person.setEmail(request.getEmail());
        person.setFirstName(request.getFirstName());
        person.setLastName(request.getLastName());
        person.setIdNumber(request.getIdNumber());
        person.setPhone(request.getPhone());
        person.setRelationship(request.getRelationship());
        return personRepository.save(person);
    }

    @ApiOperation(value = "Delete a person")
    @DeleteMapping ("/accounts/{accountId}/persons/{id}")
    public void deletePerson(@PathVariable String accountId, @PathVariable String id) {
        Person person = personRepository.findByExternalIdAndAccount(id, accountId);
        personRepository.delete(person);
    }

}
