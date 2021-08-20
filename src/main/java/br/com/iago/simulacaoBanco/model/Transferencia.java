package br.com.iago.simulacaoBanco.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name = "TRANSFERENCIA", schema = "SIMULACAO")
public class Transferencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2105354366534525252L;
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="CONTA_ORIGEM")
	private Conta contaOrigem;
	
	@ManyToOne
	@JoinColumn(name="CONTA_DESTINO")
	private Conta contaDestino;
	
	@Column(name = "DATA_TRANSFERENCIA")
	private LocalDateTime dataMovimentacao;
	
	@Column(name = "VALOR")
	private double valor;
	
	@Column(name = "TRANSFERIDO")
	private boolean transferido;
	
	@Column(name = "ESTORNO")
	private boolean estorno;

	public Transferencia(Conta contaOrigem, Conta contaDestino, LocalDateTime dataMovimentacao, double valor,
			boolean transferido) {
		super();
		this.contaOrigem = contaOrigem;
		this.contaDestino = contaDestino;
		this.dataMovimentacao = dataMovimentacao;
		this.valor = valor;
		this.transferido = transferido;
	}
	
	

}
