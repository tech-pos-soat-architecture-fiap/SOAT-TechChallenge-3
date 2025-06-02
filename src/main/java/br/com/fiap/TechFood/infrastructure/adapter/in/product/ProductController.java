package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ProductController {

    private final ProductServicePort productServicePort;

    public ProductController(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @PostMapping("/products")
    public ResponseEntity<String> create(@Valid @RequestBody ProductForm productForm) {
        Product product = productServicePort.create(productForm.toProduct());
        URI uri = URI.create("/products/" + product.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @Valid @RequestBody ProductForm productForm) {
        Product updatedProduct = productServicePort.update(id, productForm.toProduct());
        URI uri = URI.create("/products/" + updatedProduct.getId());
        return ResponseEntity.ok().location(uri).build();
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<PagePort<ProductView>> showByCategory(@PathVariable("category") String category,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        ProductCategory productCategory = ProductCategory.findByName(category).orElseThrow(NotFoundException::new);
        PagePort<ProductView> productView = productServicePort.findAllByCategory(productCategory, page, size).map(ProductView::from);
        return ResponseEntity.ok(productView);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductView> showById(@PathVariable("id") Long id) {
        ProductView productView = productServicePort.findById(id).map(ProductView::from).orElseThrow(NotFoundException::new);
        return ResponseEntity.ok(productView);
    }

    @GetMapping("/products")
    public ResponseEntity<PagePort<ProductView>> showAll(@RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        PagePort<ProductView> productsView = productServicePort.findAll(page, size).map(ProductView::from);
        return ResponseEntity.ok(productsView);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Product product = productServicePort.findById(id).orElseThrow(NotFoundException::new);
        productServicePort.remove(product);
        return ResponseEntity.ok().build();
    }
}
