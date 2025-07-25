package br.com.fiap.TechFood.application.usecases.product.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class Product {

    private Long id;
    private String name;
    private ProductCategory productCategory;
    private BigDecimal price;
    private String description;
    private Set<ProductImage> images;

    public Product(Long id, String name, ProductCategory productCategory, BigDecimal price, String description, Collection<? extends ImageContract> images) {
        this.id = id;
        this.name = name;
        this.productCategory = productCategory;
        this.price = price;
        this.description = description;
        this.images = images.stream().map(ProductImage::new).collect(Collectors.toSet());
    }

    public Product(Long id, String name, String categoryName, BigDecimal price, String description, Collection<? extends ImageContract> images) {
        this.id = id;
        this.name = name;
        this.productCategory = ProductCategory.getByName(categoryName);
        this.price = price;
        this.description = description;
        this.images = images.stream().map(ProductImage::new).collect(Collectors.toSet());
    }

    public Product(String name, String categoryName, BigDecimal price, String description, Collection<? extends ImageContract> images) {
        this.name = name;
        this.productCategory = ProductCategory.getByName(categoryName);
        this.price = price;
        this.description = description;
        this.images = images.stream().map(ProductImage::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getCategory() {
        return productCategory;
    }

    public String getCategoryName() {
        return productCategory.getName();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Set<ProductImage> getImages() {
        return images;
    }
}
