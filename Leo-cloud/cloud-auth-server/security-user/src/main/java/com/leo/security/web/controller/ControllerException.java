package com.leo.security.web.controller;


import com.leo.security.web.validate.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


@RestControllerAdvice
@Slf4j
public class ControllerException {

    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ObjectError> handlerValidateException(ValidateException validateException) {
        log.error("#####ValidateException#######，{}",validateException);
        return validateException.getErrors();
    }

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerValidateCodeException(SecurityException securityException) {
        log.error("#####SecurityException#######，{}",securityException);
        return securityException.getMessage();
    }




    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handlerValidateCodeException(Exception exception) {
        log.error("#####Exception#######，{}",exception);
        return "ERROR";
    }
}
