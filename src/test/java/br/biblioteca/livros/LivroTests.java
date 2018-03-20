package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.AutorRepository;
import br.biblioteca.livros.repository.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LivroTests {

	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	AutorRepository autorRepository;

	@Test
	public void buscaEmprestimosCadastrados() {

		Page<Livro> livros = this.livroRepository.findAll(new PageRequest(0, 10));
		assertThat(livros.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaEmprestimoEspecificoCadastrado() {

		Livro livro = this.livroRepository.findByNome("Livro novo");
		assertThat(livro).isNotNull();
		assertThat(livro.getIsbn()).isEqualTo("4568");

	}
	
	@Test
	public void buscaAutorEspecificoNaoCadastrado() {

		Livro livro = this.livroRepository.findByNome("dhh312uien32ui2");
		assertThat(livro).isNull();

	}
	
	@Test
	public void adicionaLivro() {
		Livro novoLivro = new Livro();
		
		novoLivro.setAutor(this.autorRepository.findByNome("helder"));	
		novoLivro.setIsbn("13789");
		novoLivro.setNome("Novo livro teste");
		novoLivro.setQuantidade(123);
		novoLivro.setQuantidadePaginas(1222);

		this.livroRepository.save(novoLivro);
		
		Livro livro = this.livroRepository.findByNome("Novo livro teste");
		assertThat(livro).isNotNull();

	}
	
	@Test
	public void alteraLivro() {
		
		Livro livroAlterado = this.livroRepository.findByNome("Livro teste");
		
		livroAlterado.setNome("NovoNome");
		
		this.livroRepository.save(livroAlterado);
		
		Livro livro = this.livroRepository.findByNome("NovoNome");
		assertThat(livro).isNotNull();

	}
	
	@Test
	public void deletaLivro() {
		
		Livro livro = this.livroRepository.findByNome("Novo livro");
		this.livroRepository.delete(livro);
		
		Livro procuraLivro = this.livroRepository.findByNome("Novo livro");
		assertThat(procuraLivro).isNull();

	}

}
