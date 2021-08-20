package br.com.iago.simulacaoBanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iago.simulacaoBanco.model.Movimentacao;
import br.com.iago.simulacaoBanco.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {
	
	@Autowired
	private MovimentacaoRepository movimentacaoRepository;

	public Double getSaldo(Long id) {
		return movimentacaoRepository.getSaldo(id);
	}

	public void save(Movimentacao movimentacao) {
		movimentacaoRepository.save(movimentacao);
	}

}
