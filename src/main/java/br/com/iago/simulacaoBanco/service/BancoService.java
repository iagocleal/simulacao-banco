package br.com.iago.simulacaoBanco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iago.simulacaoBanco.model.Banco;
import br.com.iago.simulacaoBanco.repository.BancoRepository;

@Service
public class BancoService {

	@Autowired
	private BancoRepository bancoRepository;
	
	public Banco salvarWithoutLog(Banco banco) {
		banco.setAudit(false);
		return bancoRepository.save(banco);
	}
	
	public Banco salvarWithLog(Banco banco) {
		banco.setAudit(true);
		return bancoRepository.save(banco);
	}
}
