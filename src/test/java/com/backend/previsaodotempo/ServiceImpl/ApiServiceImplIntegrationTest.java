package com.backend.previsaodotempo.ServiceImpl;

import com.backend.previsaodotempo.Service.ApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class ApiServiceImplIntegrationTest {

    @Autowired
    private ApiService apiService;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockRestServiceServer;
    
    private static final Logger logger = LoggerFactory.getLogger(ApiServiceImplIntegrationTest.class);


    @BeforeEach
    public void setUp() {
        mockRestServiceServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testBuscarDadosDaAPIExterna() {
        String cidade = "Rio de Janeiro";
        String apiKey = "your_test_api_key";
        
        logger.info("Iniciando teste de buscarDadosDaAPIExterna para cidade: {}", cidade);


        mockRestServiceServer.expect(requestTo("https://api.weatherbit.io/v2.0/forecast/daily?city=Rio%20de%20Janeiro&key=your_test_api_key"))
            .andExpect(method(HttpMethod.GET))
            .andRespond(withSuccess("Dados simulados que contêm alguma coisa", MediaType.APPLICATION_JSON));

        String result = apiService.buscarDadosDaAPIExterna(cidade);

        assertNotNull(result);
        
        logger.info("Resultado do teste: {}", result);

    }
}
