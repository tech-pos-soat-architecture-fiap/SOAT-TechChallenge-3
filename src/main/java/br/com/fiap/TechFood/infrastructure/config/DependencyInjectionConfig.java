package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.port.product.in.*;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.port.user.*;
import br.com.fiap.TechFood.application.usecases.product.*;
import br.com.fiap.TechFood.application.usecases.user.*;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.ProductGatewayAdapter;
import br.com.fiap.TechFood.infrastructure.adapter.out.product.repository.ProductEntityRepository;
import br.com.fiap.TechFood.infrastructure.adapter.out.user.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DependencyInjectionConfig {

    private final ProductEntityRepository productEntityRepository;

    public DependencyInjectionConfig(ProductEntityRepository productEntityRepository) {
        this.productEntityRepository = productEntityRepository;
    }

    @Bean
    public ProductGatewayPort productGateway() {
        return new ProductGatewayAdapter(productEntityRepository);
    }

    @Bean
    public CreateProductPort createProductUseCase() {
        return new CreateProductUseCase(productGateway());
    }

    @Bean
    public FindProductsByCategoryPort findProductsByCategoryPort() {
        return new FindProductsByCategoryUseCase(productGateway());
    }

    @Bean
    public UpdateProductPort updateProductPort() {
        return new UpdateProductUseCase(productGateway());
    }

    @Bean
    public FindProductPort findProductPort() {
        return new FindProductUseCase(productGateway());
    }

    @Bean
    public FindAllProductsPort finddAllProductsPort() {
        return new FindAllProductsUseCase(productGateway());
    }

    @Bean
    public RemoveProductPort removeProductPort() {
        return new RemoveProductUseCase(productGateway());
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepositoryAdapter userRepositoryAdapter) {
        return userRepositoryAdapter;
    }

    @Bean
    public CreateUserPort createUserUsePort(UserRepositoryPort userRepositoryPort) {
        return new CreateUserUseCase(userRepositoryPort);
    }

    @Bean
    public FindAllUsersPort findAllUsersPort(UserRepositoryPort userRepositoryPort) {
        return new FindAllUsersUseCase(userRepositoryPort);
    }

    @Bean
    public FindUserByIdPort findUserByIdPort(UserRepositoryPort userRepositoryPort) {
        return new FindUserByIdUseCase(userRepositoryPort);
    }

    @Bean
    public FindUserByCpfPort findUserByCpfPort(UserRepositoryPort userRepositoryPort) {
        return new FindUserByCpfUseCase(userRepositoryPort);
    }

//    @Bean
//    public OrderServicePort orderService(OrderRepositoryPort orderRepositoryPort, UserRepositoryPort userRepositoryPort,
//                                         ProductServicePort productServicePort, OrderValidatorPort orderValidatorPort) {
//        return new OrderService(orderRepositoryPort, userRepositoryPort, productServicePort, orderValidatorPort);
//    }
}
