package com.casa.dio.model.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.casa.dio.model.Aluno;

import lombok.Data;

@Data
public class AlunoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String cpf;
	private String bairro;
	private LocalDate dataNascimento;

	public AlunoDto(Aluno aluno) {
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.cpf = aluno.getCpf();
		this.bairro = aluno.getBairro();
		this.dataNascimento = aluno.getDataNascimento();
	}

}
