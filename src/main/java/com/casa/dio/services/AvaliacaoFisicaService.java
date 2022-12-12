package com.casa.dio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.dio.model.Aluno;
import com.casa.dio.model.AvaliacaoFisica;
import com.casa.dio.model.dtos.AvaliacaoFisicaDto;
import com.casa.dio.repositories.AlunoRepository;
import com.casa.dio.repositories.AvaliacaoFisicaRepository;

@Service
public class AvaliacaoFisicaService {

	@Autowired
	public AvaliacaoFisicaRepository repository;

	@Autowired
	public AlunoRepository alunoRepository;

	public AvaliacaoFisicaDto salvar(AvaliacaoFisicaDto avaliacao) {

		Aluno aluno = alunoRepository.findById(avaliacao.getAlunoId())
				.orElseThrow(() -> new RuntimeException("Aluno Não Encontrado!"));

		AvaliacaoFisica avaliacaoFisica = new AvaliacaoFisica(aluno, avaliacao.getDataDaAvaliacao(),
				avaliacao.getPeso(), avaliacao.getAltura());
		avaliacaoFisica = repository.save(avaliacaoFisica);

		AvaliacaoFisicaDto dto = new AvaliacaoFisicaDto(avaliacaoFisica);
		return dto;
	}

	public AvaliacaoFisica atualizar(Long id, AvaliacaoFisica avaliacaoFisica) {

		AvaliacaoFisica avaliacaoFisica1 = buscarPorId(id);

		if (avaliacaoFisica.getAluno() == null) {
			avaliacaoFisica1 = new AvaliacaoFisica(id, avaliacaoFisica1.getAluno(),
					avaliacaoFisica.getDataDaAvaliacao(), avaliacaoFisica.getPeso(), avaliacaoFisica.getAltura());
		} else {
			avaliacaoFisica1 = new AvaliacaoFisica(id, avaliacaoFisica.getAluno(), avaliacaoFisica.getDataDaAvaliacao(),
					avaliacaoFisica.getPeso(), avaliacaoFisica.getAltura());
		}

		avaliacaoFisica1 = repository.save(avaliacaoFisica1);

		return avaliacaoFisica1;
	}

	public AvaliacaoFisica atualizarParcialmente(Long id, AvaliacaoFisica avaliacaoFisica) {
		AvaliacaoFisica avaliacaoFisica1 = buscarPorId(id);

		if (avaliacaoFisica.getId() != null)
			avaliacaoFisica1.setId(avaliacaoFisica.getId());

		if (avaliacaoFisica.getAluno() != null)
			avaliacaoFisica1.setAluno(avaliacaoFisica.getAluno());

		if (avaliacaoFisica.getDataDaAvaliacao() != null)
			avaliacaoFisica1.setDataDaAvaliacao(avaliacaoFisica.getDataDaAvaliacao());

		if (avaliacaoFisica.getPeso() != null)
			avaliacaoFisica1.setPeso(avaliacaoFisica.getPeso());

		if (avaliacaoFisica.getAltura() != null)
			avaliacaoFisica1.setAltura(avaliacaoFisica.getAltura());

		avaliacaoFisica1 = repository.save(avaliacaoFisica1);

		return avaliacaoFisica1;
	}

	public AvaliacaoFisica buscarPorId(Long id) {
		AvaliacaoFisica avaliacaoFisisca = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Objeto não Encontrado!"));
		return avaliacaoFisisca;
	}

	public List<AvaliacaoFisica> buscarTodos() {
		List<AvaliacaoFisica> avaliacoesFisiscas = repository.findAll();
		return avaliacoesFisiscas;
	}

	public void deletePorId(Long id) {
		AvaliacaoFisica avaliacaoFisisca = buscarPorId(id);
		repository.deleteById(avaliacaoFisisca.getId());
	}

}
