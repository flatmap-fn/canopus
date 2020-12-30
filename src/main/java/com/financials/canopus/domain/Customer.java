package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreateCustomerRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "customers")
public class Customer extends Domain{

    private String address;

    private String description;

    private String name;

    private String paymentMethod;

    private String phone;

    private String shipping;

    public static Customer fromRequest(CreateCustomerRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, Customer.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("cus_");
    }
}
