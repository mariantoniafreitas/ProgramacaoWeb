package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Cargo;
import br.edu.iff.webapp.Entities.Funcionario;
import br.edu.iff.webapp.Entities.Usuario;
import br.edu.iff.webapp.Repository.CargoRepository;
import br.edu.iff.webapp.Repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository FuncionarioRepository;
	@Autowired
	private CargoRepository CargoRepository;
	@Autowired
	private UsuarioService UsuarioService;
	
	public String addFuncionario(Funcionario funcionario, String descricao) {
		if(FuncionarioRepository.buscarPeloCPF(funcionario.getCpf())!=null) {
			return "Funcionario já cadastrado";
		}else{			
			Cargo cargoBusca = CargoRepository.buscarPelaDescricao(descricao);
			if(cargoBusca == null) {
				return "Cargo não definido";
			}else {
				funcionario.setCargo(cargoBusca);
				String permissao;
				if(cargoBusca.getNivelAcesso()>=1&&cargoBusca.getNivelAcesso()<=5) {
					permissao = "FuncionarioNv"+cargoBusca.getNivelAcesso();
				}else {
					return "Nível de acesso fora dos limites";
				}
				Usuario usuario = UsuarioService.salvar(funcionario.getCpf(), funcionario.getSenha(), permissao);
				funcionario.setUsuario(usuario);
				Funcionario f = FuncionarioRepository.save(funcionario);
				return "Registrado no id "+f.getId();
			}		
		}
	}
	
	public String atualizarFuncionario(String cpf, String nome, String email, String senha, String descricao) {
		Funcionario f = FuncionarioRepository.buscarPeloCPF(cpf);
		if(f==null) {
			return "Funcionario não achado";
		}else {		
			if(nome!=null) {
				f.setNome(nome);
			}
			if(email!=null) {				
				f.setEmail(email);
			}
			if(senha!=null) {				
				f.setSenha(senha);
				UsuarioService.atualizarSenha(f.getUsuario(), senha);
			}
			if(descricao!=null) {
				Cargo cargoBusca = CargoRepository.buscarPelaDescricao(descricao);
				if(cargoBusca == null) {
					return "Cargo não existe";
				}else {
					f.setCargo(cargoBusca);
					f.getUsuario().getPermissoes().get(0).setAcesso("FuncionarioNv"+cargoBusca.getNivelAcesso());
				}
			}
			FuncionarioRepository.flush();
			return "Atualizado no id "+f.getId();
		}
	}
	
	public String deletarFuncionarioCPF(String cpf) {
		Funcionario f = FuncionarioRepository.buscarPeloCPF(cpf);
		if(f!=null) {	
			FuncionarioRepository.delete(f);
			return "Funcionario deletado no id "+f.getId();
		}else {
			return "Funcionario não encontrado";
		}
	}
	
	public List<Funcionario> listarFuncionarios(){
		return FuncionarioRepository.findAll();
	}
	
	public Funcionario buscarFuncionarioCPF(String cpf) {
		return FuncionarioRepository.buscarPeloCPF(cpf);
	}
	
	public Funcionario getFuncionarioById(Long id) {
		return FuncionarioRepository.BuscarPeloId(id);
	}
	
//	public void garantirFuncionarioADM() {
//		if((FuncionarioRepository.maiorNivelAcesso()==null)||(Integer.parseInt(FuncionarioRepository.maiorNivelAcesso())>1)) {
//			Cargo cargo = CargoRepository.buscaDescricao("ADM");
//			if(cargo==null) {
//				CargoRepository.save(new Cargo("ADM",100,1));
//				cargo = CargoRepository.buscaDescricao("ADM");
//			}else if(cargo.getNivelAcesso()>1) {
//				cargo.setNivelAcesso(1);
//			}
//			Funcionario func = FuncionarioRepository.buscarPeloCPF("000.000.000-00");
//			if(func==null) {
//				func = new Funcionario("ADM","Administrador@adm","000.000.000-00","senha","9999999","rua x","(00) 00000-0000");
//				func.setCargo(cargo);
//				Usuario usuario = UsuarioService.salvar(func.getNome(), func.getSenha(), "FuncionarioNv1");
//				func.setUsuario(usuario);
//				FuncionarioRepository.save(func);
//			}else if(func.getCargo().getNivelAcesso()>1){
//				func.setCargo(cargo);
//			}
//			CargoRepository.flush();
//			FuncionarioRepository.flush();
//		}
//	}
}