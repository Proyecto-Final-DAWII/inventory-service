package pe.edu.cibertec.inventory.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.cibertec.inventory.dto.AssetRequest;
import pe.edu.cibertec.inventory.entity.AssetStatus;
import pe.edu.cibertec.inventory.messaging.AuditEventProducer;
import pe.edu.cibertec.inventory.repository.AssetRepository;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssetServiceTest {

    @Mock
    private AssetRepository repository;

    @Mock
    private AuditEventProducer auditEventProducer;

    @InjectMocks
    private AssetService service;

    @Test
    void registersAnAvailableAsset() {
        AssetRequest request = new AssetRequest("LAP-001", "SN-001", "Dell Latitude", "Laptop",
                "Dell", "7450", LocalDate.of(2026, 3, 2), LocalDate.of(2029, 3, 2));
        when(repository.existsByIdOrSerialNumber("LAP-001", "SN-001")).thenReturn(false);
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        var asset = service.create(request);

        assertThat(asset.id()).isEqualTo("LAP-001");
        assertThat(asset.status()).isEqualTo(AssetStatus.DISPONIBLE);
    }

    @Test
    void rejectsDuplicateInternalCodeOrSerialNumber() {
        AssetRequest request = new AssetRequest("LAP-001", "SN-001", "Dell Latitude", "Laptop",
                "Dell", "7450", null, null);
        when(repository.existsByIdOrSerialNumber("LAP-001", "SN-001")).thenReturn(true);

        assertThatThrownBy(() -> service.create(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("registrado");
    }
}
