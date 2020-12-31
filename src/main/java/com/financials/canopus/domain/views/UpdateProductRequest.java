package com.financials.canopus.domain.views;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest {
    @NotNull
    private Boolean active = true;

    @NotNull
    private String description;

    @NotNull
    private String name;

    private String caption;

    private Boolean shippable = true;
}
