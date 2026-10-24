package pe.edu.cibertec.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.inventory.entity.Asset;

public interface AssetRepository extends JpaRepository<Asset, String> {
    boolean existsByIdOrSerialNumber(String id, String serialNumber);
}
