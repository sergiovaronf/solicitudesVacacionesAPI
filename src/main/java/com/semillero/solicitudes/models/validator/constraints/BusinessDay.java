package com.semillero.solicitudes.models.validator.constraints;


import com.semillero.solicitudes.models.validator.BusinessDayValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BusinessDayValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface BusinessDay {
    String message() default "La fecha no es un día hábil en Colombia";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
