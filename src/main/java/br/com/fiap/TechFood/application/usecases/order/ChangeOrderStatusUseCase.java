package br.com.fiap.TechFood.application.usecases.order;

public interface ChangeOrderStatusUseCase {
    Order changeStatus(Long orderId);
}
