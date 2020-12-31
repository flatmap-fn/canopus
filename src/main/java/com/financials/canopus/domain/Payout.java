package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreatePayoutRequest;
import com.financials.canopus.domain.views.CreatePriceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "payouts")
public class Payout extends Domain {

    private Integer amount;

    private String currency;

    private String description;

    private String statementDescriptor;

    @Enumerated(EnumType.STRING)
    private PayoutStatus status = PayoutStatus.in_transit;

    private String originalPayout;

    public static Payout fromRequest(CreatePayoutRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, Payout.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("po_");
    }
}
