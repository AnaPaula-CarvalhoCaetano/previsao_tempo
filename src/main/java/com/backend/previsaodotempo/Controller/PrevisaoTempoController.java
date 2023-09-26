package com.backend.previsaodotempo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backend.previsaodotempo.Service.ApiService;

@RestController
@RequestMapping("/api/PrevisaoTempo")
public class PrevisaoTempoController {

    private final ApiService apiService;

    @Autowired
    public PrevisaoTempoController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/listar")
    public String listarPrevisaoTempo(@RequestParam(required = false) String cidade) {
        if (cidade != null) {
            return apiService.buscarDadosDaAPIExterna(cidade);
        }
        return "Cidade não especificada";
    }
}
