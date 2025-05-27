package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.ProductCategory;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.ProductServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

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

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductView> showById(@PathVariable("id") Long id) {
        ProductView productView = productServicePort.findById(id).map(ProductView::of).orElse(null);
        return ResponseEntity.ok(productView);
    }

    @GetMapping("/products/category/{category}")
    public ResponseEntity<PagePort<ProductView>> showByCategory(@PathVariable("category") String category, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PagePort<ProductView> productView = productServicePort.findAllByCategory(ProductCategory.findByName(category), page, size).map(ProductView::of);
        return ResponseEntity.ok(productView);
    }

    @GetMapping("/products")
    public ResponseEntity<PagePort<ProductView>> showAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PagePort<ProductView> productsView = productServicePort.findAll(page, size).map(ProductView::of);
        return ResponseEntity.ok(productsView);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional<Product> possibleProduct = productServicePort.findById(id);
        if (possibleProduct.isEmpty()) return ResponseEntity.notFound().build();

        productServicePort.remove(possibleProduct.get());
        return ResponseEntity.ok().build();
    }
}
