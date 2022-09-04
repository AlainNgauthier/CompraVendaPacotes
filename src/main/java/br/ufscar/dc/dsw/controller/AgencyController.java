package br.ufscar.dc.dsw.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Agency;
import br.ufscar.dc.dsw.service.spec.IAgencyService;

@Controller
@RequestMapping("/agencias")
public class AgencyController {
    
	@Autowired
	private IAgencyService service;

	@Autowired
	BCryptPasswordEncoder encoder;

	@GetMapping("/cadastrar")
	public String cadastrar(Agency agencia) {
		return "agencia/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("agencias",service.buscarTodos());
		return "agencia/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Agency agencia, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "agencia/cadastro";
		}
		
		Agency agencia2 = agencia;
		agencia2.setSenha(encoder.encode(agencia.getSenha()));
		
        service.salvar(agencia2);
		attr.addFlashAttribute("sucess", "Agencia inserida com sucesso.");
		return "redirect:/agencias/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("agencia", service.buscarPorId(id));
		return "agencia/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Agency agencia, BindingResult result, RedirectAttributes attr) {
		
		if (result.getFieldErrorCount() > 1 || result.getFieldError("cnpj") == null) {
			return "agencia/cadastro";
		}

		Agency agencia2 = agencia;
		agencia2.setSenha(encoder.encode(agencia.getSenha()));
		
        service.salvar(agencia2);
		attr.addFlashAttribute("sucess", "Agencia editada com sucesso.");
		return "redirect:/agencias/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.temPacotes(id)) {
			model.addAttribute("fail", "Agencia não excluída. Possui pacote(s) vinculado(s).");
		} else {
			service.excluir(id);
			model.addAttribute("sucess", "Agencia excluída com sucesso.");
		}
		return listar(model);
	}
}
