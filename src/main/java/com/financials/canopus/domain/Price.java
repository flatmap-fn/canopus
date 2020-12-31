package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreatePriceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "prices")
public class Price extends Domain {
    private String currency;

    private Integer unitAmount;

    private Boolean active;

    private String nickname;

    private String product;

    public static Price fromRequest(CreatePriceRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, Price.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("price_");
    }
}
