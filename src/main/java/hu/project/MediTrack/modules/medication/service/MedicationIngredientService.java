package hu.project.MediTrack.modules.medication.service;

import hu.project.MediTrack.modules.medication.entity.MedicationIngredient;
import hu.project.MediTrack.modules.medication.repository.MedicationIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicationIngredientService {

    @Autowired
    private MedicationIngredientRepository ingredientRepository;

    public List<MedicationIngredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Optional<MedicationIngredient> findById(Integer id) {
        return ingredientRepository.findById(id);
    }

    public MedicationIngredient save(MedicationIngredient medicationIngredient) {
        // További logika, pl. ellenőrizni, hogy létezik-e a medication + ingredient
        return ingredientRepository.save(medicationIngredient);
    }

    public void deleteById(Integer id) {
        ingredientRepository.deleteById(id);
    }
}
