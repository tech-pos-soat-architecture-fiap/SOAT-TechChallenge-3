package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.in.*;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class ProductController {

    private final CreateProductPort createProductUseCasePort;
    private final FindProductsByCategoryPort findProductsByCategoryPort;
    private final UpdateProductPort updateProductPort;
    private final FindProductPort findProductPort;
    private final FindAllProductsPort findAllProductsPort;
    private final RemoveProductPort removeProductPort;

    public ProductController(CreateProductPort createProductUseCasePort, FindProductsByCategoryPort findProductsByCategoryPort, UpdateProductPort updateProductPort, FindProductPort findProductPort, FindAllProductsPort findAllProductsPort, RemoveProductPort removeProductPort) {
        this.createProductUseCasePort = createProductUseCasePort;
        this.findProductsByCategoryPort = findProductsByCategoryPort;
        this.updateProductPort = updateProductPort;
        this.findProductPort = findProductPort;
        this.findAllProductsPort = findAllProductsPort;
        this.removeProductPort = removeProductPort;
    }

    @PostMapping("/products")
    public ResponseEntity<String> create(@Valid @RequestBody ProductForm createProductForm) {
        ProductView product = createProductUseCasePort.createProduct(createProductForm);

        URI uri = URI.create("/products/" + product.id());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @Valid @RequestBody ProductForm updateProductForm) {
        ProductView productView = updateProductPort.update(id, updateProductForm);

        URI uri = URI.create("/products/" + productView.id());
        return ResponseEntity.ok().location(uri).build();
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<PagePort<ProductView>> showByCategory(@PathVariable("category") String category,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) throws NotFoundException {
        PagePort<ProductView> productView = findProductsByCategoryPort.findAllByCategory(category, page, size);
        return ResponseEntity.ok(productView);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductView> showById(@PathVariable("id") Long id) {
        Optional<ProductView> possibleProduct = findProductPort.findById(id);

        return possibleProduct.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/products")
    public ResponseEntity<PagePort<ProductView>> showAll(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        PagePort<ProductView> productsView = findAllProductsPort.findAll(page, size);
        return ResponseEntity.ok(productsView);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        removeProductPort.remove(id);
        return ResponseEntity.noContent().build();
    }
}
