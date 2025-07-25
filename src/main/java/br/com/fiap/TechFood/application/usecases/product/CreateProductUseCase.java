package br.com.fiap.TechFood.application.usecases.product;

import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.port.product.CreateProductPort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.infrastructure.result.OperationResult;
import br.com.fiap.TechFood.infrastructure.result.OperationStatus;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.CreateProductForm;

public class CreateProductUseCase implements CreateProductPort {

    private final ProductServicePort productServicePort;

    public CreateProductUseCase(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @Override
    public OperationResult<Product> createProduct(CreateProductForm createProductForm) {
        if (productServicePort.findByName(createProductForm.name()).isPresent()) {
            return OperationResult.failure(OperationStatus.RESOURCE_ALREADY_EXISTS,
                    "Produto com nome '" + createProductForm.name() + "' já existe.");
        }

        Product newProduct = new Product(createProductForm.name(), createProductForm.category(),
                createProductForm.price(), createProductForm.description(), createProductForm.images());

        return OperationResult.success(productServicePort.create(newProduct), "Produto criado com sucesso.");
    }
}