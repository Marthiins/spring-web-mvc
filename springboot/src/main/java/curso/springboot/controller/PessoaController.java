package curso.springboot.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import curso.springboot.model.Pessoa;
import curso.springboot.repository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;/* Injeção de dependencia pessoa da classe repository */

	/*--------Inicio da Tela------------*/

	@RequestMapping(method = RequestMethod.GET, value="/cadastropessoa")
	public ModelAndView inicio() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoaobj", new Pessoa());/*Criar um objeto vazio */
		return andView;
	}

	/*--------Metodo salvar e voltar na mesma tela------------*/

	@RequestMapping(method=RequestMethod.POST, value="**/salvarpessoa")
	public ModelAndView Salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		Iterable<Pessoa> pessoasIt = pessoaRepository.findAll();
		andView.addObject("pessoas", pessoasIt);
		andView.addObject("pessoaobj", new Pessoa());
		return andView;
	}

	/*--------Metodo Listar------------*/
	
	@RequestMapping(method = RequestMethod.GET, value="/listapessoas")
	public ModelAndView pessoas() {
		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");/* Instanciar o objeto */
		Iterable<Pessoa> pessoasIterable = pessoaRepository.findAll(); /* Carregar do banco de dados a minha lista */
		andView.addObject("pessoas", pessoasIterable);/* Adicionar a lista */
		andView.addObject("pessoaobj", new Pessoa());/*Criar um objeto vazio */
		return andView;
	}
	/*--------Metodo Editar------------*/

	@GetMapping("/editarpessoa/{idpessoa}")
	public ModelAndView editar(@PathVariable("idpessoa") Long idpessoa) {

		Optional<Pessoa> pessoa = pessoaRepository.findById(idpessoa);/* Retornar passando o objeto pessoa */

		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");
		andView.addObject("pessoaobj", pessoa.get());/* Adicionar pessoa ao modelo */
		return andView; /* Injetar os dados na tela */
	}
	
	/*--------Metodo Remover------------*/

	@GetMapping("/removerpessoa/{idpessoa}")
	public ModelAndView excluir(@PathVariable("idpessoa") Long idpessoa) {

		pessoaRepository.deleteById(idpessoa);/* Deletar */

		ModelAndView andView = new ModelAndView("cadastro/cadastropessoa");/*Vai voltar a tela para o cadastro*/
		andView.addObject("pessoas", pessoaRepository.findAll());/* Carregar todas as pessoas depois de carregar a tela */
		andView.addObject("pessoaobj", new Pessoa()); /*Retornar um objeto vazio*/
		
		return andView; /* Injetar os dados na tela */
	}
}
