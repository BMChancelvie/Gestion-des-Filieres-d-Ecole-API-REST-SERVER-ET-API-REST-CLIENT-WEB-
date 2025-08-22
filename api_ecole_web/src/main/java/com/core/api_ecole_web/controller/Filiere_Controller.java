package com.core.api_ecole_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.core.api_ecole_web.model.Filiere;
//import com.core.api_ecole_web.model.Classe;
import com.core.api_ecole_web.service.Filiere_Service;

import lombok.Data;

@Data
@Controller
public class Filiere_Controller {

    @Autowired
    private Filiere_Service service;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/filieres")
    public String listFiliere(Model model) {
        Iterable<Filiere> listFiliere = service.getFilieresWithClasse();
        model.addAttribute("filieres", listFiliere);
        return "filieres";
    }

    @GetMapping("/createFiliere")
    public String createFiliere(Model model) {
        Filiere e = new Filiere();
        model.addAttribute("filiere", e);
        model.addAttribute("classes", service.getClasses());
        return "formNewFiliere";
    }

    @GetMapping("/updateFiliere/{id_filiere}")
    public String updateFiliere(@PathVariable("id_filiere") final long id_filiere, Model model) {
        Filiere e = service.getFiliere(id_filiere);
        model.addAttribute("filiere", e);
        model.addAttribute("classes", service.getClasses());
        return "formUpdateFiliere";
    }

    @GetMapping("/deleteFiliere/{id_filiere}")
    public ModelAndView deleteFiliere(@PathVariable("id_filiere") final long id_filiere) {
        service.deleteFiliere(id_filiere);
        return new ModelAndView("redirect:/filieres");
    }

    @PostMapping("/saveFiliere")
    public ModelAndView saveFiliere(@ModelAttribute Filiere filiere) {
        service.saveFiliere(filiere);
        return new ModelAndView("redirect:/filieres");
    }
}