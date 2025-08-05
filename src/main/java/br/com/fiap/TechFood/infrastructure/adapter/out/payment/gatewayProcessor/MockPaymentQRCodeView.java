package br.com.fiap.TechFood.infrastructure.adapter.out.payment.gatewayProcessor;

import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;

public class MockPaymentQRCodeView implements PaymentQRCodeView {

    private final String storeOrderId;
    private final String qrCode;
    private final Long paymentId;
    private final Long orderId;

    public MockPaymentQRCodeView(String storeOrderId, String qrCode, Long paymentId, Long orderId) {
        this.storeOrderId = storeOrderId;
        this.qrCode = qrCode;
        this.paymentId = paymentId;
        this.orderId = orderId;
    }

    @Override
    public String getStoreOrderId() {
        return storeOrderId;
    }

    @Override
    public String getQRCodeData() {
        return qrCode;
    }

    @Override
    public Long getPaymentId() {
        return paymentId;
    }

    @Override
    public Long getOrderId() {
        return orderId;
    }
}
