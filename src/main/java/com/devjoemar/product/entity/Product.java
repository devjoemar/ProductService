package com.devjoemar.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    private String id;

    private String name;

    private String description;

    private String sku;

    private ManufactureDetails manufactureDetails;

    private ShippingDetails shippingDetails;

    private Pricing pricing;

    private LocalDateTime dateTimeCreated;

    private LocalDateTime dateTimeUpdated;

    @DBRef
    private Category category;
}
