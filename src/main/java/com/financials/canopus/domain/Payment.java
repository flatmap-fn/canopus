package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreatePaymentRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "payments")
public class Payment extends Domain {
    private Integer amount;

    private String currency;

    public static Payment fromRequest(CreatePaymentRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, Payment.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("py_");
    }
}
