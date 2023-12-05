package br.edu.iff.webapp.Controller.apirest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/cargo")
public class CargoController {

    @PostMapping(path = "/post")
    @ResponseBody
    public String postCargo() {
        return "Cargo adicionado.";
    }

    @PutMapping(path = "/put")
    @ResponseBody
    public String putCargo() {
        return "Cargo atualizado.";
    }

    @DeleteMapping(path = "/delete")
    @ResponseBody
    public String deleteCargo() {
        return "Cargo deletado.";
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public String getCargo() {
        return "Cargo x";
    }

}
