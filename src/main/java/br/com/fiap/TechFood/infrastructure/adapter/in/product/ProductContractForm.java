package br.com.fiap.TechFood.infrastructure.adapter.in.product;

import br.com.fiap.TechFood.application.usecases.product.domain.ImageContract;

import java.math.BigDecimal;
import java.util.Set;

public interface ProductContractForm {

    String getName();
    String getCategory();
    BigDecimal getPrice();
    String getDescription();
    Set<? extends ImageContract> getImages();
}
