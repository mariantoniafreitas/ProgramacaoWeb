package br.edu.iff.webapp.Controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "")
public class MainControllerView {
	
	@GetMapping("/")
	public String cruds() {
		return "layout-base";
	}
}
