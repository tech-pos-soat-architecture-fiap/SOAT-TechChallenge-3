package br.com.fiap.TechFood.infrastructure.adapter.out.product.entity;

import br.com.fiap.TechFood.application.core.domain.product.vo.ProductImage;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import java.util.Objects;

@Embeddable
public class ProductImageEntity {

    @URL
    private String url;

    @NotBlank
    private String description;

    @Min(1)
    private int position;

    @Deprecated
    public ProductImageEntity() {}

    public ProductImageEntity(ProductImage image) {
        this.url = image.getUrl();
        this.description = image.getDescription();
        this.position = image.getPosition();
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public int getPosition() {
        return position;
    }

    public ProductImage toProductImage() {
        return new ProductImage(this.url, this.description, this.position);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductImageEntity that = (ProductImageEntity) o;
        return position == that.position && Objects.equals(url, that.url) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, description, position);
    }
}
