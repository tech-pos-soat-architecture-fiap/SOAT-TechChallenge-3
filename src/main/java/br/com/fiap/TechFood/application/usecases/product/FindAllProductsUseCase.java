package br.com.fiap.TechFood.application.usecases.product;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.in.FindAllProductsPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public class FindAllProductsUseCase implements FindAllProductsPort {

    private final ProductGatewayPort productGatewayPort;

    public FindAllProductsUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public PagePort<ProductView> findAll(int page, int size) {
        return productGatewayPort.findAll(page, size).map(ProductView::from);
    }
}
