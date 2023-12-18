package br.edu.iff.webapp.Controller.apirest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/disco")
public class DiscoController {

    @PostMapping(path = "/post")
    @ResponseBody
    public String postDisco() {
        return "Disco adicionado.";
    }

    @PutMapping(path = "/put")
    @ResponseBody
    public String putDisco() {
        return "Disco atualizado.";
    }

    @DeleteMapping(path = "/delete")
    @ResponseBody
    public String deleteDisco() {
        return "Disco deletado.";
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public String getDisco() {
        return "Disco x";
    }

}
