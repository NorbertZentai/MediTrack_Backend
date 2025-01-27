package hu.project.MediTrack.modules.medication.repository;

import hu.project.MediTrack.modules.medication.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    // Például keresés név alapján
    // List<Medication> findByNameContainingIgnoreCase(String namePart);
}
