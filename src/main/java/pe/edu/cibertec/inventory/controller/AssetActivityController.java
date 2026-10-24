package pe.edu.cibertec.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.cibertec.inventory.entity.*;
import pe.edu.cibertec.inventory.repository.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assets")
public class AssetActivityController {
    private final AssetDocumentRepository documents;
    private final AssetHistoryRepository history;

    public AssetActivityController(AssetDocumentRepository documents, AssetHistoryRepository history) {
        this.documents = documents; this.history = history;
    }

    @GetMapping("/{assetId}/documents")
    public List<AssetDocument> documents(@PathVariable String assetId) {
        return documents.findByAssetIdOrderByUploadedAtDesc(assetId);
    }

    @PostMapping("/{assetId}/documents")
    @ResponseStatus(HttpStatus.CREATED)
    public AssetDocument upload(@PathVariable String assetId, @RequestPart("file") MultipartFile file,
                                @RequestPart(value = "type", required = false) String type) throws java.io.IOException {
        AssetDocument document = new AssetDocument();
        document.setId(UUID.randomUUID().toString());
        document.setAssetId(assetId); document.setFileName(file.getOriginalFilename());
        document.setContentType(file.getContentType()); document.setSize(file.getSize());
        document.setType(type == null ? "OTHER" : type); document.setUploadedAt(Instant.now());
        document.setUploadedBy("current-user");
        return documents.save(document);
    }

    @DeleteMapping("/documents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) { documents.deleteById(id); }

    @GetMapping("/{assetId}/history")
    public List<AssetHistoryRecord> history(@PathVariable String assetId) {
        return history.findByAssetIdOrderByOccurredAtDesc(assetId);
    }
}
