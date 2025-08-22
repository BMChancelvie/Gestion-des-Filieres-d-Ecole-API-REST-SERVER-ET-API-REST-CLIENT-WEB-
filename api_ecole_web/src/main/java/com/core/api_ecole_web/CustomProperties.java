package com.core.api_ecole_web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.core.api-ecole.web")
public class CustomProperties {
	
		private String apiUrl ="http://localhost:9093";

		public String getApiUrl() {
			// TODO Auto-generated method stub
			return apiUrl;
		}

}
