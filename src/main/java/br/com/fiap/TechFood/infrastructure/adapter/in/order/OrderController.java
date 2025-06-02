package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class OrderController {
    private final OrderServicePort orderServicePort;

    public OrderController(OrderServicePort orderServicePort) {
        this.orderServicePort = orderServicePort;
    }

    @PostMapping("create/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderForm order) {
        if (Objects.requireNonNull(order.orderItems()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return null;
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
