package com.casa.dio.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Matricula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	private Aluno aluno;

	@PastOrPresent(message = "Data Inválida!")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataDaMatricula;

	public Matricula(Aluno aluno, LocalDateTime dataDaMatricula) {
		super();
		this.aluno = aluno;
		this.dataDaMatricula = dataDaMatricula;
	}

}
