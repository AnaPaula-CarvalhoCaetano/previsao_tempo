package com.backend.previsaodotempo.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.backend.previsaodotempo.Service.ApiService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
public class PrevisaoTempoControllerTest {

    private PrevisaoTempoController previsaoTempoController;
    private ApiService apiService;

    @BeforeEach
    public void setUp() {
        apiService = mock(ApiService.class);
        previsaoTempoController = new PrevisaoTempoController(apiService);
    }

    @Test
    public void testListarPrevisaoTempoComCidade() {
        
        String cidade = "Sao Paulo";
        when(apiService.buscarDadosDaAPIExterna(cidade)).thenReturn("Previsão para São Paulo");

      
        String resultado = previsaoTempoController.listarPrevisaoTempo(cidade);

     
        assertEquals("Previsão para São Paulo", resultado);
        verify(apiService, times(1)).buscarDadosDaAPIExterna(cidade);
    }

    @Test
    public void testListarPrevisaoTempoSemCidade() {
        
        when(apiService.buscarDadosDaAPIExterna(null)).thenReturn("Cidade não especificada");

        
        String resultado = previsaoTempoController.listarPrevisaoTempo(null);

       
        assertEquals("Cidade não especificada", resultado);
        verify(apiService, times(1)).buscarDadosDaAPIExterna(null);

    }
}
