package com.financials.canopus.domain.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financials.canopus.domain.AccountType;
import com.financials.canopus.domain.BusinessType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAccountRequest {
    private AccountType type;

    private String country;

    private String email;

    @JsonProperty(value = "business_type")
    private BusinessType businessType;
}
