package com.casa.dio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.casa.dio.model.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

}
