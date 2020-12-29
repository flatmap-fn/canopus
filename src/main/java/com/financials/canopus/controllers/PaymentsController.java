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
import com.financials.canopus.dao.PaymentRepository;
import com.financials.canopus.domain.Payment;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class PaymentsController {
    @Autowired
    private PaymentRepository paymentRepository;

    @ApiOperation (value = "Create a payment")
    @PostMapping ("/payments")
    public @ResponseBody
    Payment createPayment(@RequestBody Payment payment) {
        return paymentRepository.save(payment);
    }

    @ApiOperation(value = "List all payments")
    @GetMapping ("/payments")
    public @ResponseBody Iterable<Payment> listPayments() {
        return paymentRepository.findAll();
    }

    @ApiOperation(value = "Retrieve the details of a payment")
    @GetMapping ("/payments/{id}")
    public @ResponseBody Payment getPayment(@PathVariable String id) {
        return paymentRepository.findByExternalId(id);
    }

    @ApiOperation(value = "Delete a payment")
    @DeleteMapping ("/payments/{id}")
    public void deletePayment(@PathVariable String id) {
        Payment payment = paymentRepository.findByExternalId(id);
        paymentRepository.delete(payment);
    }
}
