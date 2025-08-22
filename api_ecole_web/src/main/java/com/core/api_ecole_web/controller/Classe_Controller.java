package com.core.api_ecole_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.core.api_ecole_web.model.Classe;
import com.core.api_ecole_web.service.Classe_Service;

import lombok.Data;

@Data
@Controller
public class Classe_Controller {

	@Autowired
	private Classe_Service service;
	
	@GetMapping("/classes")
	public String listClasse(Model model) {
		Iterable<Classe> listClasse = service.getClasse();
		model.addAttribute("Classe", listClasse);
		return "classes";
	}
	
	@GetMapping("/createClasse")
	public String createclasse(Model model) {
		Classe e = new Classe();
		model.addAttribute("classe", e);
		return "formNewClasse";
	}
	
	@GetMapping("/updateClasse/{id_classe}")
	public String updateClasse(@PathVariable("id_classe") final long id_classe, Model model) {
		Classe e = service.getClasse(id_classe);		
		model.addAttribute("classe", e);	
		return "formUpdateClasse";		
	}
	
	@GetMapping("/deleteClasse/{id_classe}")
	public ModelAndView deleteClasse(@PathVariable("id_classe") final long id_classe) {
		service.deleteClasse(id_classe);
		return new ModelAndView("redirect:/classes");		
	}
	
	@PostMapping("/saveClasse")
    public ModelAndView saveClasse(@ModelAttribute Classe classe) {
        service.saveClasse(classe);
        return new ModelAndView("redirect:/classes");
    }
	
	/*@PostMapping("/saveClasse")
	public ModelAndView saveClasse(@ModelAttribute Classe classe) {
		if(classe.getId_classe() != null) {
			Classe current = service.getClasse(classe.getId_classe());
			classe.setNom_classe(current.getNom_classe());
		}
		service.saveClasse(classe);
		return new ModelAndView("redirect:/classes");	
	}*/
	
}
