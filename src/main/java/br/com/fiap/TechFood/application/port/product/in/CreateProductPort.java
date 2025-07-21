package br.com.fiap.TechFood.application.port.product.in;

import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductContractForm;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public interface CreateProductPort {

    ProductView createProduct(ProductContractForm productContractForm);
}
