package com.financials.canopus.domain;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.financials.canopus.utils.IDUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "payments")
public class Payment {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    private Integer amount;

    private String currency;

    @JsonProperty (value = "id")
    private String externalId;

    @JsonProperty(value = "created_at")
    private Timestamp createdAt;

    @JsonProperty(value = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
        this.externalId = IDUtils.generateExternalId("py_");
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt =  new Timestamp(System.currentTimeMillis());
    }
}
