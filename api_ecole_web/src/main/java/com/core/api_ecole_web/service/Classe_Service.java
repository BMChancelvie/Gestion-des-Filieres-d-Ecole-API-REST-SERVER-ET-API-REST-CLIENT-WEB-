package com.core.api_ecole_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.api_ecole_web.model.Classe;
import com.core.api_ecole_web.repository.ClasseProxy;

/*import java.util.List;
import java.util.ArrayList;*/

import lombok.Data;

@Data
@Service
public class Classe_Service {
	
	@Autowired
	private ClasseProxy classeProxy;
	
	public Classe getClasse(final long id_classe) {
		return classeProxy.getClasse(id_classe);
	}
	
	public Iterable<Classe> getClasse() {
		return classeProxy.getClasse();
	}
	
	public void deleteClasse(final long id_classe) {
		classeProxy.deleteClasse(id_classe);
	}
	
	public Classe saveClasse(Classe classe) {
		
		Classe savedClasse;
		
		classe.setNom_classe(classe.getNom_classe().toUpperCase());

		if(classe.getId_classe() == null) {
			
			savedClasse = classeProxy.createClasse(classe);
		} else {
			savedClasse = classeProxy.updateClasse(classe);
		}
		
		return savedClasse;
	}

}
