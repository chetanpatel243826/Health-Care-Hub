package com.health.coverage_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.health.coverage_service.bmi.BMIRequest;
import com.health.coverage_service.bmi.BMIResponse;

@Service
public class BMIService {
	
	@Value("${bmi.api.key}")
	private String apiKey;
	
	@Value("${bmi.api.url}")
	private String apiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	public BMIResponse getBMI(BMIRequest bmiRequest) {
		HttpHeaders headers = new HttpHeaders();
	    headers.set("x-api-key", apiKey);
		HttpEntity<String> entity = new HttpEntity<>(headers);
        String url = apiUrl + "?weight=" + bmiRequest.getWeight() + 
        		"&height=" + bmiRequest.getHeight() + 
        		"&unit=" + bmiRequest.getUnit();
        ResponseEntity<BMIResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, BMIResponse.class
            );

        return response.getBody();
	}
}
