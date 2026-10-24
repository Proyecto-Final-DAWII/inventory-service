package pe.edu.cibertec.inventory.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "software_licenses")
public class SoftwareLicense {
    @Id
    private String id;
    private String productName;
    private String vendor;
    private String type;
    private String status;
    private int totalSeats;
    private int assignedSeats;
    private LocalDate startsAt;
    private LocalDate expiresAt;
    private double annualCost;
    private String currency;
    private String ownerArea;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getVendor() { return vendor; }
    public void setVendor(String vendor) { this.vendor = vendor; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getTotalSeats() { return totalSeats; }
    public void setTotalSeats(int totalSeats) { this.totalSeats = totalSeats; }
    public int getAssignedSeats() { return assignedSeats; }
    public void setAssignedSeats(int assignedSeats) { this.assignedSeats = assignedSeats; }
    public LocalDate getStartsAt() { return startsAt; }
    public void setStartsAt(LocalDate startsAt) { this.startsAt = startsAt; }
    public LocalDate getExpiresAt() { return expiresAt; }
    public void setExpiresAt(LocalDate expiresAt) { this.expiresAt = expiresAt; }
    public double getAnnualCost() { return annualCost; }
    public void setAnnualCost(double annualCost) { this.annualCost = annualCost; }
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    public String getOwnerArea() { return ownerArea; }
    public void setOwnerArea(String ownerArea) { this.ownerArea = ownerArea; }
}
