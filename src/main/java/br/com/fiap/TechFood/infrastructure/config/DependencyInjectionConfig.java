package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.port.order.out.OrderRepositoryPort;
import br.com.fiap.TechFood.application.port.order.OrderValidatorPort;
import br.com.fiap.TechFood.application.port.order.in.*;
import br.com.fiap.TechFood.application.port.product.in.*;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
import br.com.fiap.TechFood.application.usecases.order.*;
import br.com.fiap.TechFood.application.usecases.product.*;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.repository.ProductEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DependencyInjectionConfig {

    private final ProductEntityRepository productEntityRepository;

    public DependencyInjectionConfig(ProductEntityRepository productEntityRepository) {
        this.productEntityRepository = productEntityRepository;
    }

    @Bean
    public CreateProductPort createProductUseCase(ProductGatewayPort productGatewayPort) {
        return new CreateProductUseCase(productGatewayPort);
    }

    @Bean
    public FindProductsByCategoryPort findProductsByCategoryPort(ProductGatewayPort productGatewayPort) {
        return new FindProductsByCategoryUseCase(productGatewayPort);
    }

    @Bean
    public UpdateProductPort updateProductPort(ProductGatewayPort productGatewayPort) {
        return new UpdateProductUseCase(productGatewayPort);
    }

    @Bean
    public FindProductPort findProductPort(ProductGatewayPort productGatewayPort) {
        return new FindProductUseCase(productGatewayPort);
    }

    @Bean
    public FindAllProductsPort finddAllProductsPort(ProductGatewayPort productGatewayPort) {
        return new FindAllProductsUseCase(productGatewayPort);
    }

    @Bean
    public RemoveProductPort removeProductPort(ProductGatewayPort productGatewayPort) {
        return new RemoveProductUseCase(productGatewayPort);
    }

    @Bean
    public AddOrderItemsPort addOrderItemsUseCase(
            OrderRepositoryPort orderRepositoryPort,
            OrderValidatorPort orderValidator,
            ProductGatewayPort productGatewayPort) {
        return new AddOrderItemsUseCase(orderRepositoryPort, orderValidator, productGatewayPort);
    }

    @Bean
    public ChangeOrderStatusPort changeOrderStatusUseCase(
            OrderRepositoryPort orderRepositoryPort
    ){
        return new ChangeOrderStatusUseCase(orderRepositoryPort);
    }

    @Bean
    public CreateOrderPort createOrderUseCase(
            UserRepositoryPort userRepositoryPort,
            OrderRepositoryPort orderRepositoryPort
    ) {
        return new CreateOrderUseCase(userRepositoryPort, orderRepositoryPort);
    }

    @Bean
    public RemoveOrderItemsPort removeOrderItemsUseCase(
            OrderRepositoryPort orderRepositoryPort,
            OrderValidatorPort orderValidator,
            ProductGatewayPort productGatewayPort
    ) {
        return new RemoveOrderItemsUseCase(orderRepositoryPort, orderValidator, productGatewayPort);
    }

    @Bean
    public FindAllOrderPort findAllOrderUseCase(
            OrderRepositoryPort orderRepositoryPort
    ) {
        return new FindAllOrderUseCase(orderRepositoryPort);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
