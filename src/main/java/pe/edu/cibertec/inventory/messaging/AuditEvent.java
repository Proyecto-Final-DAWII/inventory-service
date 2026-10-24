package pe.edu.cibertec.inventory.messaging;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import pe.edu.cibertec.inventory.config.rabbitmq.RabbitMqDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class AuditEvent extends RabbitMqDto<Void> {
    private String assetId;
    private String userId;
    private String actionType;
    private String previousState;
    private String newState;
    private String details;
    private LocalDateTime eventDate;
}
