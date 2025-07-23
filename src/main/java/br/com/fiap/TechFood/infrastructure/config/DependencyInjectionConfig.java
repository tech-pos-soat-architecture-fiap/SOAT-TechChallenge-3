package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.port.product.in.*;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.port.user.UserRepositoryPort;
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
    public ProductGatewayPort userGateway() {
        return new ProductGatewayAdapter(productEntityRepository);
    }

    @Bean
    public CreateProductPort createProductUseCase() {
        return new CreateProductUseCase(userGateway());
    }

    @Bean
    public FindProductsByCategoryPort findProductsByCategoryPort() {
        return new FindProductsByCategoryUseCase(userGateway());
    }

    @Bean
    public UpdateProductPort updateProductPort() {
        return new UpdateProductUseCase(userGateway());
    }

    @Bean
    public FindProductPort findProductPort() {
        return new FindProductUseCase(userGateway());
    }

    @Bean
    public FindAllProductsPort finddAllProductsPort() {
        return new FindAllProductsUseCase(userGateway());
    }

    @Bean
    public RemoveProductPort removeProductPort() {
        return new RemoveProductUseCase(userGateway());
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepositoryAdapter userRepositoryAdapter) {
        return userRepositoryAdapter;
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort userRepositoryPort) {
        return new CreateUserUseCase(userRepositoryPort);
    }

    @Bean
    public FindAllUsersUseCase findAllUsersUseCase(UserRepositoryPort userRepositoryPort) {
        return new FindAllUsersUseCase(userRepositoryPort);
    }

    @Bean
    public FindUserByIdUseCase findUserByIdUseCase(UserRepositoryPort userRepositoryPort) {
        return new FindUserByIdUseCase(userRepositoryPort);
    }

    @Bean
    public FindUserByCpfUseCase findUserByCpfUseCase(UserRepositoryPort userRepositoryPort) {
        return new FindUserByCpfUseCase(userRepositoryPort);
    }

//    @Bean
//    public OrderServicePort orderService(OrderRepositoryPort orderRepositoryPort, UserRepositoryPort userRepositoryPort,
//                                         ProductServicePort productServicePort, OrderValidatorPort orderValidatorPort) {
//        return new OrderService(orderRepositoryPort, userRepositoryPort, productServicePort, orderValidatorPort);
//    }
}
