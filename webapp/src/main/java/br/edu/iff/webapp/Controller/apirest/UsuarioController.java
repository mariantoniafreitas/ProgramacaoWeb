package br.edu.iff.webapp.Controller.apirest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController {

    @PostMapping(path = "/post")
    @ResponseBody
    public String postUsuario() {
        return "Usuario adicionado.";
    }

    @PutMapping(path = "/put")
    @ResponseBody
    public String putUsuario() {
        return "Usuario atualizado.";
    }

    @DeleteMapping(path = "/delete")
    @ResponseBody
    public String deleteUsuario() {
        return "Usuario deletado.";
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public String getUsuario() {
        return "Usuario x";
    }

}