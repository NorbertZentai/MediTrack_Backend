package hu.project.MediTrack.modules.medication.controller;

import hu.project.MediTrack.modules.medication.entity.Medication;
import hu.project.MediTrack.modules.medication.service.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping
    public List<Medication> getAllMedications() {
        return medicationService.findAll();
    }

    @GetMapping("/{id}")
    public Medication getMedicationById(@PathVariable Integer id) {
        Optional<Medication> med = medicationService.findById(id);
        return med.orElse(null);
    }

    @PostMapping
    public Medication createMedication(@RequestBody Medication medication) {
        return medicationService.save(medication);
    }

    @PutMapping("/{id}")
    public Medication updateMedication(@PathVariable Integer id,
                                       @RequestBody Medication updatedMedication) {
        return medicationService.findById(id).map(med -> {
            med.setName(updatedMedication.getName());
            med.setManufacturer(updatedMedication.getManufacturer());
            med.setDescription(updatedMedication.getDescription());
            med.setPackaging(updatedMedication.getPackaging());
            med.setReleaseDate(updatedMedication.getReleaseDate());
            med.setAverageRating(updatedMedication.getAverageRating());
            return medicationService.save(med);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteMedication(@PathVariable Integer id) {
        medicationService.deleteById(id);
    }
}
