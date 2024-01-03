package com.devjoemar.product.dto;

import com.devjoemar.product.entity.ManufactureDetails;
import com.devjoemar.product.entity.Pricing;
import com.devjoemar.product.entity.ShippingDetails;

import java.time.LocalDateTime;
import java.util.Objects;

public record ProductDto(
        String id,
        String sku,
        String name,
        String description,
        ManufactureDetails manufactureDetails,
        ShippingDetails shippingDetails,
        Pricing pricing,
        LocalDateTime dateTimeCreated,
        LocalDateTime dateTimeUpdated
) {
    public ProductDto {
        if (sku.isBlank()) {
            throw new IllegalArgumentException("SKU should not be blank");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("Name should not be blank");
        }

        if (Objects.isNull(manufactureDetails)) {
            throw new IllegalArgumentException("ManufactureDetails is mandatory");
        }
        if (Objects.isNull(shippingDetails)) {
            throw new IllegalArgumentException("ShippingDetails is mandatory");
        }

        if (Objects.isNull(pricing)) {
            throw new IllegalArgumentException("Pricing is mandatory");
        }

    }
}