package com.financials.canopus.domain.views;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePayoutRequest {
    @NotNull
    @Min(value = 0)
    @Max(value = Integer.MAX_VALUE)
    private Integer amount;

    @NotEmpty
    private String currency;

    private String description;

    private String statementDescriptor;
}
