package br.edu.iff.webapp.Controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired 
	private DiscoService discoService;

    @PostMapping("")
    @ResponseBody
    public String addDisco(@RequestBody Disco disco) {
        return discoService.addDisco(new Disco(disco.getValor(), disco.getTitulo(), disco.getInterprete(), disco.getGenero(), disco.getGravadora(),disco.getTempoDuracao(), disco.getTotalMusicas()));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String atualizarDisco(@PathVariable("id") Long id,double valor, String titulo, String interprete, String genero, String gravadora, double tempoDuracao, int totalMusicas) {
    	Disco eBusca = discoService.getDiscoById(id);
		if(eBusca==null) {				
			return "Disco não achado";
		}else {
			return discoService.atualizarDisco(eBusca.getTitulo(), valor, genero, interprete, gravadora, totalMusicas, tempoDuracao);
		}
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String deleteDisco(@PathVariable("id") Long id) {
    	Disco eBusca = discoService.getDiscoById(id);
		if(eBusca==null) {				
			return "E-Book não achado";
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
	public Disco buscarDiscos(@PathVariable("id") Long id) {
		return discoService.getDiscoById(id);
	}

}
