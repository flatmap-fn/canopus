package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreateAccountRequest;
import com.financials.canopus.domain.views.CreatePriceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "accounts")
public class Account extends Domain {
    @Enumerated(EnumType.STRING)
    private AccountType type;

    private String country;

    private String email;

    @JsonProperty (value = "business_type")
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;


    public static Account fromRequest(CreateAccountRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, Account.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("acct_");
    }
}
