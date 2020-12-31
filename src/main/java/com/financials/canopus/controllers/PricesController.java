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
import com.financials.canopus.dao.PriceRepository;
import com.financials.canopus.dao.RefundRepository;
import com.financials.canopus.domain.Payment;
import com.financials.canopus.domain.Price;
import com.financials.canopus.domain.Refund;
import com.financials.canopus.domain.RefundStatus;
import com.financials.canopus.domain.views.CreatePriceRequest;
import com.financials.canopus.domain.views.CreateRefundRequest;
import com.financials.canopus.domain.views.UpdatePriceRequest;
import com.financials.canopus.domain.views.UpdateRefundRequest;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping (path = "/v1")
public class PricesController {
    @Autowired
    private PriceRepository priceRepository;

    @ApiOperation (value = "Create a price")
    @PostMapping ("/prices")
    public @ResponseBody
    Price createPrice(@RequestBody CreatePriceRequest request) {
        return priceRepository.save(Price.fromRequest(request));
    }

    @ApiOperation (value = "List all prices")
    @GetMapping ("/prices")
    public @ResponseBody
    Iterable<Price> listPrices() {
        return priceRepository.findAll();
    }

    @ApiOperation (value = "Retrieve the details of a price")
    @GetMapping ("/prices/{id}")
    public @ResponseBody
    Price getPrice(@PathVariable String id) {
        return priceRepository.findByExternalId(id);
    }

    @ApiOperation (value = "Update a price")
    @PutMapping ("/prices/{id}")
    public Price updatePrice(@PathVariable String id, @RequestBody UpdatePriceRequest request) {
        Price price = priceRepository.findByExternalId(id);
        price.setActive(request.getActive());
        price.setCurrency(request.getCurrency());
        price.setNickname(request.getNickname());
        price.setProduct(request.getProduct());
        price.setUnitAmount(request.getUnitAmount());
        return priceRepository.save(price);
    }

}
