package br.com.iago.simulacaoBanco.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaFuturaDto {

	private Long idContaOrigem;
	private Long idContaDestino;
	private double valor;
	private int qtdParcelas;
	private LocalDateTime dataPrimeiraTransferencia;
}
