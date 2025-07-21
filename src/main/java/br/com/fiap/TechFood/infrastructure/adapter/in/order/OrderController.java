package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.core.domain.order.Order;
import br.com.fiap.TechFood.application.core.usecases.order.*;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.OrderServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
    private final ChangeOrderStatusUseCase changeOrderStatusUseCase;
    private final RemoveOrderItemsUseCase removeOrderItemsUseCase;
    private final AddOrderItemsUseCase addOrderItemsUseCase;
    private final CreateOrderUseCase createOrderUseCase;
    private final FindAllOrderUseCase findAllOrderUseCase;

    public OrderController(ChangeOrderStatusUseCase changeOrderStatusUseCase, RemoveOrderItemsUseCase removeOrderItemsUseCase, AddOrderItemsUseCase addOrderItemsUseCase, CreateOrderUseCase createOrderUseCase, FindAllOrderUseCase findAllOrderUseCase) {
        this.changeOrderStatusUseCase = changeOrderStatusUseCase;
        this.removeOrderItemsUseCase = removeOrderItemsUseCase;
        this.addOrderItemsUseCase = addOrderItemsUseCase;
        this.createOrderUseCase = createOrderUseCase;
        this.findAllOrderUseCase = findAllOrderUseCase;
    }

//    @GetMapping("/order/{orderId}")
//    public ResponseEntity<OrderView> getOrder(@PathVariable Long orderId) {
//        Order order = orderServicePort.findById(orderId);
//        return ResponseEntity.ok(OrderView.from(order));
//    }

    @PostMapping("/create/orders")
    public ResponseEntity<OrderView> createOrder(@RequestBody(required = false) Long userId) {
        Order order = createOrderUseCase.create(userId);

        URI uri = URI.create("/orders/" + userId);

        return ResponseEntity.created(uri).body(OrderView.from(order));
    }

    @PostMapping("/add-items/{orderId}")
    public ResponseEntity<OrderView> addItem(@Valid @RequestBody List<OrderItemForm> orderItemsForms, @PathVariable Long orderId) {
        Order order = addOrderItemsUseCase.addItems(orderItemsForms, orderId);
        return ResponseEntity.ok(OrderView.from(order));
    }

    @PostMapping("/remove-items/{orderId}")
    public ResponseEntity<OrderView> removeItems(@Valid @RequestBody List<OrderItemForm> orderItemForms, @PathVariable Long orderId) {
        Order order = removeOrderItemsUseCase.removeItems(orderItemForms, orderId);
        return ResponseEntity.ok(OrderView.from(order));
    }

    @GetMapping("/orders")
    public ResponseEntity<PagePort<OrderView>> showAll(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        PagePort<OrderView> ordersView = findAllOrderUseCase.findAll(page, size).map(OrderView::from);
        return ResponseEntity.ok(ordersView);
    }

    @PutMapping("/order/change-status/{orderId}")
    public ResponseEntity<OrderStatusView> changeStatus(@PathVariable("orderId") Long id) {
        Order order = changeOrderStatusUseCase.changeStatus(id);
        return ResponseEntity.ok(OrderStatusView.from(order));
    }
}
