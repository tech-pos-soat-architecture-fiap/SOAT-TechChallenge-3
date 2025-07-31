package br.com.fiap.TechFood.infrastructure.adapter.out.payment.gatewayProcessor;

import br.com.fiap.TechFood.application.port.payment.PaymentQRCodeView;

public class MockPaymentQRCodeView implements PaymentQRCodeView {

    private final String storeOrderId;
    private final String qrCode;

    public MockPaymentQRCodeView(String storeOrderId, String qrCode) {
        this.storeOrderId = storeOrderId;
        this.qrCode = qrCode;
    }

    @Override
    public String getStoreOrderId() {
        return storeOrderId;
    }

    @Override
    public String getQRCodeData() {
        return qrCode;
    }
}
