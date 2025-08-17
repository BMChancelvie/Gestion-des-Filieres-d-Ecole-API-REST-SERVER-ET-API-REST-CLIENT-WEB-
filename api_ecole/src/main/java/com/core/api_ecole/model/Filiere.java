package com.core.api_ecole.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "filiere")  

public class Filiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_filiere;
	
	@Column(name="nom_filiere")
	private String nom_filiere;
	
	/*@Column(name="id_classe")
	private Long id_classe;*/
	
	/* Gère la joiture en la table Filiere et la table Classe */
	@ManyToOne
	@JoinColumn(name="id_classe")
	private Classe classe;

}
