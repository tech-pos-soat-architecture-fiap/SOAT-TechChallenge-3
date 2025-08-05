package br.com.fiap.TechFood.infrastructure.adapter.in.order;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.order.in.*;
import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
    private final ChangeOrderStatusPort changeOrderStatusPort;
    private final RemoveOrderItemsPort removeOrderItemsPort;
    private final AddOrderItemsPort addOrderItemsPort;
    private final CreateOrderPort createOrderPort;
    private final FindAllActiveOrderPort findAllOrderPort;
    private final FindOrderPort findOrderPort;
    private final CreateOrderPaymentPort createOrderPaymentPort;

    public OrderController(ChangeOrderStatusPort changeOrderStatusPort, RemoveOrderItemsPort removeOrderItemsPort, AddOrderItemsPort addOrderItemsPort, CreateOrderPort createOrderPort, FindAllActiveOrderPort findAllOrderPort, FindOrderPort findOrderPort, CreateOrderPaymentPort createOrderPaymentPort) {
        this.changeOrderStatusPort = changeOrderStatusPort;
        this.removeOrderItemsPort = removeOrderItemsPort;
        this.addOrderItemsPort = addOrderItemsPort;
        this.createOrderPort = createOrderPort;
        this.findAllOrderPort = findAllOrderPort;
        this.findOrderPort = findOrderPort;
        this.createOrderPaymentPort = createOrderPaymentPort;
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<OrderView> getOrder(@PathVariable Long orderId) {
        Order order = findOrderPort.findById(orderId);
        return ResponseEntity.ok(OrderView.from(order));
    }

    @PostMapping("/create/orders")
    public ResponseEntity<OrderView> createOrder(@RequestBody(required = false) Long userId) {
        Order order = createOrderPort.create(userId);

        URI uri = URI.create("/orders/" + userId);

        return ResponseEntity.created(uri).body(OrderView.from(order));
    }

    @PostMapping("/add-items/{orderId}")
    public ResponseEntity<OrderView> addItem(@Valid @RequestBody List<OrderItemForm> orderItemsForms, @PathVariable Long orderId) {
        Order order = addOrderItemsPort.addItems(orderItemsForms, orderId);
        return ResponseEntity.ok(OrderView.from(order));
    }

    @PostMapping("/remove-items/{orderId}")
    public ResponseEntity<OrderView> removeItems(@Valid @RequestBody List<OrderItemForm> orderItemForms, @PathVariable Long orderId) {
        Order order = removeOrderItemsPort.removeItems(orderItemForms, orderId);
        return ResponseEntity.ok(OrderView.from(order));
    }

    @GetMapping("/orders/active")
    public ResponseEntity<PagePort<OrderView>> showAllActiveSorted(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "10") int size) {
        PagePort<OrderView> ordersView = findAllOrderPort.findAllActiveSorted(page, size).map(OrderView::from);
        return ResponseEntity.ok(ordersView);
    }

    @Transactional
    @PostMapping("/orders/payment/{orderId}")
    public ResponseEntity<PaymentQRCodeView> createPayment(@PathVariable Long orderId) {
        return ResponseEntity.ok(createOrderPaymentPort.createPayment(orderId));
    }

    @PutMapping("/order/change-status/{orderId}")
    public ResponseEntity<OrderStatusView> changeStatus(@PathVariable("orderId") Long id) {
        Order order = changeOrderStatusPort.changeStatus(id);
        return ResponseEntity.ok(OrderStatusView.from(order));
    }
}
