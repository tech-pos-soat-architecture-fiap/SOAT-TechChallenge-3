package br.com.fiap.TechFood.infrastructure.adapter.out.product.entity;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.core.domain.product.vo.ProductImage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String description;

    @NotNull
    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    private List<ProductImageEntity> images;

    @Deprecated
    public ProductEntity() {}

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.images = product.getImages().stream().map(ProductImageEntity::new).toList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public List<ProductImageEntity> getImages() {
        return images;
    }

    public List<ProductImage> productImages() {
        return this.images.stream()
                .map(imgEntity -> new ProductImage(imgEntity.getUrl(), imgEntity.getDescription(), imgEntity.getPosition()))
                .toList();
    }

    public Product getProduct() {
        return new Product(
                this.id,
                this.name,
                this.category,
                this.price,
                this.description,
                this.images.stream().map(ProductImageEntity::toProductImage).toList());
    }
}
