package com.devjoemar.product.service;

import com.devjoemar.product.api.response.ApiResponse;
import com.devjoemar.product.dto.ProductDto;
import com.devjoemar.product.entity.Product;
import com.devjoemar.product.entity.repo.ProductRepository;
import com.devjoemar.product.util.Constant;
import com.devjoemar.product.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ApiResponse<ProductDto> addProduct(final ProductDto productDto) {

        final var product = Product.builder()
                .sku(productDto.sku())
                .name(productDto.name())
                .description(productDto.description())
                .pricing(productDto.pricing())
                .manufactureDetails(productDto.manufactureDetails())
                .shippingDetails(productDto.shippingDetails())
                .description(productDto.description())
                .build();

        final var model =  productRepository.insert(product);

        final var responseDto = Utils.convertToProductDto(model);

        return ApiResponse.ok(responseDto,
                Constant.getResponseHashMap(),
                Constant.RESPONSE_CODE_PREFIX.concat("7"));
    }

    public ApiResponse<ProductDto> findById(final String id) {

        final var productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
          return ApiResponse.notFound(null,
                  Constant.getResponseHashMap(),
                  Constant.RESPONSE_CODE_PREFIX.concat("1"));
        }

        final var model = productOptional.get();

        final var responseDto = Utils.convertToProductDto(model);

        return ApiResponse.ok(responseDto,
                Constant.getResponseHashMap(),
                Constant.RESPONSE_CODE_PREFIX.concat("6"));

    }

    public ApiResponse<List<ProductDto>> findAll() {
        final var productList =  productRepository.findAll();

        if (productList.isEmpty()) {
            return ApiResponse.notFound(null,
                    Constant.getResponseHashMap(),
                    Constant.RESPONSE_CODE_PREFIX.concat("1"));
        }

        final var productDtos = productList.stream()
                .map(Utils::convertToProductDto)
                .collect(Collectors.toList());

        return ApiResponse.ok(productDtos,
                Constant.getResponseHashMap(),
                Constant.RESPONSE_CODE_PREFIX.concat("6"));
    }

    public ApiResponse<Void> delete(String id) {
        final var productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            return ApiResponse.notFound(null,
                    Constant.getResponseHashMap(),
                    Constant.RESPONSE_CODE_PREFIX.concat("1"));
        }

        productRepository.deleteById(id);

        return ApiResponse.ok(null,
                Constant.getResponseHashMap(),
                Constant.RESPONSE_CODE_PREFIX.concat("9"));
    }
}
