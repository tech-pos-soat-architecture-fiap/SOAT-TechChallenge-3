package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.domain.product.ImageContract;

import java.math.BigDecimal;
import java.util.Set;

public interface ProductContractForm {

    String getName();
    String getCategory();
    BigDecimal getPrice();
    String getDescription();
    Set<? extends ImageContract> getImages();
}
