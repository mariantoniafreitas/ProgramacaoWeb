package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Usuario;
import br.edu.iff.webapp.Repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository UsuarioRepository;

	public Usuario adicionarUsuario(String login, String senha, int permissao) {
		Usuario buscaU = UsuarioRepository.buscarPeloLogin(login);
		if (buscaU == null) {
			Usuario usuario = new Usuario(login, senha, permissao);
			Usuario u = UsuarioRepository.save(usuario);
			return u;
		}
		return null;
	}

	public String atualizarUsuario(Long id, String login, String senha, int permissao) {
		Usuario u = buscarPorId(id);
		if (u != null) {
			if (u.getLogin().equals(login)) {
				if (senha != null) {
					u.setSenha(senha);
					UsuarioRepository.save(u);
				}
				if (permissao > -1) {
					u.setPermissao(permissao);
					UsuarioRepository.save(u);
				}
				return "Usuário atualizado.";
			}
			return "O Id inserido não corresponde ao login.";
		}
		return "Usuário não encontrado.";
	}

	public String deletarUsuario(Long id) {
		Usuario u = buscarPorId(id);
		if (u != null) {
			UsuarioRepository.delete(u);
			return "Usuário deletado.";
		}
		return "Usuário não deletado.";
	}

	public Usuario buscarPorId(Long id) {
		return UsuarioRepository.buscarPorId(id);
	}

	public List<Usuario> listarUsuarios() {
		return UsuarioRepository.listarUsuarios();
	}

}