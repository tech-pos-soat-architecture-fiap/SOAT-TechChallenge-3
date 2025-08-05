package br.com.fiap.TechFood.application.usecases.order;

import br.com.fiap.TechFood.application.domain.order.Order;
import br.com.fiap.TechFood.application.domain.payment.Payment;
import br.com.fiap.TechFood.application.port.order.in.ChangeOrderStatusPort;
import br.com.fiap.TechFood.application.port.order.in.CreateOrderPaymentPort;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;
import br.com.fiap.TechFood.application.port.payment.out.PaymentGatewayProcessor;
import br.com.fiap.TechFood.application.port.payment.out.PaymentRepositoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;

public class CreateOrderPaymentUseCase implements CreateOrderPaymentPort {

    private final ChangeOrderStatusPort changeOrderStatusPort;
    private final OrderRepositoryPort orderRepositoryPort;
    private final PaymentRepositoryPort paymentRepositoryPort;
    private final PaymentGatewayProcessor paymentGatewayProcessor;

    public CreateOrderPaymentUseCase(ChangeOrderStatusPort changeOrderStatusPort, OrderRepositoryPort orderRepositoryPort, PaymentRepositoryPort paymentRepositoryPort, PaymentGatewayProcessor paymentGatewayProcessor) {
        this.changeOrderStatusPort = changeOrderStatusPort;
        this.orderRepositoryPort = orderRepositoryPort;
        this.paymentRepositoryPort = paymentRepositoryPort;
        this.paymentGatewayProcessor = paymentGatewayProcessor;
    }

    @Override
    public PaymentQRCodeView createPayment(Long orderId) {
        Order order = orderRepositoryPort.findById(orderId).orElseThrow(NotFoundException::new);
        Payment payment = paymentRepositoryPort.save(Payment.create(order.getTotal()));

        order.setPayment(payment);
        order = orderRepositoryPort.save(order);

        changeOrderStatusPort.changeStatus(orderId);

        return paymentGatewayProcessor.generateQRCode(order);
    }
}
