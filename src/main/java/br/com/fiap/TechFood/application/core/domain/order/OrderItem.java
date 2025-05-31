package br.com.fiap.TechFood.application.core.domain.order;

import br.com.fiap.TechFood.application.core.domain.product.Product;

import java.math.BigDecimal;

public class OrderItem {
    private int quantity;
    private Product product;
    private BigDecimal price = BigDecimal.ZERO;

    public OrderItem() {}

    public OrderItem(int quantity, Product product, BigDecimal price) {
        this.quantity = quantity;
        this.product = product;
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
