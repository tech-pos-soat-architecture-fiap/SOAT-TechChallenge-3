package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.port.payment.in.GetPaymentStatusPort;
import br.com.fiap.TechFood.application.port.payment.out.PaymentGatewayProcessor;
import br.com.fiap.TechFood.application.port.payment.out.PaymentRepositoryPort;
import br.com.fiap.TechFood.application.usecases.payment.GetPaymentStatusUseCase;
import br.com.fiap.TechFood.infrastructure.adapter.out.payment.gatewayProcessor.MockPaymentGatewayProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentDependencyInjectionConfig {

    @Bean
    public GetPaymentStatusPort getPaymentStatusPort(PaymentRepositoryPort paymentRepository) {
        return new GetPaymentStatusUseCase(paymentRepository);
    }

    @Bean
    public PaymentGatewayProcessor paymentGatewayProcessor() {
        return new MockPaymentGatewayProcessor();
    }
}
