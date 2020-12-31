package com.financials.canopus.domain.views;

import java.sql.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePersonRequest {
    private String address;

    private Date dateOfBirth;

    private String email;

    private String idNumber;

    private String firstName;

    private String lastName;

    private String phone;

    private String relationship;
}
