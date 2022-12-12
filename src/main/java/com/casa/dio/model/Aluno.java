package com.casa.dio.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "preencha este campo corretamente.")
	private String nome;

	@Pattern(regexp = "^[0-9]{3}[.]?[0-9]{3}[.]?[0-9]{3}[-]?[0-9]{2}$", message = "cpf Inválido!")
	private String cpf;

	@NotBlank(message = "preencha este campo corretamente.")
	private String bairro;
	
	@PastOrPresent(message = "Data Inválida!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY)
	@JsonProperty(access = Access.READ_ONLY)
	private List<AvaliacaoFisica> avaliacoesFisicas = new ArrayList<>();

	public Aluno(String nome, String cpf, String bairro, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.bairro = bairro;
		this.dataNascimento = dataNascimento;
	}

	public Aluno(Long id, String nome, String cpf, String bairro, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.bairro = bairro;
		this.dataNascimento = dataNascimento;
	}

}
