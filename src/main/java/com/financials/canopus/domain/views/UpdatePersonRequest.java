package com.financials.canopus.domain.views;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePersonRequest {
    private String address;

    private Date dateOfBirth;

    private String email;

    private String idNumber;

    private String firstName;

    private String lastName;

    private String phone;

    private String relationship;
}
