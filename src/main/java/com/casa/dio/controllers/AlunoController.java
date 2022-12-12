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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casa.dio.model.Aluno;
import com.casa.dio.model.dtos.AlunoDto;
import com.casa.dio.services.AlunoService;

@RestController
@RequestMapping(value = "/Aluno")
public class AlunoController {

	@Autowired
	public AlunoService service;

	@PostMapping(value = "/Salvar")
	public ResponseEntity<Aluno> salvar(@Valid @RequestBody Aluno aluno) {
		aluno = service.salvar(aluno);
		return new ResponseEntity<Aluno>(aluno, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Atualizar/{alunoId}")
	public ResponseEntity<Aluno> atualizar(@Valid @PathVariable(value = "alunoId") Long alunoId, @RequestBody Aluno aluno) {
		Aluno aluno1 = service.buscarPorId(alunoId);
		if (aluno1 == null) {
			return ResponseEntity.notFound().build();
		}
		aluno1 = service.atualizar(alunoId, aluno);
		return ResponseEntity.ok(aluno1);

	}

	@PatchMapping(value = "/AtualizacaoParcial/{alunoId}")
	public ResponseEntity<Aluno> atualizarParcialmente(@Valid @PathVariable(value = "alunoId") Long alunoId, @RequestBody Aluno aluno) {
		Aluno aluno1 = service.buscarPorId(alunoId);
		if (aluno1 == null) {
			return ResponseEntity.notFound().build();
		}
		aluno1 = service.atualizarParcialmente(alunoId, aluno);
		return ResponseEntity.ok(aluno1);
	}

	@GetMapping(value = "/BuscarPorId/{alunoId}")
	public ResponseEntity<Aluno> buscarPorId(@PathVariable(value = "alunoId") Long alunoId) {
		Aluno aluno = service.buscarPorId(alunoId);
		ResponseEntity<Aluno> aluno1 = (aluno == null) ?  ResponseEntity.notFound().build() : ResponseEntity.ok(aluno);
		return aluno1;
	}

	@GetMapping(value = "/BuscarTodos")
	public ResponseEntity<List<AlunoDto>> buscarTodos(Long alunoId) {
		List<Aluno> alunos = service.buscarTodos();
		List<AlunoDto> alunosDto = alunos.stream().map(al -> new AlunoDto(al)).collect(Collectors.toList());
		return ResponseEntity.ok(alunosDto);
	}

	@DeleteMapping(value = "/DeletarPorId")
	public ResponseEntity<Void> deletarPorId(@RequestParam(name = "alunoId") Long alunoId) {
		Aluno aluno = service.buscarPorId(alunoId);
		if (aluno != null) {
			service.deletePorId(alunoId);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
