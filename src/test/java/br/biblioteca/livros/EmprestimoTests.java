package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.repository.ClienteRepository;
import br.biblioteca.livros.repository.EmprestimoRepository;
import br.biblioteca.livros.repository.LivroRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmprestimoTests {

	@Autowired
	EmprestimoRepository emprestimoRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	ClienteRepository clienteRepository;

	@Test
	public void buscaEmprestimosCadastrados() {

		Page<Emprestimo> emprestimos = this.emprestimoRepository.findAll(new PageRequest(0, 10));
		assertThat(emprestimos.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaEmprestimoEspecificoCadastrado() {

		Emprestimo emprestimo = this.emprestimoRepository.findById(1L);
		assertThat(emprestimo).isNotNull();
		assertThat(emprestimo.getDataEmprestimo()).isEqualTo("2012-02-10");

	}
	
	@Test
	public void buscaAutorEspecificoNaoCadastrado() {

		Emprestimo emprestimo = this.emprestimoRepository.findById(888L);
		assertThat(emprestimo).isNull();

	}
	
	@Test
	public void adicionaEmprestimo() throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Emprestimo novoEmprestimo = new Emprestimo();
		
		novoEmprestimo.setDataDevolucao(format.parse("2012-02-02"));
		novoEmprestimo.setDataEmprestimo(format.parse("2012-02-01"));
		novoEmprestimo.setLivro(this.livroRepository.findByNome("Livro teste"));
		novoEmprestimo.setCliente(this.clienteRepository.findByNome("miah"));

		this.emprestimoRepository.save(novoEmprestimo);
		
		Emprestimo emprestimo = this.emprestimoRepository.findById(novoEmprestimo.getId());
		assertThat(emprestimo).isNotNull();

	}
	
	@Test
	public void alteraEmprestimo() throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Emprestimo emprestimoAlterado = this.emprestimoRepository.findById(1L);
		
		emprestimoAlterado.setDataDevolucao(format.parse("2012-02-05"));
		
		this.emprestimoRepository.save(emprestimoAlterado);
		
		Emprestimo emprestimo = this.emprestimoRepository.findById(1L);
		assertThat(emprestimo).isNotNull();

	}
	
	@Test
	public void deletaEmprestimo() {
		
		Emprestimo emprestimo = this.emprestimoRepository.findById(2L);
		this.emprestimoRepository.delete(emprestimo);
		
		Emprestimo procuraEmprestimo = this.emprestimoRepository.findById(2L);
		assertThat(procuraEmprestimo).isNull();

	}

}
