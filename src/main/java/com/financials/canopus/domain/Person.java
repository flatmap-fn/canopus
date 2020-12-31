package com.financials.canopus.domain;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreatePersonRequest;
import com.financials.canopus.domain.views.CreatePriceRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "persons")
public class Person extends Domain {
    private String address;

    private Date dateOfBirth;

    private String email;

    private String idNumber;

    private String firstName;

    private String lastName;

    private String phone;

    private String relationship;

    private String account;

    public static Person fromRequest(CreatePersonRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, Person.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("person_");
    }
}
