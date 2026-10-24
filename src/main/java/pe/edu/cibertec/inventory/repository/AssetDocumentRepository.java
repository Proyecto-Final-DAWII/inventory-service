package pe.edu.cibertec.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.inventory.entity.AssetDocument;
import java.util.List;

public interface AssetDocumentRepository extends JpaRepository<AssetDocument, String> {
    List<AssetDocument> findByAssetIdOrderByUploadedAtDesc(String assetId);
}
