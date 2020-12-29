package com.financials.canopus.domain;

import java.sql.Timestamp;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.financials.canopus.utils.IDUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class Domain {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    @JsonProperty (value = "id")
    private String externalId;

    @JsonProperty (value = "created_at")
    private Timestamp createdAt;

    @JsonProperty (value = "updated_at")
    private Timestamp updatedAt;

    public void prePersist(String prefix) {
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());
        this.externalId = IDUtils.generateExternalId(prefix);
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
