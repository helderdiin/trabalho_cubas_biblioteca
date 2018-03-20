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

import br.biblioteca.livros.beans.Cliente;
import br.biblioteca.livros.repository.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClienteTests {

	@Autowired
	ClienteRepository clienteRepository;

	@Test
	public void buscaClientesCadastrados() {

		Page<Cliente> clientes = this.clienteRepository.findAll(new PageRequest(0, 10));
		assertThat(clientes.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaClienteEspecificoCadastrado() {

		Cliente cliente = this.clienteRepository.findByNome("miah");
		assertThat(cliente).isNotNull();
		assertThat(cliente.getId()).isEqualTo(2);

	}
	
	@Test
	public void buscaClienteEspecificoNaoCadastrado() {

		Cliente cliente = this.clienteRepository.findByNome("jdjs82ne78whe");
		assertThat(cliente).isNull();

	}
	
	@Test
	public void adicionaCliente() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Cliente novoCliente = new Cliente();
		
		novoCliente.setNome("NovoCliente");
		novoCliente.setDataNascimento(format.parse("1999-02-02"));
		novoCliente.setEndereco("Endereco do cliente");
		novoCliente.setObservacao("Obs do cliente");
		

		this.clienteRepository.save(novoCliente);
		
		Cliente cliente = this.clienteRepository.findByNome("NovoCliente");
		assertThat(cliente).isNotNull();

	}
	
	@Test
	public void alteraCliente() {
		
		Cliente clienteAlterado = this.clienteRepository.findByNome("kassia");
		
		clienteAlterado.setNome("NovoNome");
		
		this.clienteRepository.save(clienteAlterado);
		
		Cliente cliente = this.clienteRepository.findByNome("NovoNome");
		assertThat(cliente).isNotNull();

	}
	
	@Test
	public void deletaCliente() {
		
		Cliente cliente = this.clienteRepository.findByNome("julio");
		this.clienteRepository.delete(cliente);
		
		Cliente procuraCliente = this.clienteRepository.findByNome("julio");
		assertThat(procuraCliente).isNull();

	}

}
