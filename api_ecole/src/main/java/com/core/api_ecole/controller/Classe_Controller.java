package com.core.api_ecole.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.core.api_ecole.model.Classe;
import com.core.api_ecole.service.Classe_Service;

@RestController
public class Classe_Controller {
	
	@Autowired
	private Classe_Service classeService;
	
    /* endpoint pour creer une classe*/
	
	@PostMapping("/classe")
	public Classe createClasse(@RequestBody Classe classe) {
		return classeService.saveClasse(classe); 
		}
	
	/*endpoint pour afficher une classe*/
	
	@GetMapping("/classe/{id_classe}")
	public Classe getClasse(@PathVariable("id_classe") final Long id_classe) {
		Optional<Classe> classe = classeService.getClasse(id_classe);
		if(classe.isPresent()) {
			return classe.get();
		} else {
			return null;
		}
	}
	
	/**
	 endpoint pour afficher les classes
	 */
	
	@GetMapping("/classes")
	public Iterable<Classe> getClasse() {
		return classeService.getClasse();
	}
	
	/**
	 endpoint pour mettre à jour une classe
	 */
	
	@PutMapping("/classe/{id_classe}")
	public Classe updateClasse(@PathVariable("id_classe") final Long id_classe, @RequestBody Classe classe) {
		Optional<Classe> e = classeService.getClasse(id_classe);
		if(e.isPresent()) {
			Classe currentClasse = e.get();
			
			String nom_classe = classe.getNom_classe()
;			if(nom_classe != null) {
				currentClasse.setNom_classe(nom_classe);
			}
			String description_classe = classe.getDescription_classe();
			if(description_classe != null) {
				currentClasse.setDescription_classe(description_classe);
			}
			classeService.saveClasse(currentClasse);
			return currentClasse;
		} else {
			return null;
		}
	}
	
	
	/**
	endpoint pour supprimer une classe
	 */
	@DeleteMapping("/classe/{id_classe}")
	public void deleteClasse(@PathVariable("id_classe") final Long id_classe) {
		classeService.deleteClasse(id_classe);
	}	

}
