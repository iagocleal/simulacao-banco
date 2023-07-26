package br.com.iago.simulacaoBanco.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.iago.simulacaoBanco.dto.TransferenciaDto;
import br.com.iago.simulacaoBanco.dto.TransferenciaFuturaDto;
import br.com.iago.simulacaoBanco.enums.TipoLancamentoEnum;
import br.com.iago.simulacaoBanco.enums.TipoMovimentacaoEnum;
import br.com.iago.simulacaoBanco.exception.BusinessException;
import br.com.iago.simulacaoBanco.exception.ResourceNotFoundException;
import br.com.iago.simulacaoBanco.model.Conta;
import br.com.iago.simulacaoBanco.model.Movimentacao;
import br.com.iago.simulacaoBanco.model.Transferencia;
import br.com.iago.simulacaoBanco.repository.TransferenciaRepository;

@Service
public class TransferenciaService {

	@Autowired
	private TransferenciaRepository transferenciaRepository;
	@Autowired
	private ContaService contaService; 
	@Autowired
	private MovimentacaoService movimentacaoService; 
	
	@Transactional
	public void estornarTransferencia(Long id) {
		LocalDateTime data = LocalDateTime.now();
		Transferencia transferencia = transferenciaRepository.findById(id).map(t -> {
			t.setEstorno(true);
			return t;
		}).orElseThrow(() -> new ResourceNotFoundException("Transferencia não encontrada."));
		
		gerarMovimentacao(transferencia.getContaOrigem(), data, TipoMovimentacaoEnum.RECEITA, TipoLancamentoEnum.ESTORNO, transferencia.getValor());
		gerarMovimentacao(transferencia.getContaDestino(), data, TipoMovimentacaoEnum.DESPESA, TipoLancamentoEnum.ESTORNO, -transferencia.getValor());
	}
	
	@Transactional
	public void transferenciaFutura(TransferenciaFuturaDto transferenciaFuturaDto) {
		validarTransferenciaFutura(transferenciaFuturaDto);
		
		LocalDateTime dataPrimeiraTransferencia = transferenciaFuturaDto.getDataPrimeiraTransferencia();
		Boolean transferido = false;
		
		Conta contaOrigem = contaService.findById(transferenciaFuturaDto.getIdContaOrigem());
		Conta contaDestino = contaService.findById(transferenciaFuturaDto.getIdContaDestino());
		
		for (int i = 0; i < transferenciaFuturaDto.getQtdParcelas(); i++) {
			gerarTransferencia(contaOrigem, contaDestino, dataPrimeiraTransferencia.plusMonths(i), transferenciaFuturaDto.getValor(), transferido);
		}
	}

	@Transactional
	public Transferencia transferir(TransferenciaDto transferenciaDto) {
		ValidarTransferencia(transferenciaDto.getIdContaOrigem(), transferenciaDto.getValor());
		
		LocalDateTime data = LocalDateTime.now();
		
		Conta contaOrigem = contaService.findById(transferenciaDto.getIdContaOrigem());
		Conta contaDestino = contaService.findById(transferenciaDto.getIdContaDestino());
		
		gerarMovimentacao(contaOrigem, data, TipoMovimentacaoEnum.DESPESA, TipoLancamentoEnum.TRANSFERENCIA, -transferenciaDto.getValor());
		gerarMovimentacao(contaDestino, data, TipoMovimentacaoEnum.RECEITA, TipoLancamentoEnum.TRANSFERENCIA, transferenciaDto.getValor());
		
		return gerarTransferencia(contaOrigem, contaDestino, data, transferenciaDto.getValor(), true);
	}

	public List<Transferencia> getTransferenciasFutura(Long idConta) {
		return transferenciaRepository.findAllByContaOrigemIdAndTransferido(idConta, false);
	}

	private Transferencia gerarTransferencia(Conta contaOrigem, Conta contaDestino, LocalDateTime data, double valor, boolean transferido) {
		Transferencia transf = new Transferencia(contaOrigem, contaDestino, data, valor, transferido);
		return transferenciaRepository.save(transf);
	}

	private void gerarMovimentacao(Conta conta, LocalDateTime data, TipoMovimentacaoEnum tipoMovimentacao, TipoLancamentoEnum tipoLancamento, double valor) {
		Movimentacao mov = new Movimentacao(conta, data, tipoMovimentacao, tipoLancamento, valor);
		movimentacaoService.save(mov);
	}
	
	private void validarTransferenciaFutura(TransferenciaFuturaDto transferenciaFuturaDto) {
		if (transferenciaFuturaDto.getDataPrimeiraTransferencia().isBefore(LocalDateTime.now())) {
			throw new BusinessException("Data deve ser superior a data atual!");
		}
		
		if (transferenciaFuturaDto.getQtdParcelas() <= 0) {
			throw new BusinessException("Quantidade de parcelas inválida!");
		}
		
		ValidarTransferencia(transferenciaFuturaDto.getIdContaOrigem(), transferenciaFuturaDto.getValor());		
	}
	
	private void ValidarTransferencia(Long idContaOrigem, double valor) {
		if (valor <= 0) {
			throw new BusinessException("Valor inválido!");
		}
		
		if (valor > contaService.getSaldo(idContaOrigem)) {
			throw new BusinessException("Saldo insuficiente!");
		}		
	}

	public Transferencia salvar(Transferencia transferencia) {
		return transferenciaRepository.save(transferencia);
	}

}
