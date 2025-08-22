package com.core.api_ecole.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.core.api_ecole.model.Classe;
import com.core.api_ecole.model.Filiere;
import com.core.api_ecole.service.Filiere_Service;

@RestController
public class Filiere_Controller {

	@Autowired
	private Filiere_Service filiereService;
	
	@PostMapping("/filiere")
	public Filiere createFiliere(@RequestBody Filiere filiere, @RequestParam Long idClasse) {
	    return filiereService.saveFiliere(filiere, idClasse);
	}
	
    /* endpoint pour creer une filiere*/
	
	/*@PostMapping("/filiere")
	public Filiere createFiliere(@RequestBody Filiere filiere) {
		return filiereService.saveFiliere(filiere); 
		}*/
	
	/* Recupère ID de la classe en créant la filière */
	
	/*@PostMapping("/filiere")
	public Filiere createFiliere(@RequestBody Filiere filiere, @RequestParam Long idClasse) {
	    return filiereService.saveFiliere(filiere, idClasse);
	}*/
	
	/*endpoint pour afficher une filiere*/
	
	@GetMapping("/filiere/{id_filiere}")
	public Filiere getFiliere(@PathVariable("id_filiere") final Long id_filiere) {
		Optional<Filiere> filiere = filiereService.getFiliere(id_filiere);
		if(filiere.isPresent()) {
			return filiere.get();
		} else {
			return null;
		}
	}
	
	/**
	 endpoint pour afficher les filieres
	 */
	
	@GetMapping("/filieres")
	public Iterable<Filiere> getFiliere() {
		return filiereService.getFiliere();
	}
	
	/* endpoint pour afficher toutes les filières avec leurs classes */
    @GetMapping("/filieres/with-classe")
    public List<Filiere> getAllFiliereWithClasse() {
        return filiereService.getAllFiliereWithClasse();
    }
	
	/**
	 endpoint pour mettre à jour une filiere
	 */
	

    @PutMapping("/filiere/{id_filiere}")
    public Filiere updateFiliere(@PathVariable("id_filiere") final Long id_filiere, @RequestBody Filiere filiere) {
        Optional<Filiere> e = filiereService.getFiliere(id_filiere);
        if (e.isPresent()) {
            Filiere currentFiliere = e.get();

            String nom_filiere = filiere.getNom_filiere();
            if (nom_filiere != null && !nom_filiere.trim().isEmpty()) {
                currentFiliere.setNom_filiere(nom_filiere.toUpperCase());
            } else {
                throw new IllegalArgumentException("Le nom de la filière ne peut pas être vide");
            }

            Classe classe = filiere.getClasse();
            if (classe != null && classe.getId_classe() != null) {
                return filiereService.saveFiliere(currentFiliere, classe.getId_classe());
            } else {
                throw new IllegalArgumentException("La classe de la filière ne peut pas être null");
            }
        } else {
            throw new RuntimeException("Filière non trouvée pour l'ID: " + id_filiere);
        }
    }

	
	/**
	endpoint pour supprimer une filiere
	 */
	@DeleteMapping("/filiere/{id_filiere}")
	public void deleteFiliere(@PathVariable("id_filiere") final Long id_filiere) {
		filiereService.deleteFiliere(id_filiere);
	}	

}
