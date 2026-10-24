package pe.edu.cibertec.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.cibertec.inventory.entity.Asset;
import pe.edu.cibertec.inventory.entity.AssetStatus;
import pe.edu.cibertec.inventory.repository.AssetRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dashboard")
public class DashboardController {
    private static final DateTimeFormatter MONTH_FORMAT = DateTimeFormatter.ofPattern("MMM yyyy");

    private final AssetRepository assetRepository;

    public DashboardController(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @GetMapping("/summary")
    public DashboardSummary summary() {
        List<Asset> assets = assetRepository.findAll();
        int total = assets.size();
        int assigned = countByStatus(assets, AssetStatus.ASIGNADO);
        int available = countByStatus(assets, AssetStatus.DISPONIBLE);
        int maintenance = countByStatus(assets, AssetStatus.EN_MANTENIMIENTO)
                + countByStatus(assets, AssetStatus.EN_REPARACION);
        int retired = countByStatus(assets, AssetStatus.DADO_DE_BAJA);

        return new DashboardSummary(
                List.of(
                        metric("total", "Activos totales", total, "assets", "primary"),
                        metric("assigned", "Asignados", assigned, "user-check", "success"),
                        metric("available", "Disponibles", available, "package-check", "info"),
                        metric("maintenance", "En mantenimiento", maintenance, "wrench", "warning"),
                        metric("warranty", "Garantías por vencer", expiringWarranties(assets), "shield-alert", "danger"),
                        metric("retired", "Dados de baja", retired, "archive", "neutral")
                ),
                distribution(assets, Asset::getCategory, total),
                distribution(assets, asset -> frontendStatus(asset.getStatus()), total),
                List.of(),
                List.of(),
                List.of(new MaintenanceMonth(YearMonth.now().format(MONTH_FORMAT), maintenance))
        );
    }

    private int countByStatus(List<Asset> assets, AssetStatus status) {
        return (int) assets.stream().filter(asset -> asset.getStatus() == status).count();
    }

    private int expiringWarranties(List<Asset> assets) {
        LocalDate today = LocalDate.now();
        LocalDate limit = today.plusDays(30);
        return (int) assets.stream()
                .map(Asset::getWarrantyExpiration)
                .filter(date -> date != null && !date.isBefore(today) && !date.isAfter(limit))
                .count();
    }

    private Metric metric(String key, String label, int value, String icon, String tone) {
        return new Metric(key, label, value, icon, tone);
    }

    private <T> List<DistributionItem> distribution(
            List<Asset> assets, Function<Asset, T> keyExtractor, int total) {
        return assets.stream()
                .collect(Collectors.groupingBy(keyExtractor, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> new DistributionItem(
                        String.valueOf(entry.getKey()),
                        String.valueOf(entry.getKey()),
                        entry.getValue().intValue(),
                        total == 0 ? 0 : Math.round(entry.getValue() * 100f / total)))
                .toList();
    }

    private String frontendStatus(AssetStatus status) {
        return switch (status) {
            case DISPONIBLE -> "AVAILABLE";
            case ASIGNADO -> "ASSIGNED";
            case EN_MANTENIMIENTO -> "MAINTENANCE";
            case EN_REPARACION -> "REPAIR";
            case EXTRAVIADO -> "LOST";
            case DADO_DE_BAJA -> "RETIRED";
        };
    }

    public record DashboardSummary(
            List<Metric> metrics,
            List<DistributionItem> assetsByCategory,
            List<DistributionItem> assetsByStatus,
            List<Object> recentMovements,
            List<Object> upcomingAlerts,
            List<MaintenanceMonth> maintenanceByMonth) {
    }

    public record Metric(String key, String label, int value, String icon, String tone) {
    }

    public record DistributionItem(String key, String label, int value, int percentage) {
    }

    public record MaintenanceMonth(String month, int total) {
    }
}
