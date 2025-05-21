package com.example.sistema_escolar.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name= "tb_professor")
@Data
public class ProfessorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Past
    private LocalDate dataNascimento;

    @Email
    @Column(unique = true)
    private String emai;

    @OneToMany(mappedBy = "professor")
    @JsonManagedReference
    private List<AlunoModel> alunos;

    protected ProfessorModel(){}

    public ProfessorModel(Long id, String nome, LocalDate dataNascimento, String emai) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.emai = emai;
    }
}
