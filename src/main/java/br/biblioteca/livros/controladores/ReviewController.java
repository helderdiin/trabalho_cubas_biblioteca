package br.biblioteca.livros.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Cliente;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.Review;
import br.biblioteca.livros.repository.LivroRepository;
import br.biblioteca.livros.repository.ReviewRepository;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	@GetMapping("/list")
	public ModelAndView autores() {
		
		Iterable<Review> reviews = reviewRepository.findAll();
		return new ModelAndView("reviews/list", "reviews", reviews);
		
	}
	
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Review review) {
		ModelAndView modelAndView = new ModelAndView("reviews/form");
		
		Iterable<Livro> livros = livroRepository.findAll();
		
		modelAndView.addObject("livros", livros);

		
		return modelAndView;
	} 

	@PostMapping(params = "form")
	public ModelAndView create(@ModelAttribute("review") @Valid Review review, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			ModelAndView modelAndView = new ModelAndView("reviews/form");
			
			Iterable<Livro> livros = livroRepository.findAll();
			
			modelAndView.addObject("livros", livros);

			
			return modelAndView;
		}
		
		review = this.reviewRepository.save(review);
	   	 return new ModelAndView("redirect:/reviews/list");
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView("reviews/form");
		
		Review review = this.reviewRepository.findOne(id);
		
		Iterable<Livro> livros = livroRepository.findAll();
		
		modelAndView.addObject("livros", livros);
		modelAndView.addObject("review", review);

		
		return modelAndView;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		Review review = this.reviewRepository.findOne(id);
		this.reviewRepository.delete(review);
		return new ModelAndView("redirect:/reviews/list");
	}	
	
}
