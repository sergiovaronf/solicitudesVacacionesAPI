package com.semillero.solicitudes.models.validator;

import com.semillero.solicitudes.models.validator.constraints.BusinessDay;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class BusinessDayValidator implements ConstraintValidator<BusinessDay, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null) {
            return true;
        }
        return localDate.getDayOfWeek() != DayOfWeek.SATURDAY && localDate.getDayOfWeek() != DayOfWeek.SUNDAY;
    }

    @Override
    public void initialize(BusinessDay constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
