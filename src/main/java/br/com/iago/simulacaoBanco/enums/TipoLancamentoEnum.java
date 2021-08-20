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
public enum TipoLancamentoEnum implements IBaseEnum<TipoLancamentoEnum> {

	TRANSFERENCIA(1, "TRANSFERÊNCIA"),
	PAGAMENTO(2, "PAGAMENTO"),
	SALARIO(3, "SALÁRIO"),
	ESTORNO(4, "ESTORNO");
	
	private Integer codigo;
	private String descricao;

	TipoLancamentoEnum(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public static class Converter extends CustomEnumJPAConverter<TipoLancamentoEnum, Integer> {
		public Converter() {
			super(TipoLancamentoEnum.class);
		}
	}
}