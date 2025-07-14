package br.com.fiap.TechFood.application.core.usecases.product.domain;

import java.util.Arrays;
import java.util.Optional;

public enum ProductCategory {
    SNACK("Lanche"),
    SIDE("Acompanhamento"),
    BEVERAGE("Bebida"),
    DESSERT("Sobremesa");

    private final String name;

    ProductCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Optional<ProductCategory> findByName(String name) {
        return Arrays.stream(ProductCategory.values())
                .filter(category -> category.getName().equals(name))
                .findFirst();
    }

    public static ProductCategory getByName(String name) {
        return Arrays.stream(ProductCategory.values())
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
