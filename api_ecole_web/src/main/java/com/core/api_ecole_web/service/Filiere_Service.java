package com.core.api_ecole_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.api_ecole_web.model.Classe;
import com.core.api_ecole_web.model.Filiere;
import com.core.api_ecole_web.repository.FiliereProxy;

import lombok.Data;

@Data
@Service
public class Filiere_Service {

	@Autowired
	private FiliereProxy filiereProxy;
	
	public Filiere getFiliere(final long id_filiere) {
		return filiereProxy.getFiliere(id_filiere);
	}
	
	public Iterable<Filiere> getFiliere() {
		return filiereProxy.getFiliere();
	}
	
	// Nouvelle méthode pour récupérer les filières avec leurs classes
    public Iterable<Filiere> getFilieresWithClasse() {
        return filiereProxy.getFilieresWithClasse();
    }

    // Nouvelle méthode pour récupérer toutes les classes
    public Iterable<Classe> getClasses() {
        return filiereProxy.getClasses();
    }
	
	public void deleteFiliere(final long id_filiere) {
		filiereProxy.deleteFiliere(id_filiere);
	}
	
	public Filiere saveFiliere(Filiere filiere) {
		
		Filiere savedFiliere;
		
		filiere.setNom_filiere(filiere.getNom_filiere().toUpperCase());

		if(filiere.getId_filiere() == null) {
			
			savedFiliere = filiereProxy.createFiliere(filiere);
		} else {
			savedFiliere = filiereProxy.updateFiliere(filiere);
		}
		
		return savedFiliere;
	}

}
