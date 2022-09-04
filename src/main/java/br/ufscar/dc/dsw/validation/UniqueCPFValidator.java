package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IClienteDAO;
import br.ufscar.dc.dsw.domain.Client;


@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {

	@Autowired
	private IClienteDAO clienteDAO;

	@Override
	public boolean isValid(String cpf, ConstraintValidatorContext context) {
		if (clienteDAO != null) {
			Client cliente = clienteDAO.findByCpf(cpf);

			return cliente == null;
		} else {
			return true;
		}

	}
}