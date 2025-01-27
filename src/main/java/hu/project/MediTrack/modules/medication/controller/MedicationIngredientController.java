package hu.project.MediTrack.modules.medication.controller;

import hu.project.MediTrack.modules.medication.entity.MedicationIngredient;
import hu.project.MediTrack.modules.medication.service.MedicationIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medication-ingredients")
public class MedicationIngredientController {

    @Autowired
    private MedicationIngredientService ingredientService;

    @GetMapping
    public List<MedicationIngredient> getAllMedicationIngredients() {
        return ingredientService.findAll();
    }

    @GetMapping("/{id}")
    public MedicationIngredient getMedicationIngredientById(@PathVariable Integer id) {
        Optional<MedicationIngredient> mi = ingredientService.findById(id);
        return mi.orElse(null);
    }

    @PostMapping
    public MedicationIngredient createMedicationIngredient(
            @RequestBody MedicationIngredient ingredient) {
        return ingredientService.save(ingredient);
    }

    @PutMapping("/{id}")
    public MedicationIngredient updateMedicationIngredient(@PathVariable Integer id,
                                                           @RequestBody MedicationIngredient updated) {
        return ingredientService.findById(id).map(mi -> {
            mi.setMedication(updated.getMedication());
            mi.setIngredient(updated.getIngredient());
            return ingredientService.save(mi);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteMedicationIngredient(@PathVariable Integer id) {
        ingredientService.deleteById(id);
    }
}
