package com.example.sistema_escolar.repository;

import com.example.sistema_escolar.model.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoModel, Long> {

}
