package br.com.fiap.TechFood.application.usecases.product;

import br.com.fiap.TechFood.application.usecases.product.domain.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.FindProductsByCategoryPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public class FindProductsByCategoryUSeCase implements FindProductsByCategoryPort {

    private final ProductServicePort productServicePort;

    public FindProductsByCategoryUSeCase(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @Override
    public PagePort<ProductView> findAllByCategory(ProductCategory productCategory, int page, int size) {
        return productServicePort.findAllByCategory(productCategory, page, size).map(ProductView::from);
    }
}
