package br.com.fiap.TechFood.application.usecases.product;

import br.com.fiap.TechFood.application.port.product.in.CreateProductPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductContractForm;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

import java.util.Optional;

public class CreateProductUseCase implements CreateProductPort {

    private final ProductGatewayPort productGatewayPort;

    public CreateProductUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public ProductView createProduct(ProductContractForm productContractForm) {
        Optional<Product> possibleProductWith = productGatewayPort.findByName(productContractForm.getName());
        if (possibleProductWith.isPresent()) throw new IllegalStateException("Nome do produto já existe: " + productContractForm.getName());

        Product newProduct = new Product(productContractForm.getName(), productContractForm.getCategory(),
                productContractForm.getPrice(), productContractForm.getDescription(), productContractForm.getImages());

        return ProductView.from(productGatewayPort.save(newProduct));
    }
}