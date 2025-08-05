package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.domain.order.OrderItem;
import br.com.fiap.TechFood.application.port.order.in.AddOrderItemsPort;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.domain.product.Product;
import br.com.fiap.TechFood.application.port.order.*;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AddOrderItemsUseCase implements AddOrderItemsPort {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderValidatorPort orderValidator;
    private final ProductGatewayPort productGatewayPort;

    public AddOrderItemsUseCase(OrderRepositoryPort orderRepositoryPort, OrderValidatorPort orderValidator, ProductGatewayPort productGatewayPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderValidator = orderValidator;
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Order addItems(List<? extends OrderItemPort> orderItems, Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);

        if (order.isNotDraft()) throw new IllegalStateException("Order is not draft.");

        List<Product> products = productGatewayPort.findAllByIdIn(orderItems.stream()
                .map(OrderItemPort::productId).toList());

        orderValidator.validateAddItems(orderItems, products).throwIfInvalid();

        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<OrderItem> itemsToAdd = orderItems.stream()
                .map(orderItem -> new OrderItem(orderItem.quantity(), productMap.get(orderItem.productId())))
                .toList();

        order.addItems(itemsToAdd);

        return orderRepositoryPort.save(order);
    }
}
