package br.edu.iff.webapp.Controller.apirest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/pedido")
public class PedidoController {

    @PostMapping(path = "/post")
    @ResponseBody
    public String postPedido() {
        return "Pedido adicionado.";
    }

    @PutMapping(path = "/put")
    @ResponseBody
    public String putPedido() {
        return "Pedido atualizado.";
    }

    @DeleteMapping(path = "/delete")
    @ResponseBody
    public String deletePedido() {
        return "Pedido deletado.";
    }

    @GetMapping(path = "/get")
    @ResponseBody
    public String getPedido() {
        return "Pedido x";
    }

}
