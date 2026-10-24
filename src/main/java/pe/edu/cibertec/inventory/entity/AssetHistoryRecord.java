package pe.edu.cibertec.inventory.entity;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "asset_history")
public class AssetHistoryRecord {
    @Id private String id;
    private String assetId;
    private String type;
    private String title;
    private String description;
    private Instant occurredAt;
    private String actor;

    public String getId() { return id; } public void setId(String v) { id = v; }
    public String getAssetId() { return assetId; } public void setAssetId(String v) { assetId = v; }
    public String getType() { return type; } public void setType(String v) { type = v; }
    public String getTitle() { return title; } public void setTitle(String v) { title = v; }
    public String getDescription() { return description; } public void setDescription(String v) { description = v; }
    public Instant getOccurredAt() { return occurredAt; } public void setOccurredAt(Instant v) { occurredAt = v; }
    public String getActor() { return actor; } public void setActor(String v) { actor = v; }
}
