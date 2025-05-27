package br.com.fiap.TechFood.application.core.domain.product;

import java.util.Arrays;

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

    public static ProductCategory findByName(String name) {
        return Arrays.stream(ProductCategory.values())
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
