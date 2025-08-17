package com.core.api_ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.core.api_ecole.model.Filiere;


@Repository
public interface Filiere_Repository extends JpaRepository<Filiere, Long> {
	
	// Requête avec jointure pour récupérer les filières avec leurs classes
	@Query("SELECT f FROM Filiere f JOIN f.classe c")
    List<Filiere> findAllWithClasse();
	
}