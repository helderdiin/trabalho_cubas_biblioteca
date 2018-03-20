package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.repository.AutorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutorTests {

	@Autowired
	AutorRepository autorRepository;

	@Test
	public void buscaAutoresCadastrados() {

		Page<Autor> autores = this.autorRepository.findAll(new PageRequest(0, 10));
		assertThat(autores.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaAutorEspecificoCadastrado() {

		Autor autor = this.autorRepository.findByNome("helder");
		assertThat(autor).isNotNull();
		assertThat(autor.getId()).isEqualTo(1);

	}
	
	@Test
	public void buscaAutorEspecificoNaoCadastrado() {

		Autor autor = this.autorRepository.findByNome("7ncisbnwixbsiww");
		assertThat(autor).isNull();

	}
	
	@Test
	public void adicionaAutor() {
		
		Autor novoAutor = new Autor();
		
		novoAutor.setNome("NovoAutor");

		this.autorRepository.save(novoAutor);
		
		Autor autor = this.autorRepository.findByNome("NovoAutor");
		assertThat(autor).isNotNull();

	}
	
	@Test
	public void alteraAutor() {
		
		Autor autorAlterado = this.autorRepository.findByNome("marcio");
		
		autorAlterado.setNome("NovoNome");
		
		this.autorRepository.save(autorAlterado);
		
		Autor autor = this.autorRepository.findByNome("NovoNome");
		assertThat(autor).isNotNull();

	}
	
	@Test
	public void deletaAutor() {
		
		Autor autor = this.autorRepository.findByNome("pongo");
		this.autorRepository.delete(autor);
		
		Autor procuraAutor = this.autorRepository.findByNome("pongo");
		assertThat(procuraAutor).isNull();

	}


}
