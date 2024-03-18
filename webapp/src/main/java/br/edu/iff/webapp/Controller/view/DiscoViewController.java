package br.edu.iff.webapp.Controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Service.DiscoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("disco")
public class DiscoViewController {

	@Autowired
	private DiscoService discoService;

	@GetMapping("")
	public String page(Model model) throws Exception {
		return "redirect:/disco/listarDiscos";
	}

	@GetMapping("/addForm")
	public String addDiscoForm(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("disco_add", new Disco());
		String resposta = request.getParameter("resposta");
		if (resposta != null) {
			model.addAttribute("respostaAdd", URLDecoder.decode(resposta, "UTF-8"));
		}
		return "CRUD_Disco";
	}

	@PostMapping("/add")
	public String addDisco(@Valid @ModelAttribute Disco disco, BindingResult resultado, Model model) {
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "";
		} else {
			boolean saida = discoService.adicionarDisco(disco.getValor(), disco.getTitulo(), disco.getInterprete(), disco.getGenero(), disco.getGravadora(), disco.getTempoDuracao(), disco.getTotalMusicas());
			String resposta = saida ? "Disco adicionado com sucesso." : "Disco já cadastrado com esse título.";
			try {
				return "redirect:/disco/addForm?resposta=" + URLEncoder.encode(resposta, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return "";
			}
		}
	}

	@GetMapping("/listarDiscos")
	public String listarDiscos(Model model, HttpServletRequest request) throws Exception {
		String titulo = request.getParameter("titulo");
		if (titulo == null) {
			model.addAttribute("disco_lista", discoService.listarDiscos());
		} else {
			Disco disco = discoService.buscarPeloTitulo(URLDecoder.decode(titulo, "UTF-8"));
			if (disco == null) {
				disco = new Disco();
			}
			model.addAttribute("disco_lista", disco);
		}
		return "CRUD_Disco";
	}

	@PostMapping("/buscaTitulo")
	public String buscarDiscos(String titulo) throws Exception {
		return "redirect:/disco/listarDiscos?titulo=" + URLEncoder.encode(titulo, "UTF-8");
	}

	@GetMapping("/editar")
	public String formEditar(@RequestParam Long id, Model model) throws Exception {
		model.addAttribute("livro_edit", discoService.buscarPeloId(id));
		return "CRUD_Disco";
	}

	@PostMapping("/atualizar")
	public String atualizarDisco(@Valid @ModelAttribute Disco disco, BindingResult resultado, Model model) {
		String interprete = disco.getInterprete();
		String titulo = disco.getTitulo();
		String genero = disco.getGenero();
		String gravadora = disco.getGravadora();
		double tempoDuracao = disco.getTempoDuracao();
		int qtdMusicas = disco.getTotalMusicas();
		double valor = disco.getValor();
		if (resultado.hasErrors()) {
			model.addAttribute("mensagemErro", resultado.getAllErrors());
			return "";
		} else {
			discoService.atualizarDisco(discoService.buscarPeloTitulo(titulo).getId(), valor, titulo, interprete, genero, gravadora, tempoDuracao, qtdMusicas);
			return "redirect:/disco/listarDiscos";
		}
	}

	@GetMapping("/deletaPorTitulo")
	public String deletarDiscoTitulo(String titulo) throws Exception {
		discoService.deletarDisco(discoService.buscarPeloTitulo(titulo).getId());
		return "redirect:/disco/listarDiscos";
	}

}
