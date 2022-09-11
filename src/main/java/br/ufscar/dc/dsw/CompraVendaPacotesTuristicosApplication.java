package br.ufscar.dc.dsw;

import org.springframework.boot.SpringApplication;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.yaml.snakeyaml.composer.Composer;

import br.ufscar.dc.dsw.dao.IAgencyDAO;
import br.ufscar.dc.dsw.dao.IClientDAO;
import br.ufscar.dc.dsw.dao.ICompraDAO;
import br.ufscar.dc.dsw.dao.IPacoteDAO;
import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.domain.Agencia;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Compra;
import br.ufscar.dc.dsw.domain.Pacote;

@SpringBootApplication
public class CompraVendaPacotesTuristicosApplication {
	
	//remover o log
	private static final Logger log = LoggerFactory.getLogger(CompraVendaPacotesTuristicosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CompraVendaPacotesTuristicosApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IUserDAO userDAO, IClientDAO clienteDAO, BCryptPasswordEncoder encoder, IPacoteDAO pacoteDAO, IAgencyDAO agencyDAO, ICompraDAO purchaseDAO) {
		return (args) -> {

			// Registering Client
			try {
				Client cliente1 = new Client();
				cliente1.setEmail("alain@gmail.com");
				cliente1.setSenha(encoder.encode("alain"));
				cliente1.setFuncao("ROLE_ADMIN");
				cliente1.setAtivo(true);
				cliente1.setCpf("111.111.111-11");
				cliente1.setNome("Alain");
				cliente1.setSexo("Masculino");
				cliente1.setDataNasc("29/10/1995");
				cliente1.setTelefone("9149-5542");
				userDAO.save(cliente1);
				log.info("Cliente 1 registered");
			} catch (Exception e) {
				log.info("error on registering Cliente 1: " + e.getLocalizedMessage());
			}

			try {
				Client a1 = new Client();
				a1.setEmail("patricia@gmail.com");
				a1.setSenha(encoder.encode("patricia"));
				a1.setFuncao("ROLE_USER");
				a1.setAtivo(true);
				a1.setCpf("111.111.111-12");
				a1.setNome("Patricia");
				a1.setSexo("Feminino");
				a1.setDataNasc("01/01/2022");
				a1.setTelefone("9999-9999");
				userDAO.save(a1);
				log.info("Cliente 2 registered");
			} catch (Exception e) {
				log.info("error on registering Cliente 2: " + e.getLocalizedMessage());
			}
			
			// Registering Agency
			try {
				Agencia agency1 = new Agencia();
				agency1.setEmail("dreamagency@gmail.com");
				agency1.setSenha(encoder.encode("DreamAgency"));
				agency1.setFuncao("ROLE_AGENCIA");
				agency1.setAtivo(true);
				agency1.setCnpj("11.777.235/0001-44");
				agency1.setNome("DreamAgency");
				agency1.setDescricao("Book here your next dream travel!");
				userDAO.save(agency1);
				log.info("DreamAgency 1 registered");
			} catch (Exception e) {
				log.info("error on registering DreamAgency: " + e.getLocalizedMessage());
			}

			// Registering Pacotes
			try {
				Pacote pacote1 = new Pacote();
				pacote1.setNome("Travel: XX City");
				pacote1.setAgencia(agencyDAO.findByNome("DreamAgency"));
				pacote1.setData("2022/09/12");
				pacote1.setDuracao(10);
				pacote1.setPreco(BigDecimal.valueOf(1000.00));
				pacote1.setDescricao("Have your dream travel");
				pacote1.setDestinos("xx");
				pacoteDAO.save(pacote1);
				log.info("Pacote 1 salvo");
			} catch (Exception e) {
				log.info("error on registering Pacote 1: " + e.getLocalizedMessage());
			}

			try {
				Pacote pacote2 = new Pacote();
				pacote2.setNome("Travel: XY City");
				pacote2.setAgencia(agencyDAO.findByNome("DreamAgency"));
				pacote2.setData("2022/10/10");
				pacote2.setDuracao(15);
				pacote2.setPreco(BigDecimal.valueOf(1000.00));
				pacote2.setDescricao("Have your dream travel");
				pacote2.setDestinos("xy");
				pacoteDAO.save(pacote2);
				log.info("Pacote 2 registered");
			} catch (Exception e) {
				log.info("error on registering Pacote 2: " + e.getLocalizedMessage());
			}

			// Registering Purchase
			try {
				Compra purchase1 = new Compra();
				purchase1.setCliente(clienteDAO.getUserByEmail("patricia@gmail.com"));
				purchase1.setPacote(pacoteDAO.getPacoteByName("Travel: XX City"));
				purchase1.setPreco(BigDecimal.valueOf(1000.00));
				purchaseDAO.save(purchase1);
				log.info("Purchase Cliente 2 registered");
			} catch (Exception e) {
				log.info("error on registering client 2: " + e.getLocalizedMessage());
			}
		};
	}

}
