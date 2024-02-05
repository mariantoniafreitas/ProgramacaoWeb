package br.edu.iff.webapp.Controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Cargo;
import br.edu.iff.webapp.Service.CargoService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/cargo")
public class CargoController {
	
	@Autowired
	public CargoService cargoService;

	@PostMapping("")
	@ResponseBody
	@Operation(description = "Adicionar um novo cargo")
	public String adicionarCargo(@RequestBody Cargo cargoRequest) {
	    // Lógica para adicionar o cargo usando os dados de cargoRequest
	    return cargoService.adicionarCargo(new Cargo(cargoRequest.getDescricao(), cargoRequest.getSalario(), cargoRequest.getNivelAcesso()));
	}


	@PutMapping("/{id}")
	@ResponseBody
	@Operation(description = "Atualizar um cargo")
	public String putCargo(@PathVariable("id") Long id, @RequestBody Cargo cargoRequest) {
	    Cargo cBusca = cargoService.getCargoId(id);
	    if (cBusca == null) {            
	        return "Cargo não achado";
	    } else {
	    	return cargoService.atualizarCargo(cargoRequest.getSalario(), cargoRequest.getDescricao(), cargoRequest.getNivelAcesso());
	    }
	}


    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(description = "Apagar um cargo")
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
    @Operation(description = "Informacoes de um cargo")
    public ResponseEntity<?> getCargo(@PathVariable("id") Long id) {
        Cargo cargo = cargoService.getCargoId(id);

        if (cargo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cargo não encontrado");
        } else {
            return ResponseEntity.ok(cargo);
        }
    }

    
    @GetMapping("")
	@ResponseBody
	@Operation(description = "Listar todos os cargoS")
	public List<Cargo> listarCargos() throws Exception {
		return cargoService.listaDeCargos();
	}

}
