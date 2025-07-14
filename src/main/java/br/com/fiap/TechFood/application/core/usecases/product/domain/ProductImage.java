package br.com.fiap.TechFood.application.core.usecases.product.domain;

public class ProductImage implements ImageContact {

    private String url;
    private String description;
    private int position;

    public ProductImage() {}

    public ProductImage(String url, String description, int position) {
        this.url = url;
        this.description = description;
        this.position = position;
    }

    public ProductImage(ImageContact imageContact) {
        this.url = imageContact.getUrl();
        this.description = imageContact.getDescription();
        this.position = imageContact.getPosition();
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPosition() {
        return position;
    }
}
