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
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Service.DiscoService;

@Controller
@RequestMapping(path = "/disco")
public class DiscoController {
	//INSERT INTO DISCO (genero, gravadora, interprete, tempo_duracao, titulo, total_musicas, id, valor) VALUES ('Tal do genero', 'Tal do gravadora', 'Tal do interprete', 280.5, 'Tal do titulo', 10, 1, 25.60);
	//SELECT * FROM DISCO;
	
	@Autowired 
	private DiscoService discoService;

    @PostMapping("")
    public ResponseEntity addDisco(@RequestBody Disco disco) {
        try {
        	//return discoService.addDisco(new Disco(disco.getValor(), disco.getTitulo(), disco.getInterprete(), disco.getGenero(), disco.getGravadora(),disco.getTempoDuracao(), disco.getTotalMusicas()));
        	discoService.addDisco(disco);
        	return ResponseEntity.status(HttpStatus.CREATED).body(disco);
        } catch(Exception ex) {
        	return ResponseEntity.status(500).body(ex.getMessage());
        	//return "Disco (Controller): " + ex.getMessage();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String atualizarDisco(@PathVariable("id") Long id, @RequestBody Disco disco) {
    	Disco dBusca = discoService.getDiscoById(id);

    	if(dBusca==null) {				
			return "Disco não achado";
		}else {
			return discoService.atualizarDisco(dBusca.getTitulo(), disco.getValor(), disco.getGenero(), disco.getInterprete(), disco.getGravadora(), disco.getTotalMusicas(), disco.getTempoDuracao());
		}
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteDisco(@PathVariable("id") Long id) {
    	Disco eBusca = discoService.getDiscoById(id);
		if(eBusca==null) {				
			return "Disco não achado";
		}else {
			return discoService.deletarDiscoTitulo(eBusca.getTitulo());
		}}

    @GetMapping("")
	@ResponseBody
	public List<Disco> listarDiscos() throws Exception{
		return discoService.listarDiscos();
	}
	
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> buscarDisco(@PathVariable("id") Long id) {
        Disco discoBusca = discoService.getDiscoById(id);

        if (discoBusca == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Disco não achado");
        } else {
            return ResponseEntity.ok(discoBusca);
        }
    }

}