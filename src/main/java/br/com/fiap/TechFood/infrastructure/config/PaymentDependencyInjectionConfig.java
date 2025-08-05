package br.com.fiap.TechFood.infrastructure.config;

import br.com.fiap.TechFood.application.port.order.in.ChangeOrderStatusPort;
import br.com.fiap.TechFood.application.port.payment.in.GetPaymentStatusPort;
import br.com.fiap.TechFood.application.port.payment.in.PaymentWebhookProcessorPort;
import br.com.fiap.TechFood.application.port.payment.out.PaymentGatewayProcessor;
import br.com.fiap.TechFood.application.port.payment.out.PaymentRepositoryPort;
import br.com.fiap.TechFood.application.usecases.payment.GetPaymentStatusUseCase;
import br.com.fiap.TechFood.application.usecases.payment.PaymentWebhookProcessorUseCase;
import br.com.fiap.TechFood.infrastructure.adapter.out.payment.gatewayProcessor.MockPaymentGatewayProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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

    @Bean
    public PaymentWebhookProcessorPort paymentWebhookProcessorPort(ChangeOrderStatusPort changeOrderStatusPort, PaymentRepositoryPort paymentRepository) {
        return new PaymentWebhookProcessorUseCase(changeOrderStatusPort, paymentRepository);
    }

}
