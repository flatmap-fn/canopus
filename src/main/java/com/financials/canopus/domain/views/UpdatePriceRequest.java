package com.financials.canopus.domain.views;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePriceRequest {
    @NotEmpty
    private String currency;

    @NotNull
    @Max (value = Integer.MAX_VALUE)
    @Min (value = 0)
    private Integer unitAmount;

    private Boolean active = true;

    private String nickname;

    private String product;
}
