package br.ufscar.dc.dsw;

import org.springframework.boot.SpringApplication;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.yaml.snakeyaml.composer.Composer;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufscar.dc.dsw.dao.IAgencyDAO;
import br.ufscar.dc.dsw.dao.IClientDAO;
import br.ufscar.dc.dsw.dao.IPurchaseDAO;
import br.ufscar.dc.dsw.dao.IPacoteDAO;
import br.ufscar.dc.dsw.dao.IUserDAO;
import br.ufscar.dc.dsw.domain.Agency;
import br.ufscar.dc.dsw.domain.Client;
import br.ufscar.dc.dsw.domain.Purchase;
import br.ufscar.dc.dsw.domain.Pacote;

@SpringBootApplication
public class CompraVendaPacotesTuristicosApplication {
	
	//remover o log
	private static final Logger log = LoggerFactory.getLogger(CompraVendaPacotesTuristicosApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CompraVendaPacotesTuristicosApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IUserDAO userDAO, IClientDAO clienteDAO, BCryptPasswordEncoder encoder, IPacoteDAO pacoteDAO, IAgencyDAO agencyDAO, IPurchaseDAO purchaseDAO) {
		return (args) -> {

			// Registering Client
			try {
				log.info("registering Cliente 1");
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
				log.info("registering Cliente 2");
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
				log.info("registering DreamAgency");
				Agency agency1 = new Agency();
				agency1.setEmail("dreamagency@gmail.com");
				agency1.setSenha(encoder.encode("DreamAgency"));
				agency1.setFuncao("ROLE_AGENCIA");
				agency1.setAtivo(true);
				agency1.setCnpj("12.341.234/0001-88");
				agency1.setNome("DreamAgency");
				agency1.setDescricao("Book here your next dream travel!");
				userDAO.save(agency1);
				log.info("DreamAgency 1 registered");
			} catch (Exception e) {
				log.info("error on registering DreamAgency: " + e.getLocalizedMessage());
			}

//			try {
//				log.info("Salvando Agencia 2");
//				Agencia a2 = new Agencia();
//				a2.setEmail("Maritima@gmail.com");
//				a2.setSenha(encoder.encode("Maritima"));
//				a2.setFuncao("ROLE_AGENCIA");
//				a2.setAtivo(true);
//				a2.setCnpj("56.534.546/0003-45");
//				a2.setNome("Marítima");
//				a2.setDescricao("Melhor Agência de Turismo com ênfase em Pacotes Marítmos.");
//				usuarioDAO.save(a2);
//				log.info("Agencia 2 salva");
//			} catch (Exception e) {
//				log.info("Falha ao salvar Agencia 2: " + e.getLocalizedMessage());
//			}

//			try {
//				log.info("Salvando Agencia 3");
//				Agencia a3 = new Agencia();
//				a3.setEmail("Popular@gmail.com");
//				a3.setSenha(encoder.encode("Popular"));
//				a3.setFuncao("ROLE_AGENCIA");
//				a3.setAtivo(true);
//				a3.setCnpj("76.234.554/0008-99");
//				a3.setNome("Agência Popular");
//				a3.setDescricao("Agência com os melhores preços");
//				usuarioDAO.save(a3);
//				log.info("Agencia 3 salva");
//			} catch (Exception e) {
//				log.info("Falha ao salvar Agencia 3: " + e.getLocalizedMessage());
//			}

			// Registering Pacotes
			try {
				log.info("registering Pacote 1");
				Pacote pacote1 = new Pacote();
				pacote1.setNome("Travel: XX City");
				pacote1.setAgencia(agencyDAO.findByNome("DreamAgency"));
				pacote1.setData("2022/09/05");
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
				log.info("registering Pacote 2");
				Pacote pacote2 = new Pacote();
				pacote2.setNome("Travel: XY City");
				pacote2.setAgencia(agencyDAO.findByNome("DreamAgency"));
				pacote2.setData("2022/09/09");
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
				log.info("registering Purchase Cliente 2");
				Purchase purchase1 = new Purchase();
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
