package br.com.fiap.TechFood.application.core.service.order;

import br.com.fiap.TechFood.application.core.usecases.order.Order;
import br.com.fiap.TechFood.application.core.usecases.order.OrderItem;
import br.com.fiap.TechFood.application.core.usecases.order.OrderStatus;
import br.com.fiap.TechFood.application.core.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderItemPort;
import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.OrderServicePort;
import br.com.fiap.TechFood.application.port.order.OrderValidatorPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ProductServicePort productServicePort;
    private final OrderValidatorPort orderValidator;

    public OrderService(OrderRepositoryPort orderRepositoryPort, UserRepositoryPort userRepositoryPort,
                        ProductServicePort productServicePort, OrderValidatorPort orderValidator) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.productServicePort = productServicePort;
        this.orderValidator = orderValidator;
    }

    @Override
    public PagePort<Order> findAll(int page, int size) {
        return orderRepositoryPort.findAll(page, size);
    }

    @Override
    public Order changeStatus(Long orderId) {
        Order order = this.findById(orderId);
        if (order.isNotFinished()) {
            OrderStatus nextStatus = order.getStatus().next();
            order.setStatus(nextStatus);
            return save(order);
        }
        throw new IllegalStateException("Order not found or already finished.");
    }

    @Override
    public Order create(Long userId) {
        if (userId != null) {
            userRepositoryPort.findById(userId).orElseThrow(NotFoundException::new);
        }
        return orderRepositoryPort.save(Order.createDraft(userId));
    }

    @Override
    public Order addItems(List<? extends OrderItemPort> orderItems, Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);

        if (order.isNotDraft()) throw new IllegalStateException("Order is not draft.");

        List<Product> products = productServicePort.getProductsByIds(orderItems.stream()
                .map(OrderItemPort::productId).toList());

        orderValidator.validateAddItems(orderItems, products).throwIfInvalid();

        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<OrderItem> itemsToAdd = orderItems.stream()
            .map(orderItem -> new OrderItem(orderItem.quantity(), productMap.get(orderItem.productId())))
            .toList();

        order.addItems(itemsToAdd);

        return orderRepositoryPort.save(order);
    }

    @Override
    public Order removeItems(List<? extends OrderItemPort> orderItems, Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);

        orderValidator.validateRemoveItems(order, orderItems).throwIfInvalid();

        List<Product> products = productServicePort.getProductsByIds(orderItems.stream()
                .map(OrderItemPort::productId).toList());

        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<OrderItem> orderItemsToRemove = orderItems.stream().map(orderItem ->
                new OrderItem(orderItem.quantity(), productMap.get(orderItem.productId()))).toList();

        order.removeItems(orderItemsToRemove);

        return orderRepositoryPort.save(order);
    }

    @Override
    public Order findById(Long id) {
        return orderRepositoryPort.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public Order save(Order order) {
        return orderRepositoryPort.save(order);
    }
}
