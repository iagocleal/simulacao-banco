package br.com.iago.simulacaoBanco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iago.simulacaoBanco.model.Banco;
import br.com.iago.simulacaoBanco.service.BancoService;

@RestController
@RequestMapping("/banco")
public class BancoController {

	@Autowired
	private BancoService bancoService;	
	
	@PostMapping("/without-audit")
	public ResponseEntity<Banco> salvarWithoutLog(@RequestBody Banco banco) {
		return new ResponseEntity<Banco>(bancoService.salvarWithoutLog(banco), HttpStatus.CREATED);
	}
	
	@PostMapping("/with-audit")
	public ResponseEntity<Banco> salvarWithLog(@RequestBody Banco banco) {
		return new ResponseEntity<Banco>(bancoService.salvarWithLog(banco), HttpStatus.CREATED);
	}
}
