package com.backend.previsaodotempo.ServiceImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.previsaodotempo.Exception.ApiRequestException;
import com.backend.previsaodotempo.Service.ApiService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ApiServiceImpl implements ApiService {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiServiceImpl.class);


    private final RestTemplate restTemplate;
    

    @Autowired
    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String buscarDadosDaAPIExterna(String cidade) {
        try {
            String apiKey = "74abf96f0aa7410ba962973e1d8df0f9";
            String apiUrl = "https://api.weatherbit.io/v2.0/forecast/daily?city=" + cidade + "&key=" + apiKey;

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            
            logger.info("Iniciando a chamada à API externa para a cidade: {}", cidade);


            RequestEntity<?> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(apiUrl));

            ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);

            if (response.getStatusCode().equals(HttpStatus.OK)) {
            	logger.info("API externa respondeu com sucesso.");
                return response.getBody();
            } else {
            	logger.error("Erro na chamada à API externa: {}", response.getStatusCode());
                throw new ApiRequestException("Erro na chamada à API externa: " + response.getStatusCode());
            }
        } catch (URISyntaxException e) {
        	logger.error("Erro na criação da URI: {}", e.getMessage());
            throw new ApiRequestException("Erro na criação da URI: " + e.getMessage(), e);
        }
    }
}
