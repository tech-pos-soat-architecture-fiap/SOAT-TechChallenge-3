package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.usecases.product.domain.ImageContract;
import br.com.fiap.TechFood.infrastructure.adapter.in.validation.Unique;
import br.com.fiap.TechFood.infrastructure.adapter.in.validation.ValidCategory;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.entity.ProductEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.util.Set;

public record ProductForm(
        @NotBlank @Unique(entity = ProductEntity.class, field = "name", message = "O nome informado já existe") String name,
        @ValidCategory String category,
        @NotNull BigDecimal price,
        @NotBlank String description,
        @NotNull Set<@Valid CreateProductImageForm> images) implements ProductContractForm {

    @Override
    public String getName() {
        return name.trim();
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Set<? extends ImageContract> getImages() {
        return images;
    }

    record CreateProductImageForm(@URL(message = "A URL deve ser uma imagem válida com extensão .jpg, .jpeg ou .png", regexp = ".*\\.(jpg|jpeg|png)$") String url,
                                  @NotBlank String description,
                                  @Min(1) int position) implements ImageContract {

        @Override
        public String getUrl() {
            return url;
        }

        @Override
        public String getDescription() {
            return description;
        }

        @Override
        public int getPosition() {
            return position;
        }
    }
}