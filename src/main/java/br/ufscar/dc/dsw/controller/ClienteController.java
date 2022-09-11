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
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.service.spec.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
	private IClienteService service;

	@Autowired
	BCryptPasswordEncoder encoder;

    @GetMapping("/cadastrar")
    public String cadastrar(Cliente cliente){
        return "cliente/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model){
        model.addAttribute("clientes", service.buscarTodos());
        return "cliente/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
			return "cliente/cadastro";
		}

        Cliente cliente2 = cliente;
		cliente2.setSenha(encoder.encode(cliente.getSenha()));

        service.salvar(cliente2);
		attr.addFlashAttribute("sucess", "Cliente inserido");
		
        return "redirect:/clientes/listar";
    }

    @GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("cliente", service.buscarPorId(id));
		return "cliente/cadastro";
	}

    @PostMapping("/editar")
	public String editar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attr) {

		if (result.getFieldErrorCount() > 1 || result.getFieldError("cpf") == null) {
			return "cliente/cadastro";
		}

		Cliente cliente2 = cliente;
		cliente2.setSenha(encoder.encode(cliente.getSenha()));
		
        service.salvar(cliente2);
		attr.addFlashAttribute("sucess", "Cliente removido");
		return "redirect:/clientes/listar";
	}

    @GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		service.excluir(id);
		model.addAttribute("sucess", "Cliente removido");
		
		return listar(model);
	}

}
