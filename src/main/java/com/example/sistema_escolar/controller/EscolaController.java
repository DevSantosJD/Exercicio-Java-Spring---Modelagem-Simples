package com.example.sistema_escolar.controller;


import com.example.sistema_escolar.model.AlunoModel;
import com.example.sistema_escolar.model.ProfessorModel;
import com.example.sistema_escolar.service.SalaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instituicao")
public class EscolaController {

    private final SalaService salaService;

    public EscolaController(SalaService salaService){
        this.salaService = salaService;
    }

    @GetMapping("/professores")
    public List<ProfessorModel> listarProfessores(){
        return salaService.listarProf();
    }

    @GetMapping("/alunos")
    public List<AlunoModel> listarAlunos(){
        return salaService.listarAlunos();
    }

    @GetMapping("/professores/{id}")
    public ResponseEntity<ProfessorModel> bsucarProfessorId(@PathVariable Long id){
        return salaService.encontrarProfId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
