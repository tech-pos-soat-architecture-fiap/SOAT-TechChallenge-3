package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.usecases.product.domain.ProductCategory;
import br.com.fiap.TechFood.application.port.product.CreateProductPort;
import br.com.fiap.TechFood.application.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.port.PagePort;
import br.com.fiap.TechFood.application.port.product.FindProductsByCategoryPort;
import br.com.fiap.TechFood.application.shared.exception.NotFoundException;
import br.com.fiap.TechFood.infrastructure.result.OperationResult;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ProductController {

    private final CreateProductPort createProductUseCasePort;
    private final FindProductsByCategoryPort findProductsByCategoryPort;

    public ProductController(CreateProductPort createProductUseCasePort, FindProductsByCategoryPort findProductsByCategoryPort) {
        this.createProductUseCasePort = createProductUseCasePort;
        this.findProductsByCategoryPort = findProductsByCategoryPort;
    }

    @PostMapping("/products")
    public ResponseEntity<?> create(@Valid @RequestBody CreateProductForm createProductForm) {
        OperationResult<Product> result = createProductUseCasePort.createProduct(createProductForm);

        if (result.isFailure()) {
            return ResponseEntity
                    .status(result.httpStatus())
                    .body(result.toErrorResponse("/products"));
        }

        URI uri = URI.create("/products/" + result.data().getId());
        return ResponseEntity.created(uri).build();
    }

//    @PutMapping("/product/{id}")
//    public ResponseEntity<String> update(@PathVariable("id") Long id, @Valid @RequestBody ProductForm productForm) {
//        Product updatedProduct = productServicePort.update(id, productForm.toProduct());
//        URI uri = URI.create("/products/" + updatedProduct.getId());
//        return ResponseEntity.ok().location(uri).build();
//    }
//
    @GetMapping("/products/category/{category}")
    public ResponseEntity<PagePort<ProductView>> showByCategory(@PathVariable("category") String category,
                                                                @RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size) {
        ProductCategory productCategory = ProductCategory.findByName(category).orElseThrow(NotFoundException::new);
        PagePort<ProductView> productView = findProductsByCategoryPort.findAllByCategory(productCategory, page, size);
        return ResponseEntity.ok(productView);
    }
//
//    @GetMapping("/product/{id}")
//    public ResponseEntity<ProductView> showById(@PathVariable("id") Long id) {
//        ProductView productView = productServicePort.findById(id).map(ProductView::from).orElseThrow(NotFoundException::new);
//        return ResponseEntity.ok(productView);
//    }
//
//    @GetMapping("/products")
//    public ResponseEntity<PagePort<ProductView>> showAll(@RequestParam(defaultValue = "0") int page,
//                                                         @RequestParam(defaultValue = "10") int size) {
//        PagePort<ProductView> productsView = productServicePort.findAll(page, size).map(ProductView::from);
//        return ResponseEntity.ok(productsView);
//    }
//
//    @DeleteMapping("/product/{id}")
//    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
//        productServicePort.remove(id);
//        return ResponseEntity.ok().build();
//    }
}
