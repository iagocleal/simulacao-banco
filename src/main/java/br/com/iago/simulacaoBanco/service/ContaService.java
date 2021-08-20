package br.com.iago.simulacaoBanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iago.simulacaoBanco.exception.ResourceNotFoundException;
import br.com.iago.simulacaoBanco.model.Conta;
import br.com.iago.simulacaoBanco.repository.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	private ContaRepository contaRepository;
	@Autowired
	private MovimentacaoService movimentacaoService;

	public Double getSaldo(Long id) {
		return movimentacaoService.getSaldo(id);
	}
	
	public Conta findById(Long id) {
		return contaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada."));
	}

}
