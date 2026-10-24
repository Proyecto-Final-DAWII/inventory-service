package pe.edu.cibertec.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.inventory.entity.AssetHistoryRecord;
import java.util.List;

public interface AssetHistoryRepository extends JpaRepository<AssetHistoryRecord, String> {
    List<AssetHistoryRecord> findByAssetIdOrderByOccurredAtDesc(String assetId);
}
