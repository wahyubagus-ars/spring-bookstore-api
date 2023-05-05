package com.example.demo.exception;

import com.example.demo.constant.AppConstant;
import com.example.demo.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.example.demo.constant.AppConstant.ResponseKey.UNKNOWN_ERROR;
import static com.example.demo.constant.AppConstant.ResponseMessage.UNKNOWN_ERROR_MSG;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class AppExceptionHandler {
    @ExceptionHandler(value = {AppException.class})
    public ResponseEntity<Object> handleApplicationException(AppException ex) {
        log.error("Application exception. Message [{}]", ex.getMessage());

        switch (ex.getResponseKey()) {
            case AppConstant.ResponseKey.DATA_NOT_FOUND:
                return ResponseUtils.buildResponse(ex.getResponseKey(), ex.getResponseMessage(),null, HttpStatus.BAD_REQUEST);
            default:
                return ResponseUtils.buildResponse(UNKNOWN_ERROR, UNKNOWN_ERROR_MSG,null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
