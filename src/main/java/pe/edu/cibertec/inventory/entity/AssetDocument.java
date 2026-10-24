package pe.edu.cibertec.inventory.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "asset_documents")
public class AssetDocument {
    @Id private String id;
    @Column(nullable = false) private String assetId;
    private String fileName;
    private String contentType;
    private long size;
    private String type;
    private Instant uploadedAt;
    private String uploadedBy;

    public String getId() { return id; } public void setId(String v) { id = v; }
    public String getAssetId() { return assetId; } public void setAssetId(String v) { assetId = v; }
    public String getFileName() { return fileName; } public void setFileName(String v) { fileName = v; }
    public String getContentType() { return contentType; } public void setContentType(String v) { contentType = v; }
    public long getSize() { return size; } public void setSize(long v) { size = v; }
    public String getType() { return type; } public void setType(String v) { type = v; }
    public Instant getUploadedAt() { return uploadedAt; } public void setUploadedAt(Instant v) { uploadedAt = v; }
    public String getUploadedBy() { return uploadedBy; } public void setUploadedBy(String v) { uploadedBy = v; }
}
