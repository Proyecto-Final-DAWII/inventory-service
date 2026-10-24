package pe.edu.cibertec.inventory.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.inventory.dto.AssetRequest;
import pe.edu.cibertec.inventory.dto.AssetResponse;
import pe.edu.cibertec.inventory.dto.AssetStatusRequest;
import pe.edu.cibertec.inventory.service.AssetService;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping({"/api/inventory", "/api/v1/assets"})
public class AssetController {
    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssetResponse create(@Valid @RequestBody AssetRequest request) {
        return service.create(request);
    }

    @GetMapping
    public AssetPage findAll(
            @RequestParam(defaultValue = "") String search,
            @RequestParam(defaultValue = "") String status,
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<AssetResponse> filtered = service.findAll().stream()
                .filter(asset -> search.isBlank() || searchable(asset).contains(search.toLowerCase(Locale.ROOT)))
                .filter(asset -> status.isBlank() || asset.status().name().equalsIgnoreCase(status))
                .filter(asset -> category.isBlank() || asset.category().equalsIgnoreCase(category))
                .toList();
        int safeSize = Math.max(1, Math.min(size, 100));
        int safePage = Math.max(0, page);
        int from = Math.min(safePage * safeSize, filtered.size());
        int to = Math.min(from + safeSize, filtered.size());
        return new AssetPage(filtered.subList(from, to), safePage, safeSize, filtered.size(),
                filtered.isEmpty() ? 0 : (int) Math.ceil((double) filtered.size() / safeSize));
    }

    private String searchable(AssetResponse asset) {
        return String.join(" ", asset.id(), asset.serialNumber(), asset.name(),
                asset.category(), asset.brand() == null ? "" : asset.brand(),
                asset.model() == null ? "" : asset.model()).toLowerCase(Locale.ROOT);
    }

    public record AssetPage(List<AssetResponse> content, int page, int size, int totalElements, int totalPages) {
    }

    @GetMapping("/{id}")
    public AssetResponse findById(@PathVariable String id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public AssetResponse update(@PathVariable String id, @Valid @RequestBody AssetRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        service.delete(id);
    }

    @PutMapping("/{id}/status")
    public AssetResponse updateStatus(@PathVariable String id, @Valid @RequestBody AssetStatusRequest request) {
        return service.updateStatus(id, request.status());
    }
}
