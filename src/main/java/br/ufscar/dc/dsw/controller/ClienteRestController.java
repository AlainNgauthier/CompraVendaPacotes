package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;
import org.json.simple.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@RestController
public class ClienteRestController {
    
	@Autowired
	private IClienteService service;

	private boolean isJSONValid(String jsonInString) {
		try {
			return new ObjectMapper().readTree(jsonInString) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void parse(Cliente cliente, JSONObject json) {

		Object id = json.get("id");
		if (id != null) {
			if (id instanceof Integer) {
				cliente.setId(((Integer) id).longValue());
			} else {
				cliente.setId((Long) id);
			}
		}

        cliente.setEmail((String) json.get("email"));
        cliente.setSenha((String) json.get("senha"));
		cliente.setFuncao((String) json.get("funcao"));
        cliente.setAtivo((Boolean) json.get("ativo"));
		cliente.setCpf((String) json.get("cpf"));
		cliente.setNome((String) json.get("nome"));
		cliente.setTelefone((String) json.get("telefone"));
        cliente.setSexo((String) json.get("sexo"));
        cliente.setDataNasc((String) json.get("dataNasc"));
	}

	@GetMapping(path = "/clientes")
	public ResponseEntity<List<Cliente>> lista() {
		List<Cliente> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> lista(@PathVariable("id") long id) {
		Cliente cliente = service.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente);
	}

	@PostMapping(path = "/clientes")
	@ResponseBody
	public ResponseEntity<Cliente> cria(@RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = new Cliente();
				parse(cliente, json);
				service.salvar(cliente);
				return ResponseEntity.ok(cliente);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@PutMapping(path = "/clientes/{id}")
	public ResponseEntity<Cliente> atualiza(@PathVariable("id") long id, @RequestBody JSONObject json) {
		try {
			if (isJSONValid(json.toString())) {
				Cliente cliente = service.buscarPorId(id);
				if (cliente == null) {
					return ResponseEntity.notFound().build();
				} else {
					parse(cliente, json);
					service.salvar(cliente);
					return ResponseEntity.ok(cliente);
				}
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}

	@DeleteMapping(path = "/clientes/{id}")
	public ResponseEntity<String> remove(@PathVariable("id") long id) {

		Cliente cliente = service.buscarPorId(id);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		} else {
			service.excluir(id);
			return new ResponseEntity<String>("Cliente excluído", HttpStatus.OK);
			}
		}
	}