package pe.edu.cibertec.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.inventory.entity.SoftwareLicense;

public interface SoftwareLicenseRepository extends JpaRepository<SoftwareLicense, String> {}
