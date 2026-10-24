package pe.edu.cibertec.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.inventory.entity.AlertRecord;

public interface AlertRecordRepository extends JpaRepository<AlertRecord, String> {}
