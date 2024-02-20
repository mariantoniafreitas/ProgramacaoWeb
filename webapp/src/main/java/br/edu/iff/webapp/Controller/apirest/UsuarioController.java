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

import br.edu.iff.webapp.Entities.Usuario;
import br.edu.iff.webapp.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

@Controller
@RequestMapping(path = "/api/v1/usuario")
public class UsuarioController {
	
	@Autowired 
	public UsuarioService usuarioService;

	@PostMapping("")
	@ResponseBody
	@Operation(description = "Adicionar um usuario")
	public ResponseEntity<String> adicionarUsuario(@RequestParam String login, @RequestParam String senha,
            @RequestParam int permissao) {
		 try {
	            Usuario resultado = usuarioService.adicionarUsuario(login, senha, permissao);
	            return ResponseEntity.ok(
	                    resultado != null ? "Usuário cadastrado com sucesso." : "Usuário já cadastrado com esse login.");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar usuário.");
	        }}

    @PutMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Atualizar um usuário específico")
    public ResponseEntity<String> atualizarUsuario(@PathVariable("id") Long id, @RequestParam String login,
            @RequestParam String senha, @RequestParam int permissao) {
        try {
            String mensagem = usuarioService.atualizarUsuario(id, login, senha, permissao);
            return ResponseEntity.ok(mensagem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar usuário.");
        }
    }
	
    @DeleteMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Deletar um usuário específico")
    public ResponseEntity<String> deletarUsuario(@PathVariable("id") Long id) {
        try {
            String mensagem = usuarioService.deletarUsuario(id);
            return ResponseEntity.ok(mensagem);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar usuário.");
        }
    }
    
    @GetMapping
    @ResponseBody
    @Operation(summary = "Listar todos os usuários")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }
	
	@GetMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Retornar um usuário específico")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable("id") Long id) {
        try {
            Usuario usuario = usuarioService.buscarPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}