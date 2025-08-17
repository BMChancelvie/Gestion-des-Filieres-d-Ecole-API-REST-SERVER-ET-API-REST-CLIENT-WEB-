package com.core.api_ecole.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.api_ecole.model.Classe;
import com.core.api_ecole.repository.Classe_Repository;

import lombok.Data;

@Data
@Service
public class Classe_Service {

	@Autowired
	private Classe_Repository classeRepository;
	
	public Optional<Classe> getClasse(final Long id_classe) {
		return classeRepository.findById(id_classe);
	}
	
	public Iterable<Classe> getClasse() {
		return classeRepository.findAll();
	}
	
	public void deleteClasse(final Long id_classe) {
		classeRepository.deleteById(id_classe);
	}
	
	public Classe saveClasse(Classe classe) {
		Classe savedClasse = classeRepository.save(classe);
		return savedClasse;
	}
	
}
