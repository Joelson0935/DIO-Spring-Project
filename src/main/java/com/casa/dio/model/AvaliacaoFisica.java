package com.casa.dio.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AvaliacaoFisica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty(access = Access.WRITE_ONLY)
	@ManyToOne(cascade = CascadeType.ALL)
	private Aluno aluno;
	
	@PastOrPresent(message = "Data Inválida!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDaAvaliacao;

	@NotNull(message = "preencha este campo corretamente.")
	private Double peso;

	@NotNull(message = "preencha este campo corretamente.")
	private Double altura;

	public AvaliacaoFisica(Aluno aluno, LocalDate dataDaAvaliacao, Double peso, Double altura) {
		super();
		this.aluno = aluno;
		this.dataDaAvaliacao = dataDaAvaliacao;
		this.peso = peso;
		this.altura = altura;
	}

}
