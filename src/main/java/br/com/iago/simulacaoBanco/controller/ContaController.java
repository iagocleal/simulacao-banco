package br.com.iago.simulacaoBanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iago.simulacaoBanco.service.ContaService;

@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	private ContaService contaService;	
	
	@GetMapping("/saldo/{id}")
	public ResponseEntity<Double> getById(@PathVariable Long id) {
		return new ResponseEntity<Double>(contaService.getSaldo(id),  HttpStatus.OK);
	}
}
