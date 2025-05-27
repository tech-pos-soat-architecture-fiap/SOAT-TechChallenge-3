package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.core.domain.product.Product;
import br.com.fiap.TechFood.application.core.domain.product.vo.ProductImage;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public record ProductView(String name,
                          String category,
                          BigDecimal price,
                          String description,
                          List<ProductImageView> images) {

    public static ProductView of(Product product) {
        List<ProductImageView> productImageViews = toProductImageViews(product.getImages());
        return new ProductView(product.getName(), product.getCategoryName(), product.getPrice(), product.getDescription(), productImageViews);
    }

    private static List<ProductImageView> toProductImageViews(List<ProductImage> productImages) {
        return productImages.stream().map(ProductImageView::of).collect(Collectors.toList());
    }

    record ProductImageView(String url,
                            String description,
                            int position) {

        private static ProductImageView of(ProductImage productImage) {
            return new ProductImageView(productImage.getUrl(),  productImage.getDescription(), productImage.getPosition());
        }
    }
}
