package br.com.fiap.TechFood.application.port.product.in;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public interface FindProductsByCategoryPort {
    PagePort<ProductView> findAllByCategory(String category, int page, int size);
}
