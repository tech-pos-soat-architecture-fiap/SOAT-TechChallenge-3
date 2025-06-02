package br.com.fiap.TechFood.infrastructure.adapter.in.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidCategoryValidator.class)
public @interface ValidCategory {

    String message() default "Categoria inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
