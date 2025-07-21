package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.core.usecases.order.*;
import br.com.fiap.TechFood.application.port.order.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.OrderValidatorPort;
import br.com.fiap.TechFood.application.port.product.ProductGatewayPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderDependencyInjectionConfig {

    @Bean
    public AddOrderItemsUseCase addOrderItemsUseCase(
            OrderRepositoryPort orderRepositoryPort,
            OrderValidatorPort orderValidator,
            ProductGatewayPort productGatewayPort) {
        return new AddOrderItemsUseCaseImpl(orderRepositoryPort, orderValidator, productGatewayPort);
    }

    @Bean
    public ChangeOrderStatusUseCase changeOrderStatusUseCase(
            OrderRepositoryPort orderRepositoryPort
    ){
        return new ChangeOrderStatusUseCaseImpl(orderRepositoryPort);
    }

    @Bean
    public CreateOrderUseCase createOrderUseCase(
            UserRepositoryPort userRepositoryPort,
            OrderRepositoryPort orderRepositoryPort
    ) {
        return new CreateOrderUseCaseImpl(userRepositoryPort, orderRepositoryPort);
    }

    @Bean
    public RemoveOrderItemsUseCase removeOrderItemsUseCase(
            OrderRepositoryPort orderRepositoryPort,
            OrderValidatorPort orderValidator,
            ProductGatewayPort productGatewayPort
    ) {
        return new RemoveOrderItemsUseCaseImpl(orderRepositoryPort, orderValidator, productGatewayPort);
    }

    @Bean
    public FindAllOrderUseCase findAllOrderUseCase(
            OrderRepositoryPort orderRepositoryPort
    ) {
        return new FindAllOrderUseCaseImpl(orderRepositoryPort);
    }
}
