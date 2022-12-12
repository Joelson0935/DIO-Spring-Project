package com.casa.dio.model.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.casa.dio.model.AvaliacaoFisica;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AvaliacaoFisicaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long alunoId;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDaAvaliacao;

	private Double peso;

	private Double altura;

	public AvaliacaoFisicaDto(AvaliacaoFisica avaliacao) {
		this.alunoId = avaliacao.getAluno().getId();
		this.dataDaAvaliacao = avaliacao.getDataDaAvaliacao();
		this.peso = avaliacao.getPeso();
		this.altura = avaliacao.getAltura();
	}

}
