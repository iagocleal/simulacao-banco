package br.com.iago.simulacaoBanco.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import br.com.iago.simulacaoBanco.enums.custom.CustomEnumDeserializer;
import br.com.iago.simulacaoBanco.enums.custom.CustomEnumJPAConverter;
import br.com.iago.simulacaoBanco.enums.custom.IBaseEnum;
import lombok.Getter;

@Getter
@JsonDeserialize(using = CustomEnumDeserializer.class)
@JsonFormat(shape = Shape.OBJECT)
public enum TipoMovimentacaoEnum implements IBaseEnum<TipoMovimentacaoEnum> {

	RECEITA(1, "RECEITA"),
	DESPESA(2, "DESPESA");
	
	private Integer codigo;
	private String descricao;

	TipoMovimentacaoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static class Converter extends CustomEnumJPAConverter<TipoMovimentacaoEnum, Integer> {
		public Converter() {
			super(TipoMovimentacaoEnum.class);
		}
	}
}