package pe.edu.cibertec.inventory.messaging;

import org.springframework.stereotype.Component;
import pe.edu.cibertec.inventory.config.rabbitmq.RabbitMqTemplate;
import pe.edu.cibertec.inventory.config.rabbitmq.RabbitProperties;

import java.time.LocalDateTime;

@Component
public class AuditEventProducer {
    private final RabbitMqTemplate template;
    private final RabbitProperties properties;

    public AuditEventProducer(RabbitMqTemplate template, RabbitProperties properties) {
        this.template = template;
        this.properties = properties;
    }

    public void publish(String assetId, String action, String previousState, String newState) {
        AuditEvent event = AuditEvent.builder()
                .assetId(assetId)
                .userId("system")
                .actionType(action)
                .previousState(previousState)
                .newState(newState)
                .details("Cambio registrado por inventory-service")
                .eventDate(LocalDateTime.now())
                .build();
        template.convertAndSend(properties.getRoute("audit"), event);
    }
}
