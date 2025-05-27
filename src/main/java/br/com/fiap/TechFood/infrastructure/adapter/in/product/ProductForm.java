package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.core.domain.product.vo.ProductImage;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.List;

public record ProductForm(
        @NotBlank String name,
        @NotBlank String category,
        @NotNull BigDecimal price,
        @NotBlank String description,
        @NotNull List<@Valid ProductImageForm> images) {

    public Product toProduct() {
        return new Product(name,
                ProductCategory.findByName(category),
                price,
                description,
                images.stream().map(ProductImageForm::toProductImage).toList());
    }

    record ProductImageForm(@NotBlank @URL String url,
                            @NotBlank String description,
                            @Min(1) int position) {

        private ProductImage toProductImage() {
            return new ProductImage(url, description, position);
        }
    }
}