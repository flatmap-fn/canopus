package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreateProductRequest;
import com.financials.canopus.domain.views.CreateRefundRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "refunds")
public class Refund extends Domain {
    private Integer amount;

    private String payment;

    private String currency;

    private String description;

    private String paymentIntent;

    private String reason;

    private RefundStatus status;

    public static Refund fromRequest(CreateRefundRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, Refund.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("ref_");
    }
}

