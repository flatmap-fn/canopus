package com.financials.canopus.domain;

import javax.persistence.Entity;
import javax.persistence.PrePersist;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financials.canopus.domain.views.CreateProductRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "products")
public class Product extends Domain {
    private Boolean active;

    private String description;

    private String name;

    private String caption;

    private Boolean shippable;

    public static Product fromRequest(CreateProductRequest request) {
        ObjectMapper om = new ObjectMapper();
        return om.convertValue(request, Product.class);
    }

    @PrePersist
    public void prePersist() {
        super.prePersist("prod_");
    }
}
