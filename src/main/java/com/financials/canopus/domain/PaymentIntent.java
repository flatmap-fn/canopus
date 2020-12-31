package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreatePaymentIntentRequest;
import com.financials.canopus.domain.views.CreateRefundRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "payment_intents")
public class PaymentIntent extends Domain {

    private Integer amount;

    private String currency;

    private String customer;

    private String description;

    private String shipping;


    public static PaymentIntent fromRequest(CreatePaymentIntentRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, PaymentIntent.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("pi_");
    }
}

