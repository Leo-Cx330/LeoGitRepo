package com.leo.security.web.validate;

import lombok.Data;
import org.springframework.validation.ObjectError;

import java.util.List;


@Data
public class ValidateException extends RuntimeException{

    private static final long serialVersionUID = -4033877326715070699L;

    private List<ObjectError> errors;

    public ValidateException() {
    }

    public ValidateException( List<ObjectError> errors) {
        this.errors = errors;
    }
}
