package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class OrderController {
    private final OrderServicePort orderServicePort;

    public OrderController(OrderServicePort orderServicePort) {
        this.orderServicePort = orderServicePort;
    }

    //TODO criar o endpoint /orders/id

    @PostMapping("create/order")
    public ResponseEntity<?> createOrder(@RequestBody(required = false) Long userId) {
        Order order = orderServicePort.create(userId);

        URI uri = URI.create("/orders/" + userId);

        return ResponseEntity.created(uri).body(OrderView.from(order));
    }

    @PostMapping("/add-items")
    public ResponseEntity<?> addItem(@Valid @RequestBody List<OrderItemForm> orderItemsForms, Long orderId) {
        Order order = orderServicePort.addItems(orderItemsForms, orderId);
        return ResponseEntity.ok(OrderView.from(order));
    }

    @GetMapping("/orders")
    public ResponseEntity<PagePort<OrderView>> showAll(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        PagePort<OrderView> ordersView = orderServicePort.findAll(page, size).map(OrderView::from);
        return ResponseEntity.ok(ordersView);
    }

    @GetMapping("order/change-status/{orderId}")
    public ResponseEntity<?> changeStatus(@PathVariable("orderId") Long id) {
        return ResponseEntity.ok(orderServicePort.changeStatus(id));
    }

}
