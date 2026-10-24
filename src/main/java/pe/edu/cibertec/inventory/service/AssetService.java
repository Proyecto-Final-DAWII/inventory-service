package pe.edu.cibertec.inventory.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.cibertec.inventory.dto.AssetRequest;
import pe.edu.cibertec.inventory.dto.AssetResponse;
import pe.edu.cibertec.inventory.entity.Asset;
import pe.edu.cibertec.inventory.entity.AssetStatus;
import pe.edu.cibertec.inventory.messaging.AuditEventProducer;
import pe.edu.cibertec.inventory.repository.AssetRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AssetService {
    private final AssetRepository repository;
    private final AuditEventProducer auditEventProducer;

    public AssetService(AssetRepository repository, AuditEventProducer auditEventProducer) {
        this.repository = repository;
        this.auditEventProducer = auditEventProducer;
    }

    @Transactional
    public AssetResponse create(AssetRequest request) {
        if (repository.existsByIdOrSerialNumber(request.id(), request.serialNumber())) {
            throw new IllegalArgumentException("El código interno o número de serie ya está registrado");
        }
        Asset asset = new Asset();
        asset.setId(request.id());
        asset.setSerialNumber(request.serialNumber());
        asset.setName(request.name());
        asset.setCategory(request.category());
        asset.setBrand(request.brand());
        asset.setModel(request.model());
        asset.setAcquisitionDate(request.acquisitionDate());
        asset.setWarrantyExpiration(request.warrantyExpiration());
        asset.setStatus(parseStatus(request.status()));
        applyBusinessFields(asset, request);
        Asset saved = repository.save(asset);
        auditEventProducer.publish(saved.getId(), "ACTIVO_REGISTRADO", null, saved.getStatus().name());
        return toResponse(saved);
    }

    @Transactional
    public AssetResponse update(String id, AssetRequest request) {
        Asset asset = getAsset(id);
        asset.setName(request.name());
        asset.setCategory(request.category());
        asset.setBrand(request.brand());
        asset.setModel(request.model());
        asset.setAcquisitionDate(request.acquisitionDate());
        asset.setWarrantyExpiration(request.warrantyExpiration());
        if (request.status() != null && !request.status().isBlank()) {
            asset.setStatus(parseStatus(request.status()));
        }
        applyBusinessFields(asset, request);
        Asset saved = repository.save(asset);
        auditEventProducer.publish(id, "ACTIVO_ACTUALIZADO", null, saved.getStatus().name());
        return toResponse(saved);
    }

    @Transactional
    public void delete(String id) {
        Asset asset = getAsset(id);
        asset.setStatus(AssetStatus.DADO_DE_BAJA);
        repository.save(asset);
        auditEventProducer.publish(id, "ACTIVO_DADO_DE_BAJA", null, AssetStatus.DADO_DE_BAJA.name());
    }

    @Transactional(readOnly = true)
    public AssetResponse findById(String id) {
        return toResponse(getAsset(id));
    }

    @Transactional(readOnly = true)
    public List<AssetResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public AssetResponse updateStatus(String id, AssetStatus status) {
        Asset asset = getAsset(id);
        AssetStatus previous = asset.getStatus();
        asset.setStatus(status);
        Asset saved = repository.save(asset);
        auditEventProducer.publish(id, "ESTADO_ACTIVO_ACTUALIZADO", previous.name(), status.name());
        return toResponse(saved);
    }

    private Asset getAsset(String id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Activo no encontrado"));
    }

    private AssetResponse toResponse(Asset asset) {
        return new AssetResponse(asset.getId(), asset.getSerialNumber(), asset.getName(), asset.getCategory(),
                asset.getBrand(), asset.getModel(), asset.getAcquisitionDate(), asset.getWarrantyExpiration(),
                asset.getStatus(), asset.getLocation(), asset.getArea(), asset.getPurchasePrice(),
                asset.getCurrency(), asset.getNotes());
    }

    private void applyBusinessFields(Asset asset, AssetRequest request) {
        asset.setLocation(request.location());
        asset.setArea(request.area());
        asset.setPurchasePrice(request.purchasePrice());
        asset.setCurrency(request.currency());
        asset.setNotes(request.notes());
    }

    private AssetStatus parseStatus(String status) {
        if (status == null || status.isBlank()) return AssetStatus.DISPONIBLE;
        return switch (status) {
            case "AVAILABLE" -> AssetStatus.DISPONIBLE;
            case "ASSIGNED" -> AssetStatus.ASIGNADO;
            case "MAINTENANCE" -> AssetStatus.EN_MANTENIMIENTO;
            case "REPAIR" -> AssetStatus.EN_REPARACION;
            case "LOST" -> AssetStatus.EXTRAVIADO;
            case "RETIRED" -> AssetStatus.DADO_DE_BAJA;
            default -> AssetStatus.valueOf(status);
        };
    }
}
