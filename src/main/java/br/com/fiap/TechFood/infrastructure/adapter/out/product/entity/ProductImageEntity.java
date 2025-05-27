package br.com.fiap.TechFood.infrastructure.adapter.out.product.entity;

import br.com.fiap.TechFood.application.core.domain.product.vo.ProductImage;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Embeddable
public class ProductImageEntity {

    @NotBlank
    @URL
    private String url;

    @NotBlank
    private String description;

    @Min(1)
    private int position;

    @Deprecated
    public ProductImageEntity() {}

    public ProductImageEntity(String url, String description, int position) {
        this.url = url;
        this.description = description;
        this.position = position;
    }

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
}
