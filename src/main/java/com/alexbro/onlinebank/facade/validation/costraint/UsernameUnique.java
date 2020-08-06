package com.alexbro.onlinebank.facade.validation.costraint;

import com.alexbro.onlinebank.facade.validation.UsernameUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameUniqueValidator.class)
public @interface UsernameUnique {

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
