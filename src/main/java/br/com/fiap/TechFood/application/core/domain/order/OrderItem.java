package br.com.fiap.TechFood.application.core.domain.order;

import br.com.fiap.TechFood.application.core.domain.product.Product;

import java.math.BigDecimal;

public class OrderItem {
    private int quantity;
    private Product product;
    private BigDecimal price = BigDecimal.ZERO;

    public OrderItem() {}

    public OrderItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.price = product.getPrice().multiply(new BigDecimal(quantity));
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

    public Long getProductId() {
        return this.product.getId();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
