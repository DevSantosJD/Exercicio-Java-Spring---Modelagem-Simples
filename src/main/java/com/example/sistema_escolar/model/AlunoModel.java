package com.example.sistema_escolar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="tb_alunos")
@Data
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length= 80)
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Email
    @Column(unique = true)
    private String email;
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    @JsonBackReference
    private ProfessorModel professor; //utilizado para referenciar relacionamento

    protected AlunoModel(){
    }

    public AlunoModel(Long id, String nome, LocalDate dataNascimento, String email, ProfessorModel professor){
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.professor = professor;
    }
}
