package br.com.fiap.TechFood.application.usecases.product;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.in.FindProductsByCategoryPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;
import br.com.fiap.TechFood.application.usecases.product.domain.ProductCategory;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public class FindProductsByCategoryUseCase implements FindProductsByCategoryPort {

    private final ProductGatewayPort productGatewayPort;

    public FindProductsByCategoryUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public PagePort<ProductView> findAllByCategory(String category, int page, int size) {
        ProductCategory productCategory = ProductCategory.findByName(category).orElseThrow(NotFoundException::new);
        return productGatewayPort.findAllByCategory(productCategory, page, size).map(ProductView::from);
    }
}
