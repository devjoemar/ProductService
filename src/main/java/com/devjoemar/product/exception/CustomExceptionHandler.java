package com.devjoemar.product.exception;

import com.devjoemar.product.api.response.ApiResponse;
import com.devjoemar.product.util.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleCustomException(Exception ex) {
        ApiResponse<String> ApiResponse = com.devjoemar.product.api.response.ApiResponse.<String>builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(Constant.RESULT_NOT_OK)
                .message(ex.getMessage())
                .internalCode(Constant.GENERIC_RESPONSE_CODE)
                .data(null)
                .build();

        return new ResponseEntity<>(ApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(Exception ex) {
        ApiResponse<String> ApiResponse = com.devjoemar.product.api.response.ApiResponse.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .status(Constant.RESULT_NOT_OK)
                .message(ex.getMessage())
                .internalCode(Constant.RESPONSE_CODE_PREFIX.concat("11"))
                .data(null)
                .build();

        return new ResponseEntity<>(ApiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}