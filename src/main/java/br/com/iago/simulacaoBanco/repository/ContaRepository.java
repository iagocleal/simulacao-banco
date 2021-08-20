package br.com.iago.simulacaoBanco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.iago.simulacaoBanco.model.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

	Conta findByNumero(String numero);
}
