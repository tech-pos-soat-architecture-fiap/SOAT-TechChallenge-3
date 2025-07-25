package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.domain.Order;
import br.com.fiap.TechFood.application.domain.OrderItem;
import br.com.fiap.TechFood.application.port.order.in.RemoveOrderItemsPort;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.port.order.*;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RemoveOrderItemsUseCase implements RemoveOrderItemsPort {

    private final OrderRepositoryPort orderRepositoryPort;
    private final OrderValidatorPort orderValidator;
    private final ProductGatewayPort productGatewayPort;

    public RemoveOrderItemsUseCase(OrderRepositoryPort orderRepositoryPort, OrderValidatorPort orderValidator, ProductGatewayPort productGatewayPort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.orderValidator = orderValidator;
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Order removeItems(List<? extends OrderItemPort> orderItems, Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);

        orderValidator.validateRemoveItems(order, orderItems).throwIfInvalid();

        List<Product> products = productGatewayPort.findAllByIdIn(orderItems.stream()
                .map(OrderItemPort::productId).toList());

        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<OrderItem> orderItemsToRemove = orderItems.stream().map(orderItem ->
                new OrderItem(orderItem.quantity(), productMap.get(orderItem.productId()))).toList();

        order.removeItems(orderItemsToRemove);

        return orderRepositoryPort.save(order);
    }
}
