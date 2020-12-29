package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
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

    @PrePersist
    public void prePersist() {
        super.prePersist("cus_");
    }
}
