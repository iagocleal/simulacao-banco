package br.com.iago.simulacaoBanco;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.iago.simulacaoBanco.dto.TransferenciaDto;
import br.com.iago.simulacaoBanco.dto.TransferenciaFuturaDto;
import br.com.iago.simulacaoBanco.repository.ContaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = SimulacaoBancoApplication.class)
@AutoConfigureMockMvc
public class TransferenciaControllerTest {

	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired 
	private ContaRepository ContaRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void deveSalvarUmaTransferencia() throws Exception {
		TransferenciaDto transferenciaDto = new TransferenciaDto();
		transferenciaDto.setIdContaOrigem(ContaRepository.findByNumero("4444444444").getId());
		transferenciaDto.setIdContaDestino(ContaRepository.findByNumero("5555555555").getId());
		transferenciaDto.setValor(1000);
		
		mockMvc.perform(post("/transferencia")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(transferenciaDto)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void deveSalvarUmaTransferenciaFutura() throws Exception {
		TransferenciaFuturaDto transferenciaFuturaDto = new TransferenciaFuturaDto();
		transferenciaFuturaDto.setIdContaOrigem(ContaRepository.findByNumero("4444444444").getId());
		transferenciaFuturaDto.setIdContaDestino(ContaRepository.findByNumero("5555555555").getId());
		transferenciaFuturaDto.setValor(1000);
		transferenciaFuturaDto.setQtdParcelas(2);
		transferenciaFuturaDto.setDataPrimeiraTransferencia(LocalDateTime.now().plusDays(1));
		
		mockMvc.perform(post("/transferencia/futura")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(transferenciaFuturaDto)))
				.andExpect(status().isCreated());
	}

}
