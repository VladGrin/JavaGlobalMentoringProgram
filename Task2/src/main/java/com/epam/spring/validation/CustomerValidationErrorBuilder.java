package com.epam.spring.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerValidationErrorBuilder {

    public static CustomerValidationError fromBindingsErrors(Errors errors) {
        CustomerValidationError error = new CustomerValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}
