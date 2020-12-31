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
import com.financials.canopus.dao.PayoutRepository;
import com.financials.canopus.domain.Payout;
import com.financials.canopus.domain.PayoutStatus;
import com.financials.canopus.domain.views.CreatePayoutRequest;
import com.financials.canopus.domain.views.UpdatePayoutRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class PayoutsController {
    @Autowired
    private PayoutRepository payoutRepository;

    @ApiOperation (value = "Create a payout")
    @PostMapping ("/payouts")
    public @ResponseBody
    Payout createPayout(@RequestBody CreatePayoutRequest request) {
        return payoutRepository.save(Payout.fromRequest(request));
    }

    @ApiOperation (value = "List all payouts")
    @GetMapping ("/payouts")
    public @ResponseBody
    Iterable<Payout> listPayouts() {
        return payoutRepository.findAll();
    }

    @ApiOperation (value = "Retrieve the details of a payout")
    @GetMapping ("/payouts/{id}")
    public @ResponseBody
    Payout getPayout(@PathVariable String id) {
        return payoutRepository.findByExternalId(id);
    }

    @ApiOperation (value = "Update a payout")
    @PutMapping ("/payouts/{id}")
    public Payout updatePayout(@PathVariable String id, @RequestBody UpdatePayoutRequest request) {
        Payout payout = payoutRepository.findByExternalId(id);
        payout.setAmount(request.getAmount());
        payout.setCurrency(request.getCurrency());
        payout.setDescription(request.getDescription());
        payout.setStatementDescriptor(request.getStatementDescriptor());
        return payoutRepository.save(payout);
    }

    @ApiOperation (value = "Cancel a payout")
    @PostMapping ("/payouts/{id}/cancel")
    public @ResponseBody
    Payout cancelPayout(@PathVariable String id) {
        Payout payout = payoutRepository.findByExternalId(id);
        payout.setStatus(PayoutStatus.canceled);
        return payoutRepository.save(payout);
    }

    @ApiOperation (value = "Reverse a payout")
    @PostMapping ("/payouts/{id}/reverse")
    public @ResponseBody
    Payout reversePayout(@PathVariable String id) {
        Payout originalPayout = payoutRepository.findByExternalId(id);
        Payout payout = new Payout();
        payout.setAmount(originalPayout.getAmount());
        payout.setCurrency(originalPayout.getCurrency());
        payout.setOriginalPayout(originalPayout.getExternalId());
        payout.setDescription("REVERSED PAYOUT");
        payout.setStatus(PayoutStatus.paid);
        return payoutRepository.save(payout);
    }

}
