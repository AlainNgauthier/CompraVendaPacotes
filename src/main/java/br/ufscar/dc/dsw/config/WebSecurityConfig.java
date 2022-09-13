package br.ufscar.dc.dsw.config;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.ufscar.dc.dsw.security.UsuarioDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService userDetailsService() {
		return new UsuarioDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
				http.csrf().disable().authorizeRequests()
				// REST
				.antMatchers("/clientes", "/agencias", "/pacotes").permitAll()
				.antMatchers("/clientes/{\\d+}", "/agencias/{\\d+}").permitAll()
				.antMatchers("/pacotes/agencias/{\\d+}").permitAll()
				.antMatchers("/pacotes/clientes/{\\d+}").permitAll()
				.antMatchers("/pacotes/destinos/{\\w+}").permitAll()
				// 
				.antMatchers("/error", "/login/**", "/js/**", "/css/**", "/image/**", "/webjars/**", "/agencias/listar", "/pacotes/listar").permitAll()
				.antMatchers("/pacotes/cadastrar", "/pacotes/editar/**", "/pacotes/excluir/**", "/pacotes/salvar/**").hasAnyRole("AGENCIA", "ADMIN")
				.antMatchers("/clientes/**", "/agencias/**", "/pacotes/**").hasRole("ADMIN")
				.antMatchers("/compras/**").hasRole("USER")
				.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}
}