package com.casa.dio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.dio.model.Aluno;
import com.casa.dio.model.Matricula;
import com.casa.dio.model.dtos.MatriculaDto;
import com.casa.dio.repositories.AlunoRepository;
import com.casa.dio.repositories.MatriculaRepository;

@Service
public class MatriculaService {

	@Autowired
	public MatriculaRepository repository;

	@Autowired
	public AlunoRepository alunoRepository;

	public MatriculaDto salvar(MatriculaDto matricula) {
		Aluno aluno = alunoRepository.findById(matricula.getAlunoId())
				.orElseThrow(() -> new RuntimeException("Objeto Não Encontrado!"));

		Matricula matriculaNova = new Matricula(aluno, matricula.getDataDaMatricula());
		matriculaNova = repository.save(matriculaNova);

		MatriculaDto dto = new MatriculaDto(matriculaNova);
		return dto;
	}

	public Matricula atualizar(Long id, Matricula matricula) {
		Matricula novaMatricula = buscarPorId(id);

		if (matricula.getAluno() == null) {
			novaMatricula = new Matricula(id, novaMatricula.getAluno(), matricula.getDataDaMatricula());
		} else {
			novaMatricula = new Matricula(id, matricula.getAluno(), matricula.getDataDaMatricula());
		}

		novaMatricula = repository.save(novaMatricula);
		return novaMatricula;
	}

	public Matricula atualizarParcialmente(Long id, Matricula matricula) {
		Matricula matricula1 = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Objeto Não Encontrado!"));

		if (matricula.getId() != null)
			matricula1.setId(matricula.getId());

		if (matricula.getAluno() != null)
			matricula1.setAluno(matricula.getAluno());

		if (matricula.getDataDaMatricula() != null)
			matricula1.setDataDaMatricula(matricula.getDataDaMatricula());

		matricula1 = repository.save(matricula1);

		return matricula1;
	}

	public Matricula buscarPorId(Long id) {
		Matricula matricula = repository.findById(id).orElseThrow(() -> new RuntimeException("Objeto Não Encontrado!"));
		return matricula;
	}

	public List<Matricula> buscarTodos() {
		List<Matricula> matriculas = repository.findAll();
		return matriculas;
	}

	public void deletePorId(Long id) {
		Matricula matricula = buscarPorId(id);
		repository.deleteById(matricula.getId());
	}

}
