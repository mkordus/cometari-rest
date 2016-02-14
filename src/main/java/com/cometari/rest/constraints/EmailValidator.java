package com.cometari.rest.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
public class EmailValidator implements ConstraintValidator<Email, String> {

    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }

        return org.apache.commons.validator.routines.EmailValidator
            .getInstance()
            .isValid(value);
    }
}
