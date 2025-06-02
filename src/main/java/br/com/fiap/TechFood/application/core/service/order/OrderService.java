package br.com.fiap.TechFood.application.core.service.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.core.domain.order.OrderItem;
import br.com.fiap.TechFood.application.core.domain.order.OrderStatus;
import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.user.User;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.OrderServicePort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;
import br.com.fiap.TechFood.infrastructure.adapter.in.order.OrderItemForm;
import br.com.fiap.TechFood.infrastructure.adapter.in.order.OrderStatusView;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ProductServicePort productServicePort;

    public OrderService(OrderRepositoryPort orderRepositoryPort, UserRepositoryPort userRepositoryPort,
                        ProductServicePort productServicePort) {
        this.orderRepositoryPort = orderRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.productServicePort = productServicePort;
    }

    @Override
    public PagePort<Order> findAll(int page, int size) {
        return orderRepositoryPort.findAll(page, size);
    }

    @Override
    public OrderStatusView changeStatus(Long orderId) {
        Optional<Order> possibleOrder = this.findById(orderId);
        if (possibleOrder.isPresent() && possibleOrder.get().getStatus().isNotFinished()) {
            Order order = possibleOrder.get();
            OrderStatus nextStatus = order.getStatus().next();
            order.setStatus(nextStatus);
            save(order);
            return new OrderStatusView(orderId, order.getStatusName());
        }
        throw new IllegalStateException("Order not found or already finished.");
    }

    @Override
    public Order create(Long userId) {
        User user = null;
        if (userId != null) {
            user = userRepositoryPort.findById(userId).orElseThrow(NotFoundException::new);
        }
        return orderRepositoryPort.save(Order.createDraft(user));
    }

    @Override
    public Order addItems(List<OrderItemForm> orderItemsForms, Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);

        List<Product> products = productServicePort.getProductsByIds(orderItemsForms.stream()
                .map(OrderItemForm::productId).toList());

        Map<Long, Product> productMap = products.stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<OrderItem> orderItems = orderItemsForms.stream().map(orderItemForm ->
                new OrderItem(orderItemForm.quantity(),
                        productMap.get(orderItemForm.productId()))).toList();


        order.addItems(orderItems);
        return orderRepositoryPort.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepositoryPort.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepositoryPort.save(order);
    }
}
