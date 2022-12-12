package com.casa.dio.model.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.casa.dio.model.Matricula;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatriculaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long alunoId;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataDaMatricula;

	public MatriculaDto(Matricula matricula) {
		super();
		this.alunoId = matricula.getAluno().getId();
		this.dataDaMatricula = matricula.getDataDaMatricula();
	}

}
