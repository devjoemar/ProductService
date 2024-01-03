package com.devjoemar.product.service;

import com.devjoemar.product.dto.ProductDto;
import com.devjoemar.product.entity.ManufactureDetails;
import com.devjoemar.product.entity.Pricing;
import com.devjoemar.product.entity.Product;
import com.devjoemar.product.entity.ShippingDetails;
import com.devjoemar.product.entity.repo.ProductRepository;
import com.devjoemar.product.util.Constant;
import com.devjoemar.product.util.Utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.Month;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    public void setup() {
      MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void clear() {
        productService = null;
        productRepository = null;
    }


    private static ProductDto getProductDto() {
        var shippingDetails = new ShippingDetails(6, 5, 6, 1);
        var createdDate = LocalDateTime.of(2022, Month.OCTOBER, 4, 0, 0, 0);
        var manufacturing = new ManufactureDetails("Model-iPhone12XXX", createdDate);
        var pricing = new Pricing("1800");

        return new ProductDto("",
                "SKU813013",
                "iPhone12",
                "Iphone 12 Pro Max 500gb",
                manufacturing,
                shippingDetails,
                pricing,
                createdDate,
                null);
    }

    @Test
    public void addProduct() {
        var productDto = getProductDto();

        var product =  Product.builder()
                .sku(productDto.sku())
                .name(productDto.name())
                .description(productDto.description())
                .pricing(productDto.pricing())
                .manufactureDetails(productDto.manufactureDetails())
                .shippingDetails(productDto.shippingDetails())
                .description(productDto.description())
                .build();

        var savedProduct =  Product.builder()
                .id("1")
                .sku(productDto.sku())
                .name(productDto.name())
                .description(productDto.description())
                .pricing(productDto.pricing())
                .manufactureDetails(productDto.manufactureDetails())
                .shippingDetails(productDto.shippingDetails())
                .description(productDto.description())
                .build();

        when(productRepository.insert(product)).thenReturn(savedProduct);

        var response = productService.addProduct(productDto);

        assertEquals(response.getHttpStatus(), HttpStatus.OK.value());
        assertEquals(response.getData(), Utils.convertToProductDto(savedProduct));
        assertEquals(response.getInternalCode(), Constant.RESPONSE_CODE_PREFIX.concat("7"));

        verify(productRepository, times(1)).insert(product);
    }


    @Test
    public void findById() {
        final String id = "1";

        var dto = getProductDto();

        var product = new Product();
        product.setId(id);
        product.setName(dto.name());
        product.setManufactureDetails(dto.manufactureDetails());
        product.setDescription(dto.description());
        product.setSku(dto.sku());
        product.setPricing(dto.pricing());
        product.setShippingDetails(dto.shippingDetails());

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        var  response = productService.findById(id);

        assertEquals(response.getHttpStatus(), HttpStatus.OK.value());
        assertEquals(response.getData().id(), id);
        assertEquals(response.getInternalCode(), Constant.RESPONSE_CODE_PREFIX.concat("6"));

        verify(productRepository, times(1)).findById(id);
    }

    @Test
    public void findById_not_exist() {
        final String id = "0";

        when(productRepository.findById(id)).thenReturn(Optional.empty());
        var  response = productService.findById(id);

        assertEquals(response.getHttpStatus(), HttpStatus.NOT_FOUND.value());
        assertNull(response.getData());
        assertEquals(response.getInternalCode(), Constant.RESPONSE_CODE_PREFIX.concat("1"));

        verify(productRepository, times(1)).findById(id);
    }

    @Test
    public void findAll() {
        var dto = getProductDto();

        var product1 = new Product();
        product1.setId("1");
        product1.setName(dto.name());
        product1.setManufactureDetails(dto.manufactureDetails());
        product1.setDescription(dto.description());
        product1.setSku(dto.sku());
        product1.setPricing(dto.pricing());
        product1.setShippingDetails(dto.shippingDetails());

        var product2 = new Product();
        product2.setId("2");
        product2.setName(dto.name());
        product2.setManufactureDetails(dto.manufactureDetails());
        product2.setDescription(dto.description());
        product2.setSku(dto.sku());
        product2.setPricing(dto.pricing());
        product2.setShippingDetails(dto.shippingDetails());

        List<Product> listOfProducts= new ArrayList<>();
        listOfProducts.add(product1);
        listOfProducts.add(product2);


        when(productRepository.findAll()).thenReturn(listOfProducts);

        var response = productService.findAll();

        assertEquals(response.getHttpStatus(), HttpStatus.OK.value());
        assertEquals(response.getData().size(), listOfProducts.size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void findAll_not_exist() {
        when(productRepository.findAll()).thenReturn(Collections.emptyList());
        var  response = productService.findAll();

        assertEquals(response.getHttpStatus(), HttpStatus.NOT_FOUND.value());
        assertNull(response.getData());
        assertEquals(response.getInternalCode(), Constant.RESPONSE_CODE_PREFIX.concat("1"));

        verify(productRepository, times(1)).findAll();
    }


    @Test
    public void delete() {
        final String id = "1";

        var product = new Product();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        var response = productService.delete(id);

        assertEquals(response.getHttpStatus(), HttpStatus.OK.value());
        assertEquals(response.getInternalCode(), Constant.RESPONSE_CODE_PREFIX.concat("9"));
        assertNull(response.getData());

        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    public void delete_if_id_not_exist() {
        final String id = "-1";

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        var response = productService.delete(id);

        assertEquals(response.getHttpStatus(), HttpStatus.NOT_FOUND.value());
        assertEquals(response.getInternalCode(), Constant.RESPONSE_CODE_PREFIX.concat("1"));
        assertNull(response.getData());

        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(0)).deleteById(id);
    }
}