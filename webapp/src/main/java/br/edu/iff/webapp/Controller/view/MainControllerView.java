package br.edu.iff.webapp.Controller.view;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.webapp.Entities.Cliente;
import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Service.ClienteService;
import br.edu.iff.webapp.Service.DiscoService;
import br.edu.iff.webapp.Service.FuncionarioService;
import jakarta.servlet.http.HttpServletRequest;


@Controller
@RequestMapping(path = "")
public class MainControllerView {

    @Autowired
    public ClienteService clienteService;

    @Autowired
    public FuncionarioService funcionarioService;

    @Autowired
    private DiscoService discoService;

    @GetMapping("")
    public String index(Model model, HttpServletRequest request)
            throws UnsupportedEncodingException {
        String produto = request.getParameter("produto");
        if (produto == null) {
            model.addAttribute("disco_lista", discoService.listarDiscos());
        } else {
            Disco disco = discoService.buscarPeloTitulo(URLDecoder.decode(produto, "UTF-8"));
            model.addAttribute("disco", disco);
        }
        return "index";
    }

    @PostMapping("/buscarProduto")
    public String buscarProduto(String produto) throws UnsupportedEncodingException {
        return "redirect:/?produto=" + URLEncoder.encode(produto, "UTF-8");
    }
}