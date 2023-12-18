package br.edu.iff.webapp.Controller.apirest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/funcionario")
public class FuncionarioController {

    @PostMapping(path = "/post")
    @ResponseBody
    public String postFuncionario() {
        return "Funcionario adicionado.";
    }

    @PutMapping(path = "/put")
    @ResponseBody
    public String putFuncionario() {
        return "Funcionario atualizado.";
    }

    @DeleteMapping(path = "/delete")
    @ResponseBody
    public String deleteFuncionario() {
        return "Funcionario deletado.";
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public String getFuncionario() {
        return "Funcionario x";
    }

}
