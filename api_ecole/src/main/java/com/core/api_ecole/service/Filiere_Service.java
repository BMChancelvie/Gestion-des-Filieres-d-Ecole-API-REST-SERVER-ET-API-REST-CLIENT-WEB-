package com.core.api_ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.api_ecole.model.Classe;
import com.core.api_ecole.model.Filiere;
import com.core.api_ecole.repository.Classe_Repository;
import com.core.api_ecole.repository.Filiere_Repository;

import lombok.Data;

@Data
@Service
public class Filiere_Service {

	@Autowired
	private Filiere_Repository filiereRepository;
	
	@Autowired
    private Classe_Repository classeRepository;
	
	public Optional<Filiere> getFiliere(final Long id_filiere) {
		return filiereRepository.findById(id_filiere);
	}
	
	public Iterable<Filiere> getFiliere() {
		return filiereRepository.findAll();
	}
	
	// Nouvelle méthode pour récupérer les filières avec leurs classes
    public List<Filiere> getAllFiliereWithClasse() {
        return filiereRepository.findAllWithClasse();
    }
	
	public void deleteFiliere(final Long id_filiere) {
		filiereRepository.deleteById(id_filiere);
	}
	
	public Filiere saveFiliere(Filiere filiere) {
		Filiere savedFiliere = filiereRepository.save(filiere);
		return savedFiliere;
	}
	
	public Filiere saveFiliere(Filiere filiere, Long idClasse) {
        Classe classe = classeRepository.findById(idClasse)
                .orElseThrow(() -> new RuntimeException("Classe introuvable"));
        filiere.setClasse(classe);
        return filiereRepository.save(filiere);
    }
	
	/* Recupère la classe avant d'afficher la filière */
	/*public Filiere saveFiliere(Filiere filiere, Long idClasse) {
        Classe classe = classeRepository.findById(idClasse)
                           .orElseThrow(() -> new RuntimeException("Classe introuvable"));
        filiere.setClasse(classe);
        return filiereRepository.save(filiere);
	}*/
}
