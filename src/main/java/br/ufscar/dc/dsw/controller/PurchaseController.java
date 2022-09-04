package br.ufscar.dc.dsw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Purchase;
import br.ufscar.dc.dsw.domain.Pacote;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.IClientService;
import br.ufscar.dc.dsw.service.spec.IPurchaseService;
import br.ufscar.dc.dsw.service.spec.IPacoteService;
//import br.ufscar.dc.dsw.util.Mail;


@Controller
@RequestMapping("/compras")
public class PurchaseController {
	
	@Autowired
	private IPurchaseService service;
	
	@Autowired
	private IPacoteService PacoteService;

	@Autowired
	private IClientService ClienteService;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Purchase compra) {
		compra.setCliente(this.getCliente());
		return "compra/cadastro";
	}
	
	private Client getCliente() {
		UsuarioDetails UsuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ClienteService.buscarPorId(UsuarioDetails.getUsuario().getId());
		
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {			
		model.addAttribute("compras",service.buscarTodosPorCliente(this.getCliente()));
		
		//System.out.println("\n\n\n\nOPAAAAAAAAAAAAAAAA\n\n\n\n\n\n" + service.buscarTodosPorCliente(this.getCliente()).get(0).getPacote().getNome());
		return "compra/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Pacote pacote, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "compra/cadastro";
		}

		Purchase compra = new Purchase();
		
		compra.setCliente(this.getCliente());
		compra.setPacote(pacote);
		compra.setPreco(pacote.getPreco());
		
		service.salvar(compra);

// Envia e-mail
//		try {
//            Mail email = new Mail();
//            email.sendMail(this.getCliente().getEmail());
//        } catch (Exception e){
//        }
		
		attr.addFlashAttribute("sucess", "Compra inserida com sucesso.");
		return "redirect:/compras/listar";
	}
	
	@ModelAttribute("pacotes")
	public List<Pacote> listaPacotes() {
		return PacoteService.buscarTodos();
	}
}
