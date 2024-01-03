package com.devjoemar.product.entity;

import java.math.BigDecimal;
import java.util.Objects;

public record Pricing(String price) {

    public Pricing(String price) {
        if (Objects.isNull(price)) {
            throw new IllegalArgumentException("Price is mandatory");
        }

        this.price = new BigDecimal(price).toPlainString();
    }
}
