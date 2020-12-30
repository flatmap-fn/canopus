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
import com.financials.canopus.dao.PaymentRepository;
import com.financials.canopus.domain.Payment;
import com.financials.canopus.domain.views.CreatePaymentRequest;
import com.financials.canopus.domain.views.UpdatePaymentRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class PaymentsController {
    @Autowired
    private PaymentRepository paymentRepository;

    @ApiOperation (value = "Create a payment")
    @PostMapping ("/payments")
    public @ResponseBody
    Payment createPayment(@RequestBody CreatePaymentRequest request) {
        return paymentRepository.save(Payment.fromRequest(request));
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

    @ApiOperation(value = "Update a payment")
    @PutMapping ("/payments/{id}")
    public Payment updatePayment(@PathVariable String id, @RequestBody UpdatePaymentRequest request) {
        Payment payment = paymentRepository.findByExternalId(id);
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        return paymentRepository.save(payment);
    }
}
