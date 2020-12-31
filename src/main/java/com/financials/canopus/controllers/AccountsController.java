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
import com.financials.canopus.domain.Account;
import com.financials.canopus.domain.views.CreateAccountRequest;
import com.financials.canopus.domain.views.UpdateAccountRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class AccountsController {
    @Autowired
    private AccountRepository accountRepository;

    @ApiOperation (value = "Create an account")
    @PostMapping ("/accounts")
    public @ResponseBody
    Account crreateAccount(@RequestBody CreateAccountRequest request) {
        return accountRepository.save(Account.fromRequest(request));
    }

    @ApiOperation (value = "List all accounts")
    @GetMapping ("/accounts")
    public @ResponseBody
    Iterable<Account> listAccounts() {
        return accountRepository.findAll();
    }

    @ApiOperation (value = "Retrieve the details of an account")
    @GetMapping ("/accounts/{id}")
    public @ResponseBody
    Account getAccount(@PathVariable String id) {
        return accountRepository.findByExternalId(id);
    }

    @ApiOperation (value = "Update an account")
    @PutMapping ("/accounts/{id}")
    public Account updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest request) {
        Account account = accountRepository.findByExternalId(id);
        account.setType(request.getType());
        account.setBusinessType(request.getBusinessType());
        account.setCountry(request.getCountry());
        account.setEmail(request.getEmail());
        return accountRepository.save(account);
    }

    @ApiOperation(value = "Delete an account")
    @DeleteMapping("/accounts/{id}")
    public void deleteAccount(@PathVariable String id) {
        Account account = accountRepository.findByExternalId(id);
        accountRepository.delete(account);
    }
}
