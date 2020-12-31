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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.dao.CustomerRepository;
import com.financials.canopus.domain.Customer;
import com.financials.canopus.domain.views.CreateCustomerRequest;
import com.financials.canopus.domain.views.UpdateCustomerRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class CustomersController {
    @Autowired
    private CustomerRepository customerRepository;

    @ApiOperation (value = "Create a customer")
    @PostMapping ("/customers")
    public @ResponseBody
    Customer createCustomer(@RequestBody CreateCustomerRequest request) {
        return customerRepository.save(Customer.fromRequest(request));
    }

    @ApiOperation(value = "List all customers")
    @GetMapping ("/customers")
    public @ResponseBody Iterable<Customer> listCustomers() {
        return customerRepository.findAll();
    }

    @ApiOperation(value = "Retrieve the details of a customer")
    @GetMapping ("/customers/{id}")
    public @ResponseBody Customer getCustomer(@PathVariable String id) {
        return customerRepository.findByExternalId(id);
    }

    @ApiOperation(value = "Update a customer")
    @PutMapping ("/customers/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody UpdateCustomerRequest request) {
        Customer customer = customerRepository.findByExternalId(id);
        customer.setAddress(request.getAddress());
        customer.setDescription(request.getDescription());
        customer.setName(request.getName());
        customer.setPaymentMethod(request.getPaymentMethod());
        customer.setPhone(request.getPhone());
        return customerRepository.save(customer);
    }

    @ApiOperation(value = "Delete a customer")
    @DeleteMapping ("/customers/{id}")
    public void deleteCustomer(@PathVariable String id) {
        Customer customer = customerRepository.findByExternalId(id);
        customerRepository.delete(customer);
    }
}
