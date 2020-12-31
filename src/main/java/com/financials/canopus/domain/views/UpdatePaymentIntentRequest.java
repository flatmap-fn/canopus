package com.financials.canopus.domain.views;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePaymentIntentRequest {
    @NotNull
    private Integer amount;

    @NotNull
    private String currency;

    @NotNull
    private String customer;

    private String description;

    private String shipping;
}
