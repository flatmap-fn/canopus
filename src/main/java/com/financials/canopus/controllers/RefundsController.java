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
import com.financials.canopus.dao.PaymentRepository;
import com.financials.canopus.dao.RefundRepository;
import com.financials.canopus.domain.Payment;
import com.financials.canopus.domain.Refund;
import com.financials.canopus.domain.RefundStatus;
import com.financials.canopus.domain.views.CreateRefundRequest;
import com.financials.canopus.domain.views.UpdateRefundRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class RefundsController {
    @Autowired
    private RefundRepository refundRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @ApiOperation (value = "Create a refund")
    @PostMapping ("/refunds")
    public @ResponseBody
    Refund createRefund(@RequestBody CreateRefundRequest request) {
        Payment payment = paymentRepository.findByExternalId(request.getPayment());
        Refund refund = Refund.fromRequest(request);
        refund.setCurrency(payment.getCurrency());
        refund.setStatus(RefundStatus.succeeded);
        return refundRepository.save(refund);
    }

    @ApiOperation (value = "List all refunds")
    @GetMapping ("/refunds")
    public @ResponseBody
    Iterable<Refund> listRefunds() {
        return refundRepository.findAll();
    }

    @ApiOperation (value = "Retrieve the details of a refund")
    @GetMapping ("/refunds/{id}")
    public @ResponseBody
    Refund getRefund(@PathVariable String id) {
        return refundRepository.findByExternalId(id);
    }

    @ApiOperation (value = "Update a refund")
    @PutMapping ("/refunds/{id}")
    public Refund updateRefund(@PathVariable String id, @RequestBody UpdateRefundRequest request) {
        Refund refund = refundRepository.findByExternalId(id);
        refund.setAmount(request.getAmount());
        refund.setPayment(request.getPayment());
        refund.setPaymentIntent(request.getPaymentIntent());
        refund.setReason(request.getReason());
        return refundRepository.save(refund);
    }

}
