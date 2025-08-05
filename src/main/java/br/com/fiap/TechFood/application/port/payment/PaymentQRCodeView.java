package br.com.fiap.TechFood.application.port.payment;

public interface PaymentQRCodeView {
    String getStoreOrderId();
    String getQRCodeData();
    Long getPaymentId();
    Long getOrderId();
}
