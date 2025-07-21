package br.com.fiap.TechFood.application.usecases.product;

import br.com.fiap.TechFood.application.port.product.in.RemoveProductPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;
import br.com.fiap.TechFood.application.usecases.product.domain.Product;

public class RemoveProductUseCase implements RemoveProductPort {

    private final ProductGatewayPort productGatewayPort;

    public RemoveProductUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public void remove(Long id) {
        Product product = productGatewayPort.findById(id).orElseThrow(NotFoundException::new);
        productGatewayPort.remove(product);
    }
}
