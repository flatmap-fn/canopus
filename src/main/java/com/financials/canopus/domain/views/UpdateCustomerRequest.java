package com.financials.canopus.domain.views;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCustomerRequest {
    @NotNull
    private String address;

    private String description;

    private String name;

    private String paymentMethod;

    @NotNull
    private String phone;

    private String shipping;
}
