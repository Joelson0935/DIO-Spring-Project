package com.casa.dio.controllers;

import java.util.List;

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

import com.casa.dio.model.AvaliacaoFisica;
import com.casa.dio.model.dtos.AvaliacaoFisicaDto;
import com.casa.dio.services.AvaliacaoFisicaService;

@RestController
@RequestMapping(value = "/AvaliacaoFisica")
public class AvaliacaoFisicaController {

	@Autowired
	public AvaliacaoFisicaService service;

	@PostMapping(value = "/Salvar")
	public ResponseEntity<AvaliacaoFisicaDto> salvar(@Valid @RequestBody AvaliacaoFisicaDto avaliacaoFisica) {
		avaliacaoFisica = service.salvar(avaliacaoFisica);
		return new ResponseEntity<AvaliacaoFisicaDto>(avaliacaoFisica, HttpStatus.CREATED);
	}

	@PutMapping(value = "/Atualizar/{avaliacaoFisicaId}")
	public ResponseEntity<AvaliacaoFisica> atualizar(@Valid @PathVariable Long avaliacaoFisicaId,
			@RequestBody AvaliacaoFisica avaliacaoFisica) {
		AvaliacaoFisica avaliacaoFisica1 = service.buscarPorId(avaliacaoFisicaId);
		if (avaliacaoFisica1 == null) {
			return ResponseEntity.notFound().build();
		}
		avaliacaoFisica1 = service.atualizar(avaliacaoFisicaId, avaliacaoFisica);
		return ResponseEntity.ok(avaliacaoFisica1);
	}

	@PatchMapping(value = "/AtualizacaoParcial/{avaliacaoFisicaId}")
	public ResponseEntity<AvaliacaoFisica> atualizarParcialmente(@Valid @PathVariable Long avaliacaoFisicaId,
			@RequestBody AvaliacaoFisica avaliacaoFisica) {
		AvaliacaoFisica avaliacaoFisica1 = service.buscarPorId(avaliacaoFisicaId);
		if (avaliacaoFisica1 == null) {
			return ResponseEntity.notFound().build();
		}
		avaliacaoFisica1 = service.atualizarParcialmente(avaliacaoFisicaId, avaliacaoFisica);
		return ResponseEntity.ok(avaliacaoFisica1);
	}

	@GetMapping(value = "/BuscarPorId/{avaliacaoFisicaId}")
	public ResponseEntity<AvaliacaoFisica> buscarPorId(@PathVariable Long avaliacaoFisicaId) {
		AvaliacaoFisica avaliacaoFisica = service.buscarPorId(avaliacaoFisicaId);
		ResponseEntity<AvaliacaoFisica> avaliacaoFisica1 = (avaliacaoFisica == null) ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(avaliacaoFisica);
		return avaliacaoFisica1;
	}

	@GetMapping(value = "/BuscarTodos")
	public ResponseEntity<List<AvaliacaoFisica>> buscarTodos(Long id) {
		List<AvaliacaoFisica> avaliacoesFisicas = service.buscarTodos();
		return ResponseEntity.ok(avaliacoesFisicas);
	}

	@DeleteMapping(value = "/DeletarPorId/{avaliacaoFisicaId}")
	public ResponseEntity<Void> deletarPorId(@RequestParam(name = "avaliacaoFisicaId") Long avaliacaoFisicaId) {
		AvaliacaoFisica avaliacaoFisica = service.buscarPorId(avaliacaoFisicaId);
		if (avaliacaoFisica != null) {
			service.deletePorId(avaliacaoFisicaId);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
