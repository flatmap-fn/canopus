package com.financials.canopus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.financials.canopus.dao.PaymentIntentRepository;
import com.financials.canopus.domain.PaymentIntent;
import com.financials.canopus.domain.views.CreatePaymentIntentRequest;
import com.financials.canopus.domain.views.UpdatePaymentIntentRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class PaymentIntentsController {
    @Autowired
    private PaymentIntentRepository paymentIntentRepository;

    @ApiOperation (value = "Create a payment intent")
    @PostMapping ("/payment_intents")
    public @ResponseBody
    PaymentIntent createPaymentIntent(@RequestBody CreatePaymentIntentRequest request) {
        return paymentIntentRepository.save(PaymentIntent.fromRequest(request));
    }

    @ApiOperation (value = "List all payment intents")
    @GetMapping ("/payment_intents")
    public @ResponseBody
    Iterable<PaymentIntent> listPaymentIntent() {
        return paymentIntentRepository.findAll();
    }

    @ApiOperation (value = "Retrieve the details of a payment intent")
    @GetMapping ("/payment_intents/{id}")
    public @ResponseBody
    PaymentIntent getPaymentIntent(@PathVariable String id) {
        return paymentIntentRepository.findByExternalId(id);
    }

    @ApiOperation (value = "Update a payment intent")
    @PutMapping ("/payment_intents/{id}")
    public PaymentIntent updatePaymentIntent(@PathVariable String id, @RequestBody UpdatePaymentIntentRequest request) {
        PaymentIntent paymentIntent = paymentIntentRepository.findByExternalId(id);
        paymentIntent.setAmount(request.getAmount());
        paymentIntent.setCurrency(request.getCurrency());
        paymentIntent.setCustomer(request.getCustomer());
        paymentIntent.setDescription(request.getDescription());
        paymentIntent.setShipping(request.getShipping());
        return paymentIntentRepository.save(paymentIntent);
    }

}
