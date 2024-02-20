package br.edu.iff.webapp.Controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Service.DiscoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("disco")
public class DiscoViewController {

	@Autowired
	private DiscoService discoService;

	@GetMapping
	public String listarDiscos(Model model) {
		List<Disco> discos = discoService.listarDiscos();
		model.addAttribute("discos", discos);
		return "listarDiscos";
	}

	@GetMapping("/adicionar")
	public String exibirFormularioAdicionar() {
		return "adicionarDisco";
	}

	@PostMapping("/adicionar")
	public String adicionarDisco(@Valid @RequestParam double valor, @Valid @RequestParam String titulo,
			@Valid @RequestParam String interprete, @Valid @RequestParam String genero,
			@Valid @RequestParam String gravadora, @Valid @RequestParam double tempo_duracao,
			@Valid @RequestParam int total_musicas, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "adicionarDisco";
		}

		boolean resultado = discoService.adicionarDisco(valor, titulo, interprete, genero, gravadora, tempo_duracao,
				total_musicas);
		model.addAttribute("resultado", resultado);
		return "redirect:/disco/adicionar";
	}

	@GetMapping("/editar/{id}")
	public String exibirFormularioEditar(@PathVariable Long id, Model model) {
		Disco disco = discoService.buscarPeloId(id);
		model.addAttribute("disco", disco);
		return "editarDisco";
	}

	@PostMapping("/editar/{id}")
	public String editarDisco(@PathVariable Long id, @Valid @RequestParam double valor,
			@Valid @RequestParam String titulo, @Valid @RequestParam String interprete,
			@Valid @RequestParam String genero, @Valid @RequestParam String gravadora,
			@Valid @RequestParam double tempo_duracao, @Valid @RequestParam int total_musicas, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "editarDisco";
		}

		String mensagem = discoService.atualizarDisco(id, valor, titulo, interprete, genero, gravadora, tempo_duracao,
				total_musicas);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/disco/editar/" + id;
	}

	@GetMapping("/excluir/{id}")
	public String excluirDisco(@PathVariable Long id, Model model) {
		String mensagem = discoService.deletarDisco(id);
		model.addAttribute("mensagem", mensagem);
		return "redirect:/disco/excluir/" + id;
	}

}
