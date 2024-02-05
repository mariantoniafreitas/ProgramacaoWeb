package br.edu.iff.webapp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "home")
public class MainController {

    @GetMapping(path = "/")
    @ResponseBody
    public String home() {
        return "Esta é a Home. 1234567 ";

    }
}