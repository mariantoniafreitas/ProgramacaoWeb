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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Service.DiscoService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/api/v1/disco")
public class DiscoController {

	@Autowired
	private DiscoService discoService;

	@PostMapping
	@ResponseBody
	@Operation(summary = "Adicionar um disco em específico")
	public ResponseEntity<String> adicionarDisco(@RequestParam double valor, @RequestParam String titulo,
			@RequestParam String interprete, @RequestParam String genero, @RequestParam String gravadora,
			@RequestParam double tempo_duracao, @RequestParam int total_musicas) {
		try {
			boolean resultado = discoService.adicionarDisco(valor, titulo, interprete, genero, gravadora, tempo_duracao,
					total_musicas);
			return ResponseEntity
					.ok(resultado ? "Disco cadastrado com sucesso." : "Disco já cadastrado com esse titulo.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar disco.");
		}
	}

	@PutMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Atualizar um disco em específico")
	public ResponseEntity<String> atualizarDisco(@PathVariable Long id, @RequestParam double valor,
			@RequestParam String titulo, @RequestParam String interprete, @RequestParam String genero,
			@RequestParam String gravadora, @RequestParam double tempo_duracao, @RequestParam int total_musicas) {
		try {
			String mensagem = discoService.atualizarDisco(id, valor, titulo, interprete, genero, gravadora,
					tempo_duracao, total_musicas);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o disco.");
		}
	}

	@DeleteMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Deletar um disco em específico")
	public ResponseEntity<String> deletarDisco(@PathVariable Long id) {
		try {
			String mensagem = discoService.deletarDisco(id);
			return ResponseEntity.ok(mensagem);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar disco.");
		}
	}

	@GetMapping
	@ResponseBody
	@Operation(summary = "Listar todos os discos")
	public ResponseEntity<List<Disco>> listarDiscos() {
		List<Disco> discos = discoService.listarDiscos();
		return ResponseEntity.ok(discos);
	}

	@GetMapping("/{id}")
	@ResponseBody
	@Operation(summary = "Retornar um disco em específico")
	public ResponseEntity<Disco> buscarDisco(@PathVariable Long id) {
		try {
			Disco disco = discoService.buscarPeloId(id);
			return ResponseEntity.ok(disco);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}