package br.com.iago.simulacaoBanco.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;

import br.com.iago.simulacaoBanco.enums.TipoContaEnum;
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
@Table(name = "CONTA", schema = "SIMULACAO")
public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1810338303095961115L;

	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique=true, updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "AGENCIA")
	private String agencia;
	
	@Column(name = "DIGITO_AGENCIA")
	private String digitoAgencia;
	
	@Column(name = "NUMERO")
	private String numero;
	
	@Column(name = "DIGITO")
	private String digito;
	
	@ManyToOne
	@JoinColumn(name="BANCO_FK")
	private Banco banco;
	
	@Column(name = "TIPO_CONTA")
	@Convert(converter = TipoContaEnum.Converter.class)
	private TipoContaEnum tipoContaEnum;
	
	@OneToMany(mappedBy = "conta", targetEntity = Movimentacao.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Movimentacao> lstMovimentacao;

}
