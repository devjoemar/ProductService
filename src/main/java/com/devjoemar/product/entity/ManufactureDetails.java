package com.devjoemar.product.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public record ManufactureDetails (String modelNumber, LocalDateTime releaseDate){

    public ManufactureDetails {
        if (modelNumber.isBlank()) {
            throw new IllegalArgumentException("Model number should not be blank");
        }

        if (Objects.isNull(releaseDate)) {
            throw new IllegalArgumentException("Release date should not be blank");
        }
    }
}
