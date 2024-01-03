package com.devjoemar.product.util;

import com.devjoemar.product.dto.ProductDto;
import com.devjoemar.product.entity.Product;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static String convertLocalDateToString(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(formatter);
    }

    public static LocalDate convertStringToLocalDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(string, formatter);
    }
    
    public static ProductDto convertToProductDto(final Product product) {
       return new ProductDto(
                product.getId(),
                product.getSku(),
                product.getName(),
                product.getDescription(),
                product.getManufactureDetails(),
                product.getShippingDetails(),
                product.getPricing(),
                product.getDateTimeCreated(),
                product.getDateTimeUpdated());
    }
}