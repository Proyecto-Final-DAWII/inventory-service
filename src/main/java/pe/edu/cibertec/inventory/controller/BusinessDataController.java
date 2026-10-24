package pe.edu.cibertec.inventory.controller;

import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.inventory.entity.*;
import pe.edu.cibertec.inventory.repository.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BusinessDataController {
    private final SoftwareLicenseRepository licenses;
    private final IncidentRecordRepository incidents;
    private final AlertRecordRepository alerts;

    public BusinessDataController(SoftwareLicenseRepository licenses, IncidentRecordRepository incidents,
                                  AlertRecordRepository alerts) {
        this.licenses = licenses; this.incidents = incidents; this.alerts = alerts;
    }

    @GetMapping("/licenses")
    public List<SoftwareLicense> licenses() { return licenses.findAll(); }

    @GetMapping("/incidents")
    public List<IncidentRecord> incidents() { return incidents.findAll(); }

    @GetMapping("/alerts")
    public List<AlertRecord> alerts() { return alerts.findAll(); }

    @PatchMapping("/alerts/{id}/read")
    public void markRead(@PathVariable String id) {
        alerts.findById(id).ifPresent(alert -> { alert.setRead(true); alerts.save(alert); });
    }

    @PatchMapping("/alerts/read-all")
    public void markAllRead() {
        List<AlertRecord> allAlerts = alerts.findAll();
        allAlerts.forEach(alert -> alert.setRead(true));
        alerts.saveAll(allAlerts);
    }

    @PatchMapping("/incidents/{id}")
    public IncidentRecord updateIncident(@PathVariable String id, @RequestBody IncidentStatusRequest request) {
        IncidentRecord incident = incidents.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incidencia no encontrada: " + id));
        incident.setStatus(request.status());
        incident.setUpdatedAt(java.time.Instant.now());
        return incidents.save(incident);
    }

    public record IncidentStatusRequest(String status) {}
}
