package br.com.fiap.TechFood.infrastructure.adapter.in.validation.order;

import br.com.fiap.TechFood.application.domain.Order;
import br.com.fiap.TechFood.application.domain.OrderItem;
import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.port.order.OrderItemPort;
import br.com.fiap.TechFood.application.port.order.OrderValidatorPort;
import br.com.fiap.TechFood.application.shared.exception.ValidationResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderValidator implements OrderValidatorPort {

    @Override
    public ValidationResult validateAddItems(List<? extends OrderItemPort> orderItemForm, List<Product> productsByIds) {
        ValidationResult validationResult = new ValidationResult();

        orderItemForm.stream().map(OrderItemPort::productId).forEach(productId -> {
            if (productsByIds.stream().noneMatch(product -> product.getId().equals(productId))) {
                validationResult.addError("productId", "Product %s not found".formatted(productId));
            }
        });

        return validationResult;
    }

    @Override
    public ValidationResult validateRemoveItems(Order order, List<? extends OrderItemPort> orderItemForms) {
        ValidationResult validationResult = new ValidationResult();

        Map<Long, Integer> productsQuantityMap = order.getOrderItems().stream().collect(Collectors.toMap(OrderItem::getProductId,
                OrderItem::getQuantity));

        orderItemForms.forEach(cartItemRequest -> {
            if (productsQuantityMap.get(cartItemRequest.productId()) == null) {
                validationResult.addError("productId", "Product %s not found in cart".formatted(cartItemRequest.productId()));
            } else if (productsQuantityMap.get(cartItemRequest.productId()) < cartItemRequest.quantity()) {
                validationResult.addError("quantity", "Quantity of product %s is less than requested".formatted(cartItemRequest.productId()));
            }
        });

        return validationResult;
    }
}
