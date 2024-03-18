package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Funcionario;
import br.edu.iff.webapp.Entities.Usuario;

import br.edu.iff.webapp.Repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository FuncionarioRepository;

	@Autowired
	private UsuarioService UsuarioService;

	public String adicionarFuncionario(String login, String senha, String nome, String email, String cpf, String tel,
			String endereco, String dataNascimento, String cargo, double salario) {
		if (buscarPeloCPF(cpf) == null) {
			Usuario usuario = UsuarioService.adicionarUsuario(login, cargo, 1);
			if (usuario != null) {
				Funcionario f = new Funcionario(nome, email, cpf, tel, endereco, dataNascimento, cargo, salario);
				f.setUsuario(usuario);
				FuncionarioRepository.save(f);
				return "Funcionário registrado com sucesso.";
			}
			return "Login já existe";
		}
		return "Funcionário já cadastrado neste CPF.";
	}

	public String atualizarFuncionario(Long id, String nome, String email, String cpf,
			String tel, String endereco, String dataNascimento, String cargo, double salario) {
		Funcionario f = buscarPeloId(id);
		if (f == null) {
			return "Funcionario não achado";
		} else {
			if (nome != null) {
				f.setNome(nome);
			}
			if (email != null) {
				f.setEmail(email);
			}
			if (cpf != null) {
				f.setCpf(cpf);
			}
			if (tel != null) {
				f.setTel(tel);
			}
			if (endereco != null) {
				f.setEndereco(endereco);
			}
			if (dataNascimento != null) {
				f.setDataNascimento(dataNascimento);
			}
			if (cargo != null) {
				f.setCargo(cargo);
			}
			if (salario > 0) {
				f.setSalario(salario);
			}
		}
		FuncionarioRepository.saveAndFlush(f);
		return "Funcionario atualizado no id " + f.getId();
	}

	public String deletarFuncionario(Long id) {
		Funcionario f = buscarPeloId(id);
		if (f != null) {
			FuncionarioRepository.delete(f);
			return "Funcionario deletado no id " + f.getId();
		} else {
			return "Funcionario não encontrado";
		}
	}

	public List<Funcionario> listarFuncionarios() {
		return FuncionarioRepository.listarFuncionarios();
	}

	public Funcionario buscarPeloCPF(String cpf) {
		return FuncionarioRepository.buscarPeloCPF(cpf);
	}

	public Funcionario buscarPeloId(Long id) {
		return FuncionarioRepository.buscarPeloId(id);
	}
	
	public Funcionario buscarPorLogin(String login) {
        return FuncionarioRepository.buscarPorLogin(login);
    }
	

}