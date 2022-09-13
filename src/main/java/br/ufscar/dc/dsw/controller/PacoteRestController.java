package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.service.spec.IAgencyService;
import br.ufscar.dc.dsw.service.spec.ICompraService;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@RestController
public class PacoteRestController {
    
	@Autowired
	private IPacoteService service;

	@Autowired
	private ICompraService service_compra;

    @Autowired
    private IAgencyService agenciaService;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Pacote pacote, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				pacote.setId(((Integer) id).longValue());
			} else {
				pacote.setId(((Long) id));
			}
		}

		pacote.setNome((String) json.get("nome"));
        pacote.setData((String) json.get("date"));
		pacote.setDuracao((Integer) json.get("duracao"));
		Double value = (Double) json.get("preco");
		pacote.setPreco(BigDecimal.valueOf(value));
		pacote.setDescricao((String) json.get("descricao"));
        pacote.setDestinos((String) json.get("destinos"));
	}

	@GetMapping(path = "/pacotes")
	public ResponseEntity<List<Pacote>> lista() {
		List<Pacote> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/pacotes/{id}")
	public ResponseEntity<Pacote> lista(@PathVariable("id") long id) {
		Pacote pacote = service.buscarPorId(id);
		if (pacote == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pacote);
	}

	@GetMapping(path = "/pacotes/clientes/{id}")
	public ResponseEntity<List<Compra>> listaPorCliente(@PathVariable("id") long idCliente) {
		List<Compra> compra = service_compra.buscarTodosPorClienteId(idCliente);
		if (compra == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(compra);
	}
	
	@GetMapping(path = "/pacotes/agencias/{id}")
	public ResponseEntity<Pacote> listaPorAgencia(@PathVariable("id") long idAgencia) {
		Pacote pacote = service.buscarTodosPorAgencia(idAgencia);
		if (pacote == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pacote);
	}

	@GetMapping(path = "pacotes/destinos/{destinos}")
	public ResponseEntity<Pacote> listaPorAgencia(@PathVariable("destinos") String destinos) {
		Pacote pacote = service.buscarTodosPorDestinos(destinos);
		if (pacote == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pacote);
	}

	@PostMapping(path = "/pacotes/agencias/{id}")
	@ResponseBody
	public ResponseEntity<Pacote> cria(@PathVariable("id") long idAgencia, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Pacote pacote = new Pacote();
				parse(pacote, json);
				Agencia agencia = agenciaService.buscarPorId(idAgencia);
				if(agencia == null){
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
				}
				pacote.setAgencia(agencia);
				service.salvar(pacote);
				return ResponseEntity.ok(pacote);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
}