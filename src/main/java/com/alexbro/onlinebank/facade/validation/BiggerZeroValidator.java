package com.alexbro.onlinebank.facade.validation;

import com.alexbro.onlinebank.facade.validation.costraint.BiggerZero;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class BiggerZeroValidator implements ConstraintValidator<BiggerZero, Double> {

    @Override
    public boolean isValid(Double sum, ConstraintValidatorContext constraintValidatorContext) {
        if (sum != null) {
            return sum > 0;
        }
        return false;
    }
}
