package com.core.api_ecole_web.model;

import lombok.Data;

@Data

public class Filiere {

	private Integer id_filiere;

	private String nom_filiere;
	
	//private Integer id_classe;
	
	private Classe classe; // Remplacement de id_classe par un objet Classe

}
