package br.edu.iff.webapp.Controller.apirest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/cliente")
public class ClienteController {

    @PostMapping(path = "/post")
    @ResponseBody
    public String postCliente() {
        return "cliente adicionado.";
    }

    @PutMapping(path = "/put")
    @ResponseBody
    public String putCliente() {
        return "cliente atualizado.";
    }

    @DeleteMapping(path = "/delete")
    @ResponseBody
    public String deleteCliente() {
        return "cliente deletado.";
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public String getCliente() {
        return "cliente x";
    }

}
