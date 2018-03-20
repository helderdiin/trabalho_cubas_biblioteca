package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioTests {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Test
	public void buscaEmprestimosCadastrados() {

		Page<Usuario> usuarios = this.usuarioRepository.findAll(new PageRequest(0, 10));
		assertThat(usuarios.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaEmprestimoEspecificoCadastrado() {

		Usuario usuario = this.usuarioRepository.findByEmail("helder@helder.com");
		assertThat(usuario).isNotNull();
		assertThat(usuario.getUsername()).isEqualTo("helder");

	}
	
	@Test
	public void buscaAutorEspecificoNaoCadastrado() {

		Usuario usuario = this.usuarioRepository.findByEmail("dsja8921@dhsuia.com");
		assertThat(usuario).isNull();

	}
	
	@Test
	public void adicionaUsuario() {
		Usuario novoUsuario = new Usuario();
		
		novoUsuario.setEmail("teste@teste.com");
		novoUsuario.setPassword("123456789");
		novoUsuario.setUsername("teste");
		

		this.usuarioRepository.save(novoUsuario);
		
		Usuario usuario = this.usuarioRepository.findByEmail("teste@teste.com");
		assertThat(usuario).isNotNull();

	}
	
	@Test
	public void alteraUsuario() {
		
		Usuario usuarioAlterado = this.usuarioRepository.findByEmail("kassia@kassia.com");
		
		usuarioAlterado.setUsername("NovoNome");
		
		this.usuarioRepository.save(usuarioAlterado);
		
		Usuario usuario = this.usuarioRepository.findByEmail("kassia@kassia.com");
		assertThat(usuario).isNotNull();

	}
	
	@Test
	public void deletaUsuario() {
		
		Usuario usuario = this.usuarioRepository.findByEmail("teste@testeeee.commmm");
		this.usuarioRepository.delete(usuario);
		
		Usuario procuraUsuario = this.usuarioRepository.findByEmail("teste@testeeee.commmm");
		assertThat(procuraUsuario).isNull();

	}

}
