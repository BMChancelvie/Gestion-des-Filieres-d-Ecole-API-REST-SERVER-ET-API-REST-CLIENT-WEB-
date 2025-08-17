package com.core.api_ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.core.api_ecole.model.Classe;

@Repository
public interface Classe_Repository extends JpaRepository<Classe, Long> {
	
}