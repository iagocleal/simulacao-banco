package br.com.iago.simulacaoBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.iago.simulacaoBanco.model.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long> {

	@Query("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.conta.id = :id")
	Double getSaldo(@Param("id") Long id);
}
