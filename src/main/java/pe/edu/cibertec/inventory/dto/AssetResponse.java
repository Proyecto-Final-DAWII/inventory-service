package pe.edu.cibertec.inventory.dto;

import pe.edu.cibertec.inventory.entity.AssetStatus;

import java.time.LocalDate;

public record AssetResponse(
        String id,
        String serialNumber,
        String name,
        String category,
        String brand,
        String model,
        LocalDate acquisitionDate,
        LocalDate warrantyExpiration,
        AssetStatus status,
        String location,
        String area,
        Double purchasePrice,
        String currency,
        String notes
) {
}
