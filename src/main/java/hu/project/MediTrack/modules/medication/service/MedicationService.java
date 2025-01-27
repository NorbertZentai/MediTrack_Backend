package hu.project.MediTrack.modules.medication.service;

import hu.project.MediTrack.modules.medication.entity.Medication;
import hu.project.MediTrack.modules.medication.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;

    public List<Medication> findAll() {
        return medicationRepository.findAll();
    }

    public Optional<Medication> findById(Integer id) {
        return medicationRepository.findById(id);
    }

    public Medication save(Medication medication) {
        // Itt pl. lehetne validálás, rating számítás, stb.
        return medicationRepository.save(medication);
    }

    public void deleteById(Integer id) {
        medicationRepository.deleteById(id);
    }
}
