package br.com.fiap.TechFood.application.core.domain.product;

import br.com.fiap.TechFood.application.core.domain.product.vo.ProductImage;

import java.math.BigDecimal;
import java.util.Set;

public class Product {

    private Long id;
    private String name;
    private ProductCategory productCategory;
    private BigDecimal price;
    private String description;
    private Set<ProductImage> images;

    public Product() {}

    public Product(Long id, String name, ProductCategory productCategory, BigDecimal price, String description, Set<ProductImage> images) {
        this.id = id;
        this.name = name;
        this.productCategory = productCategory;
        this.price = price;
        this.description = description;
        this.images = images;
    }

    public Product(String name, String categoryName, BigDecimal price, String description, Set<ProductImage> images) {
        this.name = name;
        this.productCategory = ProductCategory.getByName(categoryName);
        this.price = price;
        this.description = description;
        this.images = images;
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

    public Product update(Product productData) {
        return new Product(productData.getId(),
                productData.getName(),
                productData.getCategory(),
                productData.getPrice(),
                productData.getDescription(),
                productData.getImages());
    }
}
