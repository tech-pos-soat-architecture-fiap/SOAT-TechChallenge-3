package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.core.usecases.product.domain.Product;
import br.com.fiap.TechFood.application.core.usecases.product.domain.ProductImage;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductView(String name,
                          String category,
                          BigDecimal price,
                          String description,
                          Set<ProductImageView> images) {

    public static ProductView from(Product product) {
        return new ProductView(product.getName(),
                product.getCategoryName(),
                product.getPrice(),
                product.getDescription(),
                product.getImages().stream().map(ProductImageView::from).collect(Collectors.toSet()));
    }

    private record ProductImageView(String url,
                            String description,
                            int position) {

        private static ProductImageView from(ProductImage productImage) {
            return new ProductImageView(productImage.getUrl(),
                    productImage.getDescription(),
                    productImage.getPosition());
        }
    }
}
