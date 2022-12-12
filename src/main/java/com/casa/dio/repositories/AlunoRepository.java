package com.casa.dio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.dio.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{

}
