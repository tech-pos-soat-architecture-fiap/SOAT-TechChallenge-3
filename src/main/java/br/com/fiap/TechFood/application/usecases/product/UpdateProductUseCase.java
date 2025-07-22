package br.com.fiap.TechFood.application.usecases.product;

import br.com.fiap.TechFood.application.port.product.in.UpdateProductPort;
import br.com.fiap.TechFood.application.port.product.out.ProductGatewayPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;
import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductContractForm;
import br.com.fiap.TechFood.infrastructure.adapter.in.product.ProductView;

public class UpdateProductUseCase implements UpdateProductPort {

    private final ProductGatewayPort productGatewayPort;

    public UpdateProductUseCase(ProductGatewayPort productGatewayPort) {
        this.productGatewayPort = productGatewayPort;
    }

    @Override
    public ProductView update(Long id, ProductContractForm productContractForm) {
        productGatewayPort.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado: " + id));

        Product updatedProduct = new Product(id, productContractForm.getName(), productContractForm.getCategory(),
                productContractForm.getPrice(), productContractForm.getDescription(), productContractForm.getImages());

        return ProductView.from(productGatewayPort.save(updatedProduct));
    }
}
