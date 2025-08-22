package com.core.api_ecole_web.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.core.api_ecole_web.CustomProperties;
import com.core.api_ecole_web.model.Classe;
import com.core.api_ecole_web.model.Filiere;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
public class FiliereProxy {

	@Autowired
	private CustomProperties props;

	public Iterable<Filiere> getFiliere() {

		String baseApiUrl = props.getApiUrl();
		String getApiUrl = baseApiUrl + "/filieres";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Filiere>> response = restTemplate.exchange(
				getApiUrl, 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<Iterable<Filiere>>() {}
			);
		
		log.debug("Liste des filières " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	// Nouvelle méthode pour récupérer les filières avec leurs classes
    public Iterable<Filiere> getFilieresWithClasse() {
        String baseApiUrl = props.getApiUrl();
        String getApiUrl = baseApiUrl + "/filieres/with-classe";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Filiere>> response = restTemplate.exchange(
                getApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Filiere>>() {}
        );

        log.debug("Liste des filières avec classes " + response.getStatusCode().toString());

        return response.getBody();
    }

    // Nouvelle méthode pour récupérer toutes les classes
    public Iterable<Classe> getClasses() {
        String baseApiUrl = props.getApiUrl();
        String getApiUrl = baseApiUrl + "/classes"; // Supposons un endpoint /classes

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Classe>> response = restTemplate.exchange(
                getApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Classe>>() {}
        );

        log.debug("Liste des classes " + response.getStatusCode().toString());

        return response.getBody();
    }
	
	public Filiere getFiliere(long id_filiere) {
		String baseApiUrl = props.getApiUrl();
		String getApiUrl = baseApiUrl + "/filiere/" + id_filiere;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Filiere> response = restTemplate.exchange(
				getApiUrl, 
				HttpMethod.GET, 
				null,
				Filiere.class
			);
		
		log.debug("Liste des filières " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	// Mise à jour de la méthode createFiliere pour inclure idClasse
    public Filiere createFiliere(Filiere e) {
        String baseApiUrl = props.getApiUrl();
        String createApiUrl = UriComponentsBuilder.fromUriString(baseApiUrl + "/filiere")
                .queryParam("idClasse", e.getClasse().getId_classe())
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Filiere> request = new HttpEntity<Filiere>(e);
        ResponseEntity<Filiere> response = restTemplate.exchange(
                createApiUrl, 
                HttpMethod.POST, 
                request, 
                Filiere.class);
        
        log.debug("Créer une filière " + response.getStatusCode().toString());
        
        return response.getBody();
    }
	
	public Filiere updateFiliere(Filiere e) {
		String baseApiUrl = props.getApiUrl();
		String updateApiUrl = baseApiUrl + "/filiere/" + e.getId_filiere();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Filiere> request = new HttpEntity<Filiere>(e);
		ResponseEntity<Filiere> response = restTemplate.exchange(
				updateApiUrl, 
				HttpMethod.PUT, 
				request, 
				Filiere.class);
		
		log.debug("Mettre à jour la filière " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public void deleteFiliere(long id_filiere) {
		String baseApiUrl = props.getApiUrl();
		String deleteApiUrl = baseApiUrl + "/filiere/" + id_filiere;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteApiUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Supprimer la filière " + response.getStatusCode().toString());
	}

}
