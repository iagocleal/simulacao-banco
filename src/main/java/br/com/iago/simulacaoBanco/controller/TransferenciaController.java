package br.com.iago.simulacaoBanco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.iago.simulacaoBanco.dto.TransferenciaDto;
import br.com.iago.simulacaoBanco.dto.TransferenciaFuturaDto;
import br.com.iago.simulacaoBanco.model.Conta;
import br.com.iago.simulacaoBanco.model.Transferencia;
import br.com.iago.simulacaoBanco.service.TransferenciaService;

@RestController
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@PostMapping
	public ResponseEntity<Transferencia> transferir(@RequestBody TransferenciaDto transferenciaDto) {
		return new ResponseEntity<Transferencia>(transferenciaService.transferir(transferenciaDto), HttpStatus.CREATED);
	}
	
	@PostMapping("/futura")
	public ResponseEntity<?> transferenciaFutura(@RequestBody TransferenciaFuturaDto transferenciaFuturaDto) {
		transferenciaService.transferenciaFutura(transferenciaFuturaDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/estornar/{id}")
	public ResponseEntity<?> estornarTransferencia(@PathVariable Long id) {
		transferenciaService.estornarTransferencia(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/futuras/{idConta}")
	public ResponseEntity<List<Transferencia>> getTransferenciasFutura(@PathVariable Long idConta) {
		return new ResponseEntity<List<Transferencia>>(transferenciaService.getTransferenciasFutura(idConta),  HttpStatus.OK);
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Transferencia> salvar(@RequestBody Transferencia transferencia) {
		return new ResponseEntity<Transferencia>(transferenciaService.salvar(transferencia), HttpStatus.CREATED);
	}
	
}
