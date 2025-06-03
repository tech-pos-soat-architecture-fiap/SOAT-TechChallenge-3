package br.com.fiap.TechFood.application.core.service.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.core.domain.order.OrderItem;
import br.com.fiap.TechFood.application.core.domain.order.OrderStatus;
import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.OrderServicePort;
import br.com.fiap.TechFood.application.port.order.OrderValidatorPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;
import br.com.fiap.TechFood.infrastructure.adapter.in.order.OrderItemForm;
import br.com.fiap.TechFood.infrastructure.adapter.in.order.OrderStatusView;

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
    public OrderStatusView changeStatus(Long orderId) {
        Order order = this.findById(orderId);
        if (order.isNotFinished()) {
            OrderStatus nextStatus = order.getStatus().next();
            order.setStatus(nextStatus);
            save(order);
            return new OrderStatusView(orderId, order.getStatusName());
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
    public Order addItems(List<OrderItemForm> orderItemsForms, Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);

        List<Product> products = productServicePort.getProductsByIds(orderItemsForms.stream()
                .map(OrderItemForm::productId).toList());

        orderValidator.validateAddItems(orderItemsForms, products).throwIfInvalid();

        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<OrderItem> orderItems = orderItemsForms.stream().map(orderItemForm ->
                new OrderItem(orderItemForm.quantity(),
                        productMap.get(orderItemForm.productId()))).toList();


        order.addItems(orderItems);
        return orderRepositoryPort.save(order);
    }

    @Override
    public Order removeItems(List<OrderItemForm> orderItemsForms, Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);

        orderValidator.validateRemoveItems(order, orderItemsForms).throwIfInvalid();

        List<Product> products = productServicePort.getProductsByIds(orderItemsForms.stream()
                .map(OrderItemForm::productId).toList());

        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<OrderItem> orderItems = orderItemsForms.stream().map(orderItemForm ->
                new OrderItem(orderItemForm.quantity(),
                        productMap.get(orderItemForm.productId()))).toList();

        order.removeItems(orderItems);

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
