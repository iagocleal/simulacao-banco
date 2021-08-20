package br.com.iago.simulacaoBanco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iago.simulacaoBanco.model.Transferencia;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	List<Transferencia> findAllByContaOrigemIdAndTransferido(Long id, boolean transferido);
}
