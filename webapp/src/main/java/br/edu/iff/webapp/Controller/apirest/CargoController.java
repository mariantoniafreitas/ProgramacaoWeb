package br.edu.iff.webapp.Controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Cargo;
import br.edu.iff.webapp.Service.CargoService;

@Controller
@RequestMapping(path = "/cargo")
public class CargoController {
	
	@Autowired
	public CargoService cargoService;

    @PostMapping("")
    @ResponseBody
    public String adicionarCargo(String descricao, double salario, int nivelAcesso) {
    	return cargoService.adicionarCargo(new Cargo(descricao, salario, nivelAcesso));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String putCargo(@PathVariable("id") Long id, double salario, int nivelAcesso) {
    	Cargo cBusca = cargoService.getCargoId(id);
		if(cBusca==null) {			
			return "Cargo não achado";
		}else {
			return cargoService.atualizarCargo(salario, cBusca.getDescricao(), nivelAcesso);
		}
	}

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteCargo(@PathVariable("id") Long id) {
    	Cargo cBusca = cargoService.getCargoId(id);
		if(cBusca==null) {			
			return "Cargo não encontrado";
		}else {			
			return cargoService.deleteCargo(cBusca.getDescricao());
		}
	}

    @GetMapping("/{id}")
    @ResponseBody
    public Cargo getCargo(@PathVariable("id") Long id) {
        return cargoService.getCargoId(id);
    }
    
    @GetMapping("")
	@ResponseBody
	public List<Cargo> listarCargos() throws Exception {
		return cargoService.listaDeCargos();
	}

}
