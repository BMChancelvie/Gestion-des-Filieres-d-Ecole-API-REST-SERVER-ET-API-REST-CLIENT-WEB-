package com.core.api_ecole_web.repository;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.core.api_ecole_web.CustomProperties;
import com.core.api_ecole_web.model.Classe;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Component
public class ClasseProxy {
	
	@Autowired
	private CustomProperties props;

	public Iterable<Classe> getClasse() {

		String baseApiUrl = props.getApiUrl();
		String getApiUrl = baseApiUrl + "/classes";

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
	
	public Classe getClasse(long id_classe) {
		String baseApiUrl = props.getApiUrl();
		String getApiUrl = baseApiUrl + "/classe/" + id_classe;

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Classe> response = restTemplate.exchange(
				getApiUrl, 
				HttpMethod.GET, 
				null,
				Classe.class
			);
		
		log.debug("Liste des classes " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public Classe createClasse(Classe e) {
		String baseApiUrl = props.getApiUrl();
		String createApiUrl = baseApiUrl + "/classe";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Classe> request = new HttpEntity<Classe>(e);
		ResponseEntity<Classe> response = restTemplate.exchange(
				createApiUrl, 
				HttpMethod.POST, 
				request, 
				Classe.class);
		
		log.debug("Créer une classe " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public Classe updateClasse(Classe e) {
		String baseApiUrl = props.getApiUrl();
		String updateApiUrl = baseApiUrl + "/classe/" + e.getId_classe();
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Classe> request = new HttpEntity<Classe>(e);
		ResponseEntity<Classe> response = restTemplate.exchange(
				updateApiUrl, 
				HttpMethod.PUT, 
				request, 
				Classe.class);
		
		log.debug("Mettre à jour la classe " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public void deleteClasse(long id_classe) {
		String baseApiUrl = props.getApiUrl();
		String deleteApiUrl = baseApiUrl + "/classe/" + id_classe;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteApiUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Supprimer la classe " + response.getStatusCode().toString());
	}

}
