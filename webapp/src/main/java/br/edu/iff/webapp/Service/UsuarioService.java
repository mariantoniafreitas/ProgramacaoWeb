package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Permissao;
import br.edu.iff.webapp.Entities.Usuario;
import br.edu.iff.webapp.Repository.PermissaoRepository;
import br.edu.iff.webapp.Repository.UsuarioRepository;



@Service
public class UsuarioService {
	
	@Autowired 
	private UsuarioRepository UsuarioRepository;
	@Autowired
	private PermissaoRepository PermissaoRepository;


	
	public Usuario salvar(String login, String senha, String permissao) {
		Usuario usuario = new Usuario(login, senha);
		usuario.setSenha(senha);
		Permissao perm = PermissaoRepository.getByAcesso(permissao);
		if(perm == null) {
			perm = new Permissao(permissao);
			PermissaoRepository.save(perm);
		}
		usuario.addPermissao(perm);
		Usuario u = UsuarioRepository.save(usuario);
		return u;
	}
	
	public void atualizarSenha(Usuario usuario, String senha) {
		usuario.setSenha(senha);
		UsuarioRepository.flush();
	}
	
	public void atualizarPermissao(Usuario usuario, String permissao) {
		Permissao perm = PermissaoRepository.getByAcesso(permissao);
		if(perm == null) {
			perm = new Permissao(permissao);
			PermissaoRepository.save(perm);
		}
		usuario.getPermissoes().remove(0);
		usuario.addPermissao(perm);
		UsuarioRepository.save(usuario);
	}
	
	public void deletarPorId(Long id) {
		UsuarioRepository.delete(UsuarioRepository.buscarPorId(id));
	}
	
	public Usuario buscarPorId(Long id) {
		return UsuarioRepository.buscarPorId(id);
	}
	
	public List<Usuario> listarTodos(){
		return UsuarioRepository.listarTodos();
	}

}