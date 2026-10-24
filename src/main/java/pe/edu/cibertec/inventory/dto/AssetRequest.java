package pe.edu.cibertec.inventory.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record AssetRequest(
        @NotBlank String id,
        @NotBlank String serialNumber,
        @NotBlank String name,
        @NotBlank String category,
        String brand,
        String model,
        LocalDate acquisitionDate,
        LocalDate warrantyExpiration,
        String status,
        String location,
        String area,
        Double purchasePrice,
        String currency,
        String notes
) {
    public AssetRequest(String id, String serialNumber, String name, String category,
                        String brand, String model, LocalDate acquisitionDate,
                        LocalDate warrantyExpiration) {
        this(id, serialNumber, name, category, brand, model, acquisitionDate,
                warrantyExpiration, null, null, null, null, null, null);
    }
}
