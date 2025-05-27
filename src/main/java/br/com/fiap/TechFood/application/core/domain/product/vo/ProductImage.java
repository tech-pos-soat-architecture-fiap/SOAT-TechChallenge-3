package br.com.fiap.TechFood.application.core.domain.product.vo;

public class ProductImage {

    private String url;
    private String description;
    private int position;

    public ProductImage() {}

    public ProductImage(String url, String description, int position) {
        this.url = url;
        this.description = description;
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public int getPosition() {
        return position;
    }
}
