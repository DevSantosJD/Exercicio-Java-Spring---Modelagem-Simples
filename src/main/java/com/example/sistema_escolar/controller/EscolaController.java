package com.example.sistema_escolar.controller;


import com.example.sistema_escolar.model.AlunoModel;
import com.example.sistema_escolar.model.ProfessorModel;
import com.example.sistema_escolar.service.SalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instituicao")
public class EscolaController {

    private final SalaService salaService;

    public EscolaController(SalaService salaService){
        this.salaService = salaService;
    }

    //Cadastrar aluno
    @PostMapping("/cadastroAluno")
    public AlunoModel cadastrarAluno(@RequestBody AlunoModel aluno){
        return salaService.criarAluno(aluno);
    }

    //Listar Aluno
    @GetMapping("/alunosCadastrados")
    public List<AlunoModel> listrAlunos(){
        return salaService.listarAlunos();
    }

    //Cadastrar profesor
    @PostMapping("/cadastroProfessor")
    public ProfessorModel cadastroProfessor(@RequestBody ProfessorModel professor){
        return salaService.criarProfessor(professor);
    }

    //Listar professores
    @GetMapping("/professoresCadastrados")
    public List<ProfessorModel> listarProfessores(){
        return salaService.listarProfesores();
    }


    //Deletar Professor
    @DeleteMapping("/deletarProfessor/{professorId}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long professorId){
        salaService.deletarProfessor(professorId);
        return ResponseEntity.noContent().build();
    }

    //Deletar alunos
    @DeleteMapping("/deletarAluno/{alunoId}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long alunoId){
        salaService.deletarAluno(alunoId);
        return ResponseEntity.noContent().build();
    }

    //Vincular aluno a professor
    @PostMapping("/professor/{professorId}/adicionar-aluno/{alunoId}")
    public ResponseEntity<?> vincularAlunoProferssor(@PathVariable Long professorId, @PathVariable Long alunoId){
        salaService.vincularAlunoAProfessor(professorId, alunoId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno vinculado com sucesso!" );
    }

    //Desvincular aluno de professor
    @PostMapping("/professor/{professorId}/remover-aluno/{alunoId}")
    public ResponseEntity<?> desvincularAlunoProfessor(@PathVariable Long professorId, @PathVariable Long alunoId){
        salaService.desvincularAlunoDeProfessor(professorId, alunoId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Aluno desvinculado com sucesso!");
    }

    //Localizar aluno por id
    @GetMapping("/localizarAluno/{alunoId}")
    public Optional<?> localizarAluno(@PathVariable Long alunoId){
        return salaService.buscarAluno(alunoId);
    }

    //Localizar professor por id
    @GetMapping("/localizarProfessor/{professorId}")
    public Optional<?> localizarProfessor(@PathVariable Long professorId){
        return salaService.buscarProfessor(professorId);
    }
}
