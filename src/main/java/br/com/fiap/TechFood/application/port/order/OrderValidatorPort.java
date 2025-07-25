package br.com.fiap.TechFood.application.port.order;

import br.com.fiap.TechFood.application.usecases.order.Order;
import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.shared.exception.ValidationResult;

import java.util.List;

public interface OrderValidatorPort {
    ValidationResult validateAddItems(List<? extends OrderItemPort> orderItems, List<Product> products);
    ValidationResult validateRemoveItems(Order order, List<? extends OrderItemPort> orderItems);
}
