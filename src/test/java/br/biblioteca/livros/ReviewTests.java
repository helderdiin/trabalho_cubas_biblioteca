package br.biblioteca.livros;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import br.biblioteca.livros.beans.Review;
import br.biblioteca.livros.repository.LivroRepository;
import br.biblioteca.livros.repository.ReviewRepository;
import br.biblioteca.livros.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewTests {

	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	UsuarioRepository usuarioRepository;

	@Test
	public void buscaEmprestimosCadastrados() {

		Page<Review> reviews = this.reviewRepository.findAll(new PageRequest(0, 10));
		assertThat(reviews.getTotalElements()).isGreaterThan(1L);

	}
	
	@Test
	public void buscaEmprestimoEspecificoCadastrado() {

		Review review = this.reviewRepository.findByComentario("Teste ruim de nota");
		assertThat(review).isNotNull();
		assertThat(review.getAvaliacao()).isEqualTo(1);

	}
	
	@Test
	public void buscaAutorEspecificoNaoCadastrado() {

		Review review = this.reviewRepository.findByComentario("djiwo789endw");
		assertThat(review).isNull();

	}
	
	@Test
	public void adicionaReview() {
		Review novoReview = new Review();
		
		novoReview.setAvaliacao(5);
		novoReview.setComentario("Teste de comentario");
		novoReview.setLivro(this.livroRepository.findByNome("Livro novo"));
		novoReview.setUsuario(this.usuarioRepository.findByEmail("helder@helder.com"));

		this.reviewRepository.save(novoReview);
		
		Review review = this.reviewRepository.findByComentario("Teste de comentario");
		assertThat(review).isNotNull();

	}
	
	@Test
	public void alteraReview() {
		
		Review reviewAlterado = this.reviewRepository.findByComentario("Teste de av");
		
		reviewAlterado.setComentario("NovoNome");
		
		this.reviewRepository.save(reviewAlterado);
		
		Review review = this.reviewRepository.findByComentario("NovoNome");
		assertThat(review).isNotNull();

	}
	
	@Test
	public void deletaReview() {
		
		Review review = this.reviewRepository.findByComentario("Avaliação");
		this.reviewRepository.delete(review);
		
		Review procuraReview = this.reviewRepository.findByComentario("Avaliação");
		assertThat(procuraReview).isNull();

	}

}
