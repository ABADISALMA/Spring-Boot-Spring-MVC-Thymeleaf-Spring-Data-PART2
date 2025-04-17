package org.example.hospital.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.hospital.entities.Patient;
import org.example.hospital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String kw) {

        Page<Patient> pagePatients = patientRepository.findByNomContains(kw, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", kw);

        return "patient";
    }

    @GetMapping("/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());

        return "formPatient";

    }
    @GetMapping("/delete")
    public String delete(@RequestParam Long id,
                         @RequestParam String keyword,
                         @RequestParam int page) {
        patientRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }


    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("patient", new Patient()); // Crée un nouvel objet Patient vide
        return "addpatient"; // Renvoie vers la vue 'add-patient.html'
    }

    @PostMapping("/save")
    public String savePatient(Model model, @Valid Patient patient, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return "formPatient";
        patientRepository.save(patient); // Enregistrer le patient dans la base de données
        return "redirect:/index"; // Rediriger vers la page d'index après l'ajout
    }

    @GetMapping("/edit")
    public String editPatient(@RequestParam Long id, Model model ) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) throw new RuntimeException("Patient not found");

        model.addAttribute("patient", patient);
        //model.addAttribute("keyword", keyword);
        //model.addAttribute("pages", page);
        return "editPatient"; // le même formulaire pour edit
    }



}
