package br.com.fiap.TechFood.application.port.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.infrastructure.adapter.in.order.OrderItemForm;
import br.com.fiap.TechFood.application.shared.exception.ValidationResult;

import java.util.List;

public interface OrderValidatorPort {
    ValidationResult validateAddItems(List<OrderItemForm> orderItemForm, List<Product> productsByIds);
    ValidationResult validateRemoveItems(Order order, List<OrderItemForm> orderItemForms);
}
