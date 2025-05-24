package com.example.sistema_escolar.service;

import com.example.sistema_escolar.model.AlunoModel;
import com.example.sistema_escolar.model.ProfessorModel;
import com.example.sistema_escolar.repository.AlunoRepository;
import com.example.sistema_escolar.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;

    public SalaService(AlunoRepository alunoRepository, ProfessorRepository professorRepository) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
    }

    //Criar aluno
    public AlunoModel criarAluno(AlunoModel aluno){
        return alunoRepository.save(aluno);
    }

    //Criar Professore
    public ProfessorModel criarProfessor(ProfessorModel professor){
        return professorRepository.save(professor);
    }

    //Listar todos os alunos
    public List<AlunoModel> listarAlunos(){
        return alunoRepository.findAll();
    }

    //Listar todos os professores
    public List<ProfessorModel> listarProfesores(){
        return professorRepository.findAll();
    }

    //Buscar alunos por id
    public Optional<AlunoModel> buscarAluno(Long alunoId){
        return alunoRepository.findById(alunoId);
    }

    //Buscar professor por id
    public Optional<ProfessorModel> buscarProfessor(Long professorId){
        return professorRepository.findById(professorId);
    }

    //Vincular aluno a professor
    public void vincularAlunoAProfessor(Long professorId, Long alunoId){
        ProfessorModel professor = professorRepository.findById(professorId)
                .orElseThrow(()-> new RuntimeException("Professor não localizado"));

        AlunoModel aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não localizado"));

        aluno.setProfessor(professor);
        alunoRepository.save(aluno);
    }

    //Desvincular aluno de porfessor

    public void desvincularAlunoDeProfessor(Long professorId, Long alunoId){
        ProfessorModel professor = professorRepository.findById(professorId)
            .orElseThrow(()-> new RuntimeException("Professor não localizado"));

        AlunoModel aluno = alunoRepository.findById(alunoId)
            .orElseThrow(()-> new RuntimeException("Aluno não localizado"));

        if(!professor.getAlunos().contains(aluno)){
            throw new RuntimeException("Aluno não esta matriculado ao professor");
        }

        //Desvincula aluno de um professor
        professor.getAlunos().remove(aluno);

        //Atualiza o objeto professor do banco de dados
        professorRepository.save(professor);


    }

    //Deletar aluno por id

    //Deletar professor por id

}
