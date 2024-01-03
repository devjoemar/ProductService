package com.devjoemar.product.controller;


import com.devjoemar.product.api.response.ApiResponse;
import com.devjoemar.product.dto.ProductDto;
import com.devjoemar.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductDto>> doAddProduct(@RequestBody ProductDto productDto) {
        final ApiResponse<ProductDto> response = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductDto>>> doFindAllProducts() {
       final ApiResponse<List<ProductDto>> response =  productService.findAll();
       return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ApiResponse<ProductDto>> doFindByProductId(@PathVariable("product-id") String productId) {
        final ApiResponse<ProductDto>  response = productService.findById(productId);
        return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }

    @DeleteMapping("/{product-id}")
    public ResponseEntity<ApiResponse<Void>> doDeleteProductById(@PathVariable("product-id") String productId) {
        final ApiResponse<Void> response = productService.delete(productId);
        return ResponseEntity.status(HttpStatus.valueOf(response.getHttpStatus()))
                .body(response);
    }
}
