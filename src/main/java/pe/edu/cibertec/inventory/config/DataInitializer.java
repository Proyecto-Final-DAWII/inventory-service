package pe.edu.cibertec.inventory.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pe.edu.cibertec.inventory.entity.Asset;
import pe.edu.cibertec.inventory.entity.AssetStatus;
import pe.edu.cibertec.inventory.repository.AssetRepository;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAssets(AssetRepository repository) {
        return args -> {
            saveIfMissing(repository, "ast-dell-001", "DL-923812", "Dell Latitude 7450", "LAPTOP", "Dell", "Latitude 7450", 6, 18, AssetStatus.ASIGNADO);
            saveIfMissing(repository, "ast-mbp-002", "MBP-2024-99", "MacBook Pro 16 M3", "LAPTOP", "Apple", "MacBook Pro 16", 3, 21, AssetStatus.ASIGNADO);
            saveIfMissing(repository, "ast-mon-003", "LG-4K-8821", "Monitor LG UltraFine 27", "MONITOR", "LG", "27UP850-W", 8, 4, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-len-004", "LN-THK-4421", "ThinkPad T14 Gen 5", "LAPTOP", "Lenovo", "ThinkPad T14", 5, 24, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-hp-005", "HP-840-7812", "HP EliteBook 840", "LAPTOP", "HP", "EliteBook 840 G10", 10, 2, AssetStatus.EN_MANTENIMIENTO);
            saveIfMissing(repository, "ast-cis-006", "CS-9300-1102", "Switch Catalyst 9300", "NETWORK", "Cisco", "C9300-24T", 14, 10, AssetStatus.EN_MANTENIMIENTO);
            saveIfMissing(repository, "ast-del-007", "DL-OPT-2026", "Dell OptiPlex 7010", "DESKTOP", "Dell", "OptiPlex 7010", 7, 20, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-sam-008", "SM-S24-9811", "Samsung Galaxy S24", "MOBILE", "Samsung", "Galaxy S24", 2, 10, AssetStatus.ASIGNADO);
            saveIfMissing(repository, "ast-iph-009", "IP-15P-8820", "iPhone 15 Pro", "MOBILE", "Apple", "iPhone 15 Pro", 9, 1, AssetStatus.EXTRAVIADO);
            saveIfMissing(repository, "ast-hpe-010", "HPE-ML-3320", "Servidor ProLiant ML350", "SERVER", "HPE", "ProLiant ML350 Gen10", 18, 30, AssetStatus.EN_REPARACION);
            saveIfMissing(repository, "ast-log-011", "LG-MX-2211", "Mouse MX Master 3S", "PERIPHERAL", "Logitech", "MX Master 3S", 4, 12, AssetStatus.ASIGNADO);
            saveIfMissing(repository, "ast-key-012", "KB-MX-2212", "Teclado MX Keys", "PERIPHERAL", "Logitech", "MX Keys", 4, 12, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-pro-013", "PR-HP-5512", "Impresora HP LaserJet", "OTHER", "HP", "LaserJet Pro 400", 12, 3, AssetStatus.EN_MANTENIMIENTO);
            saveIfMissing(repository, "ast-ada-014", "AD-24-5511", "Adaptador USB-C multipuerto", "PERIPHERAL", "Anker", "PowerExpand 8-in-1", 1, 12, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-ups-015", "UPS-APC-7102", "UPS APC Smart-UPS", "OTHER", "APC", "SMT1500C", 20, 16, AssetStatus.DADO_DE_BAJA);
            saveIfMissing(repository, "ast-hp-016", "HP-840-7813", "HP EliteBook 840", "LAPTOP", "HP", "EliteBook 840 G10", 9, 14, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-len-017", "LN-T14-5517", "Lenovo ThinkPad T14", "LAPTOP", "Lenovo", "ThinkPad T14 Gen 4", 11, 8, AssetStatus.ASIGNADO);
            saveIfMissing(repository, "ast-apple-018", "MBA-M2-9018", "MacBook Air M2", "LAPTOP", "Apple", "MacBook Air 13 M2", 13, 4, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-dell-019", "DL-7420-5519", "Dell Latitude 7420", "LAPTOP", "Dell", "Latitude 7420", 24, -2, AssetStatus.EN_REPARACION);
            saveIfMissing(repository, "ast-mon-020", "DELL-U2720-20", "Monitor Dell UltraSharp", "MONITOR", "Dell", "U2720Q", 16, 10, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-mon-021", "SAMSUNG-32-21", "Monitor Samsung Smart", "MONITOR", "Samsung", "M8 32 pulgadas", 7, 17, AssetStatus.ASIGNADO);
            saveIfMissing(repository, "ast-cis-022", "CIS-AP-9022", "Access Point WiFi 6", "NETWORK", "Cisco", "Catalyst 9136", 4, 28, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-fort-023", "FG-60F-9023", "Firewall FortiGate", "NETWORK", "Fortinet", "FortiGate 60F", 15, 21, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-hpe-024", "HPE-DL380-24", "Servidor ProLiant DL380", "SERVER", "HPE", "ProLiant DL380 Gen10", 28, -6, AssetStatus.EN_MANTENIMIENTO);
            saveIfMissing(repository, "ast-epson-025", "EP-L6160-25", "Impresora Epson EcoTank", "PERIPHERAL", "Epson", "L6160", 19, 3, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-zebra-026", "ZB-ZD421-26", "Impresora de etiquetas", "PERIPHERAL", "Zebra", "ZD421", 6, 30, AssetStatus.ASIGNADO);
            saveIfMissing(repository, "ast-ipad-027", "IPAD-AIR-27", "iPad Air", "MOBILE", "Apple", "iPad Air 5", 8, 16, AssetStatus.ASIGNADO);
            saveIfMissing(repository, "ast-moto-028", "MOTO-G84-28", "Motorola G84", "MOBILE", "Motorola", "Moto G84", 3, 20, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-log-029", "LOG-K380-29", "Teclado inalámbrico K380", "PERIPHERAL", "Logitech", "K380", 2, 34, AssetStatus.DISPONIBLE);
            saveIfMissing(repository, "ast-sony-030", "SONY-WH1000-30", "Audífonos Sony", "PERIPHERAL", "Sony", "WH-1000XM5", 5, 25, AssetStatus.DISPONIBLE);
        };
    }

    private void saveIfMissing(AssetRepository repository, String id, String serial, String name,
                               String category, String brand, String model, int acquiredMonthsAgo,
                               int warrantyMonthsFromNow, AssetStatus status) {
        if (repository.existsById(id)) {
            return;
        }
        Asset asset = new Asset();
        asset.setId(id);
        asset.setSerialNumber(serial);
        asset.setName(name);
        asset.setCategory(category);
        asset.setBrand(brand);
        asset.setModel(model);
        asset.setAcquisitionDate(LocalDate.now().minusMonths(acquiredMonthsAgo));
        asset.setWarrantyExpiration(LocalDate.now().plusMonths(warrantyMonthsFromNow));
        asset.setStatus(status);
        repository.save(asset);
    }
}
