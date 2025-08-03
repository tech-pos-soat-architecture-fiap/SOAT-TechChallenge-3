package br.com.fiap.TechFood.application.domain.order;

import br.com.fiap.TechFood.application.usecases.product.domain.Product;

import java.math.BigDecimal;

public class OrderItem {
    private int quantity;
    private Product product;
    private BigDecimal subTotal = BigDecimal.ZERO;

    public OrderItem() {}

    public OrderItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
        this.subTotal = product.getPrice().multiply(new BigDecimal(quantity));
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public Long getProductId() {
        return this.product.getId();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        updateSubTotal();
    }

    private void updateSubTotal() {
        if (this.product != null && this.product.getPrice() != null) {
            this.subTotal = this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
        }
    }
}
