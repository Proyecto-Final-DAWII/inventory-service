package pe.edu.cibertec.inventory.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.cibertec.inventory.entity.*;
import pe.edu.cibertec.inventory.repository.*;

import java.time.Instant;
import java.time.LocalDate;

@Configuration
public class BusinessDataInitializer {
    @Bean
    CommandLineRunner initBusinessData(SoftwareLicenseRepository licenses,
                                       IncidentRecordRepository incidents,
                                       AlertRecordRepository alerts,
                                       AssetDocumentRepository documents,
                                       AssetHistoryRepository history) {
        return args -> {
            seedLicenses(licenses);
            seedIncidents(incidents);
            seedAlerts(alerts);
            seedDocuments(documents);
            seedHistory(history);
        };
    }

    private void seedLicenses(SoftwareLicenseRepository repository) {
        saveIfMissing(repository, license("lic-m365", "Microsoft 365 Business Premium", "Microsoft", "SUBSCRIPTION", "ACTIVE", 420, 387, 4200, "Tecnología", 12));
        saveIfMissing(repository, license("lic-adobe", "Adobe Creative Cloud", "Adobe", "SUBSCRIPTION", "EXPIRING", 50, 30, 8400, "Marketing", 22));
        saveIfMissing(repository, license("lic-jetbrains", "JetBrains All Products", "JetBrains", "SUBSCRIPTION", "ACTIVE", 25, 18, 5600, "Desarrollo", 210));
        saveIfMissing(repository, license("lic-windows", "Windows 11 Pro", "Microsoft", "PERPETUAL", "ACTIVE", 35, 29, 9100, "Tecnología", 900));
        saveIfMissing(repository, license("lic-visio", "Visio Standard", "Microsoft", "OEM", "EXPIRED", 12, 12, 1800, "Operaciones", -30));
        saveIfMissing(repository, license("lic-autocad", "AutoCAD LT", "Autodesk", "SUBSCRIPTION", "ACTIVE", 18, 14, 12600, "Ingeniería", 145));
        saveIfMissing(repository, license("lic-github", "GitHub Enterprise", "GitHub", "SUBSCRIPTION", "ACTIVE", 80, 61, 17400, "Desarrollo", 260));
        saveIfMissing(repository, license("lic-zoom", "Zoom Workplace", "Zoom", "SUBSCRIPTION", "EXPIRING", 120, 94, 7200, "Recursos Humanos", 35));
        saveIfMissing(repository, license("lic-vmware", "VMware vSphere", "Broadcom", "PERPETUAL", "ACTIVE", 8, 8, 22400, "Infraestructura", 480));
        saveIfMissing(repository, license("lic-kaspersky", "Kaspersky Endpoint", "Kaspersky", "SUBSCRIPTION", "ACTIVE", 150, 132, 9800, "Seguridad", 175));
        saveIfMissing(repository, license("lic-powerbi", "Power BI Pro", "Microsoft", "SUBSCRIPTION", "ACTIVE", 45, 39, 8100, "Finanzas", 90));
        saveIfMissing(repository, license("lic-slack", "Slack Business+", "Salesforce", "SUBSCRIPTION", "EXPIRED", 60, 57, 6500, "Operaciones", -12));
    }

    private void seedIncidents(IncidentRecordRepository repository) {
        saveIfMissing(repository, incident("inc-0107", "INC-2026-0107", "ast-pro-013", "ast-pro-013", "Impresora HP LaserJet", "soporte", "Impresora no imprime", "Atascos recurrentes y baja calidad de impresión.", "HIGH", "IN_MAINTENANCE"));
        saveIfMissing(repository, incident("inc-0108", "INC-2026-0108", "ast-iph-009", "ast-iph-009", "iPhone 15 Pro", "admin", "Equipo extraviado", "El colaborador reportó la pérdida durante una visita.", "CRITICAL", "OPEN"));
        saveIfMissing(repository, incident("inc-0109", "INC-2026-0109", "ast-hpe-010", "ast-hpe-010", "Servidor ProLiant ML350", "soporte", "Alerta de temperatura", "El servidor supera el umbral recomendado.", "MEDIUM", "IN_REVIEW"));
        saveIfMissing(repository, incident("inc-0110", "INC-2026-0110", "ast-dell-019", "ast-dell-019", "Dell Latitude 7420", "mesa-ayuda", "Pantalla con líneas", "La pantalla presenta líneas verticales intermitentes.", "HIGH", "OPEN"));
        saveIfMissing(repository, incident("inc-0111", "INC-2026-0111", "ast-cis-022", "ast-cis-022", "Access Point WiFi 6", "redes", "Pérdida de conectividad", "El punto de acceso pierde conexión durante la tarde.", "MEDIUM", "IN_REVIEW"));
        saveIfMissing(repository, incident("inc-0112", "INC-2026-0112", "ast-epson-025", "ast-epson-025", "Impresora Epson EcoTank", "operaciones", "Bajo nivel de tinta", "Se requiere reposición de tinta negra.", "LOW", "RESOLVED"));
        saveIfMissing(repository, incident("inc-0113", "INC-2026-0113", "ast-ipad-027", "ast-ipad-027", "iPad Air", "soporte", "Batería degradada", "La batería dura menos de cuatro horas.", "MEDIUM", "IN_MAINTENANCE"));
        saveIfMissing(repository, incident("inc-0114", "INC-2026-0114", "ast-fort-023", "ast-fort-023", "Firewall FortiGate", "seguridad", "Regla bloqueando servicio", "Una regla reciente bloquea el acceso a un proveedor.", "HIGH", "IN_REVIEW"));
        saveIfMissing(repository, incident("inc-0115", "INC-2026-0115", "ast-mon-021", "ast-mon-021", "Monitor Samsung Smart", "marketing", "Monitor sin señal", "El monitor no detecta la estación de trabajo.", "LOW", "RESOLVED"));
    }

    private void seedAlerts(AlertRecordRepository repository) {
        saveIfMissing(repository, alert("alt-001", "WARRANTY", "WARNING", "Garantía próxima a vencer", "La garantía del HP EliteBook vence próximamente.", LocalDate.now().plusDays(18), "/assets/ast-hp-005"));
        saveIfMissing(repository, alert("alt-002", "LICENSE", "WARNING", "Renovación de Adobe Creative Cloud", "La suscripción requiere renovación.", LocalDate.now().plusDays(22), "/licenses"));
        saveIfMissing(repository, alert("alt-003", "INCIDENT", "CRITICAL", "Incidencia crítica sin resolver", "El iPhone 15 Pro continúa reportado como extraviado.", null, "/incidents"));
        saveIfMissing(repository, alert("alt-004", "MAINTENANCE", "INFO", "Mantenimiento en curso", "Hay equipos en mantenimiento preventivo.", LocalDate.now().plusDays(5), "/maintenance"));
        saveIfMissing(repository, alert("alt-005", "WARRANTY", "CRITICAL", "Garantía vencida", "La garantía del Dell Latitude 7420 ya venció.", LocalDate.now().minusDays(2), "/assets/ast-dell-019"));
        saveIfMissing(repository, alert("alt-006", "LICENSE", "INFO", "Licencias con baja disponibilidad", "GitHub Enterprise tiene menos de 20 asientos disponibles.", null, "/licenses"));
        saveIfMissing(repository, alert("alt-007", "INCIDENT", "WARNING", "Incidencias pendientes", "Hay incidencias abiertas que requieren atención.", null, "/incidents"));
        saveIfMissing(repository, alert("alt-008", "SECURITY", "CRITICAL", "Revisión de firewall requerida", "Existe una incidencia de seguridad en el firewall.", null, "/incidents"));
        saveIfMissing(repository, alert("alt-009", "ASSET", "INFO", "Nuevos activos registrados", "Se incorporaron nuevos dispositivos al inventario.", null, "/assets"));
        saveIfMissing(repository, alert("alt-010", "LICENSE", "WARNING", "Slack requiere renovación", "La licencia de Slack se encuentra vencida.", LocalDate.now().minusDays(12), "/licenses"));
    }

    private void seedDocuments(AssetDocumentRepository repository) {
        saveIfMissing(repository, document("ast-hp-005", "factura-hp-elitebook.pdf", "application/pdf", "INVOICE"));
        saveIfMissing(repository, document("ast-hp-005", "garantia-hp-elitebook.pdf", "application/pdf", "WARRANTY"));
        saveIfMissing(repository, document("ast-dell-001", "acta-entrega-dell-001.pdf", "application/pdf", "DELIVERY_ACT"));
        saveIfMissing(repository, document("ast-mbp-002", "factura-macbook-pro.pdf", "application/pdf", "INVOICE"));
        saveIfMissing(repository, document("ast-iph-009", "foto-iphone-extraviado.jpg", "image/jpeg", "PHOTO"));
        saveIfMissing(repository, document("ast-fort-023", "configuracion-firewall.pdf", "application/pdf", "OTHER"));
        saveIfMissing(repository, document("ast-hpe-024", "garantia-servidor-hpe.pdf", "application/pdf", "WARRANTY"));
    }

    private void seedHistory(AssetHistoryRepository repository) {
        saveIfMissing(repository, history("ast-hp-005", "REGISTERED", "Activo registrado", "El activo fue incorporado al inventario.", 12));
        saveIfMissing(repository, history("ast-hp-005", "STATUS_CHANGED", "En mantenimiento", "El activo fue enviado a mantenimiento preventivo.", 2));
        saveIfMissing(repository, history("DEV-LAP-100", "REGISTERED", "Activo registrado", "El activo fue incorporado al inventario.", 8));
        saveIfMissing(repository, history("ast-dell-001", "REGISTERED", "Activo registrado", "Se registró el equipo Dell Latitude.", 30));
        saveIfMissing(repository, history("ast-dell-001", "ASSIGNED", "Activo asignado", "El equipo fue asignado al área de Finanzas.", 20));
        saveIfMissing(repository, history("ast-mbp-002", "REGISTERED", "Activo registrado", "Se registró el equipo MacBook Pro.", 18));
        saveIfMissing(repository, history("ast-mbp-002", "ASSIGNED", "Activo asignado", "El equipo fue asignado al área de Diseño.", 10));
        saveIfMissing(repository, history("ast-iph-009", "STATUS_CHANGED", "Activo extraviado", "El dispositivo fue marcado como extraviado.", 3));
        saveIfMissing(repository, history("ast-hpe-024", "MAINTENANCE_STARTED", "Mantenimiento iniciado", "Se inició mantenimiento preventivo del servidor.", 4));
        saveIfMissing(repository, history("ast-epson-025", "MAINTENANCE_COMPLETED", "Mantenimiento completado", "Se realizó limpieza y cambio de consumibles.", 1));
    }

    private <T> void saveIfMissing(org.springframework.data.jpa.repository.JpaRepository<T, String> repository, T entity) {
        if (!repository.existsById(entityId(entity))) repository.save(entity);
    }

    private String entityId(Object entity) {
        if (entity instanceof SoftwareLicense value) return value.getId();
        if (entity instanceof IncidentRecord value) return value.getId();
        if (entity instanceof AlertRecord value) return value.getId();
        if (entity instanceof AssetDocument value) return value.getId();
        return ((AssetHistoryRecord) entity).getId();
    }

    private AssetDocument document(String assetId, String fileName, String contentType, String type) {
        AssetDocument value = new AssetDocument();
        value.setId("doc-" + assetId + "-" + type.toLowerCase());
        value.setAssetId(assetId); value.setFileName(fileName); value.setContentType(contentType);
        value.setSize(245760); value.setType(type); value.setUploadedAt(Instant.now().minusSeconds(86400));
        value.setUploadedBy("admin@assetflow.com");
        return value;
    }

    private AssetHistoryRecord history(String assetId, String type, String title, String description, int daysAgo) {
        AssetHistoryRecord value = new AssetHistoryRecord();
        value.setId("hist-" + assetId + "-" + type.toLowerCase() + "-" + daysAgo);
        value.setAssetId(assetId); value.setType(type); value.setTitle(title);
        value.setDescription(description); value.setOccurredAt(Instant.now().minusSeconds(86400L * daysAgo));
        value.setActor("admin@assetflow.com");
        return value;
    }

    private SoftwareLicense license(String id, String product, String vendor, String type, String status,
                                    int total, int assigned, double cost, String area, int days) {
        SoftwareLicense value = new SoftwareLicense();
        value.setId(id); value.setProductName(product); value.setVendor(vendor); value.setType(type);
        value.setStatus(status); value.setTotalSeats(total); value.setAssignedSeats(assigned);
        value.setStartsAt(LocalDate.now().minusMonths(8)); value.setExpiresAt(LocalDate.now().plusDays(days));
        value.setAnnualCost(cost); value.setCurrency("PEN"); value.setOwnerArea(area);
        return value;
    }

    private IncidentRecord incident(String id, String code, String assetId, String assetCode, String assetName,
                                    String reporter, String title, String description, String priority, String status) {
        IncidentRecord value = new IncidentRecord();
        value.setId(id); value.setCode(code); value.setAssetId(assetId); value.setAssetCode(assetCode);
        value.setAssetName(assetName); value.setReportedBy(reporter); value.setTitle(title);
        value.setDescription(description); value.setPriority(priority); value.setStatus(status);
        value.setCreatedAt(Instant.now().minusSeconds(86400 * 3)); value.setUpdatedAt(Instant.now());
        return value;
    }

    private AlertRecord alert(String id, String type, String severity, String title, String message,
                              LocalDate dueAt, String route) {
        AlertRecord value = new AlertRecord();
        value.setId(id); value.setType(type); value.setSeverity(severity); value.setTitle(title);
        value.setMessage(message); value.setDueAt(dueAt); value.setRead(false);
        value.setCreatedAt(Instant.now()); value.setRoute(route);
        return value;
    }
}
