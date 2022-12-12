package com.casa.dio.controllers.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Erro implements Serializable {
	private static final long serialVersionUID = 1L;

	private String erro;
	private Integer codigo;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataHora;
	
	@OneToMany
	private List<Campo> campos = new ArrayList<>();

	public Erro(String erro, Integer codigo, LocalDateTime dataHora) {
		super();
		this.erro = erro;
		this.codigo = codigo;
		this.dataHora = dataHora;
	}

	public void setCamposDeErros(String nome, String erro) {
		Campo campo = new Campo(nome, erro);
		this.campos.add(campo);
	}

}
