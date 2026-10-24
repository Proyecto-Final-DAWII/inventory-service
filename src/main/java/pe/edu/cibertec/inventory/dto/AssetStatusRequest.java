package pe.edu.cibertec.inventory.dto;

import jakarta.validation.constraints.NotNull;
import pe.edu.cibertec.inventory.entity.AssetStatus;

public record AssetStatusRequest(@NotNull AssetStatus status) {
}
