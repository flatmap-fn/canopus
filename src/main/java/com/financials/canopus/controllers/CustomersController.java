package com.financials.canopus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.financials.canopus.dao.CustomerRepository;
import com.financials.canopus.dao.PaymentRepository;
import com.financials.canopus.domain.Customer;
import com.financials.canopus.domain.Payment;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class CustomersController {
    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation (value = "Create a customer")
    @PostMapping ("/customers")
    public @ResponseBody
    Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @ApiOperation(value = "List all customers")
    @GetMapping ("/customers")
    public @ResponseBody Iterable<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @ApiOperation(value = "Retrieve the details of a customer")
    @GetMapping ("/customers/{id}")
    public @ResponseBody Customer getPayment(@PathVariable String id) {
        return customerRepository.findByExternalId(id);
    }

    @ApiOperation(value = "Delete a customer")
    @DeleteMapping ("/customers/{id}")
    public void deletePayment(@PathVariable String id) {
        Customer customer = customerRepository.findByExternalId(id);
        customerRepository.delete(customer);
    }
}
