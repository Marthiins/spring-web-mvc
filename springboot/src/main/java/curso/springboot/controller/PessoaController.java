package curso.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping(method = RequestMethod.GET, value = "/cadastropessoa")
	public String inicio() {
		return "cadastro/cadastropessoa";
	}

	/*--------Metodo salvar e voltar na mesma tela------------*/

	@RequestMapping(method = RequestMethod.POST, value = "/salvarpessoa")
	public ModelAndView salvar(Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		/*Depois que salvar vai mostrar a lista de pessoas salva*/
		ModelAndView andview = new ModelAndView("cadastro/cadastropessoa");/* Instanciar o objeto */
		Iterable<Pessoa> pessoasIterable = pessoaRepository.findAll(); /* Carregar do banco de dados a minha lista */
		andview.addObject("pessoas", pessoasIterable);/* Adicionar a lista */
		
		return andview;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listapessoas")
	public ModelAndView pessoas() {
		ModelAndView andview = new ModelAndView("cadastro/cadastropessoa");/* Instanciar o objeto */
		Iterable<Pessoa> pessoasIterable = pessoaRepository.findAll(); /* Carregar do banco de dados a minha lista */
		andview.addObject("pessoas", pessoasIterable);/* Adicionar a lista */
		return andview;
	}
}
