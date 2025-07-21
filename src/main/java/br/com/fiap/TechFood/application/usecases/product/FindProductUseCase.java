package br.com.fiap.TechFood.application.usecases.product;

import br.com.fiap.TechFood.application.port.product.in.FindProductPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

import java.util.Optional;

public class FindProductUseCase implements FindProductPort {

    private final ProductGatewayPort productGatewayPort;

    public FindProductUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public Optional<ProductView> findById(Long id) {
        return productGatewayPort.findById(id).map(ProductView::from);
    }
}
