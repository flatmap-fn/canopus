package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "payments")
public class Payment extends Domain {
    private Integer amount;

    private String currency;

    @PrePersist
    public void prePersist() {
        super.prePersist("py_");
    }
}
