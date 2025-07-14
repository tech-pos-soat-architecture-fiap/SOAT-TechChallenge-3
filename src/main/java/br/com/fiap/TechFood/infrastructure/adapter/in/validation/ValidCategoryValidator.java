package br.com.fiap.TechFood.infrastructure.adapter.in.validation;

import br.com.fiap.TechFood.application.core.usecases.product.domain.ProductCategory;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryValidator implements ConstraintValidator<ValidCategory, String> {

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext constraintValidatorContext) {
        if (categoryName == null || categoryName.trim().isEmpty()) return false;
        return ProductCategory.findByName(categoryName).isPresent();
    }
}
