package pe.edu.cibertec.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "alert_records")
public class AlertRecord {
    @Id private String id;
    private String type;
    private String severity;
    private String title;
    private String message;
    private LocalDate dueAt;
    @Column(name = "read_flag")
    private boolean read;
    private Instant createdAt;
    private String route;

    public String getId() { return id; } public void setId(String v) { id = v; }
    public String getType() { return type; } public void setType(String v) { type = v; }
    public String getSeverity() { return severity; } public void setSeverity(String v) { severity = v; }
    public String getTitle() { return title; } public void setTitle(String v) { title = v; }
    public String getMessage() { return message; } public void setMessage(String v) { message = v; }
    public LocalDate getDueAt() { return dueAt; } public void setDueAt(LocalDate v) { dueAt = v; }
    public boolean isRead() { return read; } public void setRead(boolean v) { read = v; }
    public Instant getCreatedAt() { return createdAt; } public void setCreatedAt(Instant v) { createdAt = v; }
    public String getRoute() { return route; } public void setRoute(String v) { route = v; }
}
