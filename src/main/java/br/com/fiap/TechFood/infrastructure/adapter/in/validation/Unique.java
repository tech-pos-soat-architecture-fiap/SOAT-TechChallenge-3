package br.com.fiap.TechFood.infrastructure.adapter.in.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UniquenessValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface Unique {

    String message() default "O {field} na {entity} deve ser único";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> entity();

    String field();
}
