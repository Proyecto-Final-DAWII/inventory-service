package pe.edu.cibertec.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "incident_records")
public class IncidentRecord {
    @Id private String id;
    private String code;
    private String assetId;
    private String assetCode;
    private String assetName;
    private String reportedBy;
    private String title;
    private String description;
    private String priority;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
    private String assignedTo;

    public String getId() { return id; } public void setId(String v) { id = v; }
    public String getCode() { return code; } public void setCode(String v) { code = v; }
    public String getAssetId() { return assetId; } public void setAssetId(String v) { assetId = v; }
    public String getAssetCode() { return assetCode; } public void setAssetCode(String v) { assetCode = v; }
    public String getAssetName() { return assetName; } public void setAssetName(String v) { assetName = v; }
    public String getReportedBy() { return reportedBy; } public void setReportedBy(String v) { reportedBy = v; }
    public String getTitle() { return title; } public void setTitle(String v) { title = v; }
    public String getDescription() { return description; } public void setDescription(String v) { description = v; }
    public String getPriority() { return priority; } public void setPriority(String v) { priority = v; }
    public String getStatus() { return status; } public void setStatus(String v) { status = v; }
    public Instant getCreatedAt() { return createdAt; } public void setCreatedAt(Instant v) { createdAt = v; }
    public Instant getUpdatedAt() { return updatedAt; } public void setUpdatedAt(Instant v) { updatedAt = v; }
    public String getAssignedTo() { return assignedTo; } public void setAssignedTo(String v) { assignedTo = v; }
}
