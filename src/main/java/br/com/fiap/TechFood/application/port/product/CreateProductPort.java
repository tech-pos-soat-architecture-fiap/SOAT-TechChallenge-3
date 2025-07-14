package br.com.fiap.TechFood.application.port.product;

import br.com.fiap.TechFood.application.core.usecases.product.domain.Product;
import br.com.fiap.TechFood.infrastructure.result.OperationResult;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.CreateProductForm;

public interface CreateProductPort {

    OperationResult<Product> createProduct(CreateProductForm createProductForm);
}
