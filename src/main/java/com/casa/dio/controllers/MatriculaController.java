package com.casa.dio.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casa.dio.model.Matricula;
import com.casa.dio.model.dtos.MatriculaDto;
import com.casa.dio.services.MatriculaService;

@RestController
@RequestMapping(value = "/Matricula")
public class MatriculaController {

	@Autowired
	public MatriculaService service;

	@PostMapping(value = "/Salvar")
	public ResponseEntity<MatriculaDto> salvar(@Valid @RequestBody MatriculaDto matricula) {
		matricula = service.salvar(matricula);
		return new ResponseEntity<MatriculaDto>(matricula, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Atualizar/{matriculaId}")
	public ResponseEntity<Matricula> atualizar(@Valid @PathVariable Long matriculaId,
			@RequestBody Matricula matricula) {
		Matricula matricula1 = service.buscarPorId(matriculaId);
		if (matricula1 == null) {
			return ResponseEntity.notFound().build();
		}
		matricula1 = service.atualizar(matriculaId, matricula);
		return ResponseEntity.ok(matricula1);

	}

	@PatchMapping(value = "/AtualizacaoParcial/{matriculaId}")
	public ResponseEntity<Matricula> atualizarParcialmente(@Valid @PathVariable Long matriculaId,
			@RequestBody Matricula matricula) {
		Matricula matricula1 = service.buscarPorId(matriculaId);
		if (matricula1 == null) {
			return ResponseEntity.notFound().build();
		}
		matricula1 = service.atualizarParcialmente(matriculaId, matricula);
		return ResponseEntity.ok(matricula1);

	}

	@GetMapping(value = "/BuscarPorId/{matriculaId}")
	public ResponseEntity<Matricula> buscarPorId(@PathVariable Long matriculaId) {
		Matricula matricula = service.buscarPorId(matriculaId);
		ResponseEntity<Matricula> matricula1 = (matricula == null) ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(matricula);
		return matricula1;
	}

	@GetMapping(value = "/BuscarTodos")
	public ResponseEntity<List<MatriculaDto>> buscarTodos(Long matriculaId) {
		List<Matricula> matriculas = service.buscarTodos();
		List<MatriculaDto> matriculasDto = matriculas.stream().map(mat -> new MatriculaDto(mat))
				.collect(Collectors.toList());
		return ResponseEntity.ok(matriculasDto);
	}

	@DeleteMapping(value = "/DeletarPorId/{matriculaId}")
	public ResponseEntity<Void> deletarPorId(@PathVariable Long matriculaId) {
		Matricula matricula = service.buscarPorId(matriculaId);
		if (matricula != null) {
			service.deletePorId(matriculaId);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
