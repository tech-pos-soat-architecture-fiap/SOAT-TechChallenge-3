package br.com.fiap.TechFood.application.port.product;

import br.com.fiap.TechFood.application.core.usecases.product.domain.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public interface FindProductsByCategoryPort {
    PagePort<ProductView> findAllByCategory(ProductCategory productCategory, int page, int size);
}
