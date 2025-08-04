package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.port.order.OrderValidatorPort;
import br.com.fiap.TechFood.application.port.order.in.*;
import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.payment.out.PaymentGatewayProcessor;
import br.com.fiap.TechFood.application.port.payment.out.PaymentRepositoryPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.usecases.order.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderDependencyInjectionConfig {

    @Bean
    public AddOrderItemsPort addOrderItemsUseCase(OrderRepositoryPort orderRepositoryPort, OrderValidatorPort orderValidator, ProductGatewayPort productGatewayPort) {
        return new AddOrderItemsUseCase(orderRepositoryPort, orderValidator, productGatewayPort);
    }

    @Bean
    public ChangeOrderStatusPort changeOrderStatusUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new ChangeOrderStatusUseCase(orderRepositoryPort);
    }

    @Bean
    public CreateOrderPort createOrderUseCase(UserRepositoryPort userRepositoryPort, OrderRepositoryPort orderRepositoryPort) {
        return new CreateOrderUseCase(userRepositoryPort, orderRepositoryPort);
    }

    @Bean
    public RemoveOrderItemsPort removeOrderItemsUseCase(OrderRepositoryPort orderRepositoryPort, OrderValidatorPort orderValidator, ProductGatewayPort productGatewayPort) {
        return new RemoveOrderItemsUseCase(orderRepositoryPort, orderValidator, productGatewayPort);
    }

    @Bean
    public FindAllActiveOrderPort findAllOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new FindAllActiveOrderUseCase(orderRepositoryPort);
    }

    @Bean
    public FindOrderPort findOrderUseCase(OrderRepositoryPort orderRepositoryPort) {
        return new FindOrderUseCase(orderRepositoryPort);
    }

    @Bean
    public ProccessOrderPaymentPort proccessOrderPaymentUseCase(OrderRepositoryPort orderRepositoryPort, PaymentRepositoryPort paymentRepositoryPort, PaymentGatewayProcessor paymentGatewayProcessor) {
        return new ProccessOrderPaymentUseCase(orderRepositoryPort, paymentRepositoryPort, paymentGatewayProcessor);
    }

}
