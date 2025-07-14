package br.com.fiap.TechFood.infrastructure.adapter.out.product.entity;

import br.com.fiap.TechFood.application.core.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.core.usecases.product.domain.ProductCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Entity
@Table(name = "products")
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
    private Set<ProductImageEntity> images;

    @Deprecated
    public ProductEntity() {}

    public ProductEntity(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.images = product.getImages().stream().map(ProductImageEntity::new).collect(toSet());
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

    public Set<ProductImageEntity> getImages() {
        return images;
    }

    public Product toProduct() {
        return new Product(
                this.id,
                this.name,
                this.category,
                this.price,
                this.description,
                this.images.stream().map(ProductImageEntity::toProductImage).collect(toSet()));
    }

    public ProductEntity updateFrom(Product product) {
        this.name = product.getName();
        this.category = product.getCategory();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.images.clear();
        this.images.addAll(product.getImages().stream()
            .map(ProductImageEntity::new)
            .collect(Collectors.toSet()));
        return this;
    }
}
