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

    //Listar todos os alunos
    public List<AlunoModel> listarAlunos(){
        return alunoRepository.findAll();
    }

    //Listar todos os professores
    public List<ProfessorModel> listarProf(){
        return professorRepository.findAll();
    }

    //Buscar alunos por id
    public Optional<AlunoModel> encontrarAlunoId(Long id){
        return alunoRepository.findById(id);
    }

    //Buscar professor por id
    public Optional<ProfessorModel> encontrarProfId(Long id){
        return professorRepository.findById(id);
    }

    //Deletar professor por id
    public void deletarProf(Long id){
        professorRepository.deleteById(id);
    }

    //Deletar aluno por id
    public void deletarAluno(Long id){
        alunoRepository.deleteById(id);
    }

    // Metodo para adicionar alunos a professores
    public void adicionarAlunoAProfessor(Long profId, Long alunoId){
        ProfessorModel professor = professorRepository.findById(profId)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));

        AlunoModel aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));

        //Atribui o professor ao aluno
        aluno.setProfessor(professor);

        alunoRepository.save(aluno);
    }
}
