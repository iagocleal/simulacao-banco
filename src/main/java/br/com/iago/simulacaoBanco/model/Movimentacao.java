package br.com.iago.simulacaoBanco.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import br.com.iago.simulacaoBanco.enums.TipoLancamentoEnum;
import br.com.iago.simulacaoBanco.enums.TipoMovimentacaoEnum;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@JsonSerializableSchema
@Getter
@Setter
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "MOVIMENTACAO", schema = "SIMULACAO")
public class Movimentacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1309308530785975487L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "CONTA_FK", referencedColumnName = "ID")
	@JsonBackReference
	private Conta conta;
	
	@Column(name = "DATA_MOVIMENTACAO")
	private LocalDateTime dataMovimentacao;
	
	@Column(name = "TIPO_MOVIMENTACAO")
	@Convert(converter = TipoMovimentacaoEnum.Converter.class)
	private TipoMovimentacaoEnum tipoMovimentacao;
	
	@Column(name = "TIPO_LANCAMENTO")
	@Convert(converter = TipoLancamentoEnum.Converter.class)
	private TipoLancamentoEnum TipoLancamento;
	
	@Column(name = "VALOR")
	private double valor;

	public Movimentacao(Conta conta, LocalDateTime dataMovimentacao, TipoMovimentacaoEnum tipoMovimentacao,
			TipoLancamentoEnum tipoLancamento, double valor) {
		super();
		this.conta = conta;
		this.dataMovimentacao = dataMovimentacao;
		this.tipoMovimentacao = tipoMovimentacao;
		TipoLancamento = tipoLancamento;
		this.valor = valor;
	}
	
	
	
}
