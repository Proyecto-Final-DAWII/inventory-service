package pe.edu.cibertec.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.cibertec.inventory.entity.IncidentRecord;

public interface IncidentRecordRepository extends JpaRepository<IncidentRecord, String> {}
