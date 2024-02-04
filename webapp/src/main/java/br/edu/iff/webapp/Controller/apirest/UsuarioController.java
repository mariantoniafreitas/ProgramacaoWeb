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

import br.edu.iff.webapp.Entities.Usuario;
import br.edu.iff.webapp.Service.UsuarioService;

@Controller
@RequestMapping(path = "/usuario")
public class UsuarioController {
	
	@Autowired 
	public UsuarioService usuarioService;

	@PostMapping("")
	@ResponseBody
	public Usuario addUsuario(String login, String senha, String permissao) {
		return usuarioService.salvar(login, senha, permissao);
	}

	@PutMapping("/{id}")
	@ResponseBody
	public String atualizarUsuario(@PathVariable("id") Long id, String senha, String permissao) {
		Usuario uBusca = usuarioService.buscarPorId(id);
		if(uBusca==null) {			
			return "Usuario não achado";
		}else {
			if(senha!=null) {
				usuarioService.atualizarSenha(uBusca, senha);
			}
			if(permissao!=null) {
				usuarioService.atualizarPermissao(uBusca, permissao);
			}
			return "Usuario atuaizado com sucesso";
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseBody
	public String deletarUsuario(@PathVariable("id") Long id){
		Usuario uBusca = usuarioService.buscarPorId(id);
		if(uBusca==null) {			
			return "Usuario não achado";
		}else {	
			usuarioService.deletarPorId(id);
			return "Usuario deletado";
		}
	}

	@GetMapping("")
	@ResponseBody
	public List<Usuario> listarUsuarios(){
		return usuarioService.listarTodos();
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public Usuario buscarUsuario(@PathVariable("id") Long id) {
		return usuarioService.buscarPorId(id);
	}

}