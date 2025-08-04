package br.com.fiap.TechFood.infrastructure.adapter.out.payment.gatewayProcessor;

import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;

public class MockPaymentQRCodeView implements PaymentQRCodeView {

    private final String storeOrderId;
    private final String qrCode;
    private final Long paymentId;

    public MockPaymentQRCodeView(String storeOrderId, String qrCode, Long paymentId) {
        this.storeOrderId = storeOrderId;
        this.qrCode = qrCode;
        this.paymentId = paymentId;
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
}
