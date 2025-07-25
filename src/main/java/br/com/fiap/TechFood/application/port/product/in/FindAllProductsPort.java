package br.com.fiap.TechFood.application.port.product.in;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public interface FindAllProductsPort {
    PagePort<ProductView> findAll(int page, int size);
}
