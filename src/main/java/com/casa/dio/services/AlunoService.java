package com.casa.dio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.dio.model.Aluno;
import com.casa.dio.repositories.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	public AlunoRepository repository;

	public Aluno salvar(Aluno aluno) {
		aluno = repository.save(aluno);
		return aluno;
	}

	public Aluno atualizar(Long id, Aluno aluno) {
		Aluno aluno1 = buscarPorId(id);
		aluno.setId(id);
		aluno1 = repository.save(aluno);
		return aluno1;
	}

	public Aluno atualizarParcialmente(Long id, Aluno aluno) {
		Aluno aluno1 = buscarPorId(id);

		if (aluno.getId() != null)
			aluno1.setId(id);

		if (aluno.getNome() != null)
			aluno1.setNome(aluno.getNome());

		if (aluno.getCpf() != null)
			aluno1.setCpf(aluno.getCpf());

		if (aluno.getBairro() != null)
			aluno1.setBairro(aluno.getBairro());

		if (aluno.getDataNascimento() != null)
			aluno1.setDataNascimento(aluno.getDataNascimento());

		if (aluno.getAvaliacoesFisicas() != null)
			aluno1.setAvaliacoesFisicas(aluno.getAvaliacoesFisicas());

		aluno1 = repository.save(aluno1);

		return aluno1;
	}

	public Aluno buscarPorId(Long id) {
		Aluno aluno = repository.findById(id).orElseThrow(() -> new RuntimeException("Objeto não Encontrado!"));
		return aluno;
	}

	public List<Aluno> buscarTodos() {
		List<Aluno> alunos = repository.findAll();
		return alunos;
	}

	public void deletePorId(Long id) {
		Aluno aluno = buscarPorId(id);
		repository.deleteById(aluno.getId());
	}

}
