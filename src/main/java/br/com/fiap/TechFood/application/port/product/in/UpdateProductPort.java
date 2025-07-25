package br.com.fiap.TechFood.application.port.product.in;

import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductContractForm;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public interface UpdateProductPort {

    ProductView update(Long id, ProductContractForm productContractForm);
}
