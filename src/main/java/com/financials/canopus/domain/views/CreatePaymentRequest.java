package com.financials.canopus.domain.views;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePaymentRequest {
    @NotNull
    @Max(value = Integer.MAX_VALUE)
    @Min(value = Integer.MIN_VALUE)
    private Integer amount;

    @NotNull
    private String currency;
}
