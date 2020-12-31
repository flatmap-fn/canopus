package com.financials.canopus.domain.views;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRefundRequest {
    @NotNull
    private Integer amount;

    @NotNull
    private String payment;

    @NotNull
    private String paymentIntent;

    @NotNull
    private String reason;
}
