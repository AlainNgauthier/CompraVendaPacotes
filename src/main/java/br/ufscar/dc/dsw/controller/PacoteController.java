package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IAgencyService;
import br.ufscar.dc.dsw.service.spec.IPacoteService;

@Controller
@RequestMapping("/pacotes")
public class PacoteController {
    
	@Autowired
	private IPacoteService pacoteService;

	@Autowired
	private IAgencyService agenciaService;

	@GetMapping("/cadastrar")
	public String cadastrar(ModelMap model, Pacote pacote) {
		model.addAttribute("agencia", this.getAgencia());
		return "pacote/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("pacotes", pacoteService.buscarTodos());
		return "pacote/lista";
	}

	@GetMapping("/filtrar")
	public String filtrar(ModelMap model, Long id) {
		model.addAttribute("pacotes", agenciaService.buscarPorId(id).getPacotes());
		return "pacote/lista";
	}

	private String getUsuario() {
		UsuarioDetails UsuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return UsuarioDetails.getUsuario().getFuncao();
		
	}

	private Agencia getAgencia() {
		UsuarioDetails UsuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return agenciaService.buscarPorId(UsuarioDetails.getUsuario().getId());
		
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "pacote/cadastro";
		}

		pacoteService.salvar(pacote);
		attr.addFlashAttribute("sucess", "Pacote inserido com sucesso");
		return "redirect:/pacotes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {

		model.addAttribute("pacote", pacoteService.buscarPorId(id));

		if(this.getUsuario().equals("ROLE_ADMIN")){
		return "pacote/cadastro";
	}
		
		else{
		if(agenciaService.buscarPorId(this.getAgencia().getId()).getId().equals(pacoteService.buscarPorId(id).getAgencia().getId())){
			model.addAttribute("agencia", agenciaService.buscarPorId(this.getAgencia().getId()));
			return "pacote/cadastro";
		}

		else{
			return "redirect:/pacotes/listar";
		}
	}
	}

	@PostMapping("/editar")
	public String editar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "pacote/cadastro";
		}

		pacoteService.salvar(pacote);
		attr.addFlashAttribute("sucess", "Pacote editado com sucesso.");
		return "redirect:/pacotes/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		pacoteService.excluir(id);
		attr.addFlashAttribute("sucess", "Pacote excluído com sucesso.");
		return "redirect:/pacotes/listar";
	}

	@ModelAttribute("agencias")
	public List<Agencia> listaAgencia() {
		return agenciaService.buscarTodos();
	}
}
