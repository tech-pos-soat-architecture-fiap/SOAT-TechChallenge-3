package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.core.domain.product.vo.ProductImage;
import br.com.fiap.TechFood.infrastructure.adapter.in.validation.ValidCategory;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductForm(
        @NotBlank String name,
        @ValidCategory String category,
        @NotNull BigDecimal price,
        @NotBlank String description,
        @NotNull Set<@Valid ProductImageForm> images) {

    public Product toProduct() {
        return new Product(name,
                category,
                price,
                description,
                images.stream().map(ProductImageForm::toProductImage).collect(Collectors.toSet()));
    }

    record ProductImageForm(@URL String url,
                            @NotBlank String description,
                            @Min(1) int position) {

        private ProductImage toProductImage() {
            return new ProductImage(url, description, position);
        }
    }
}