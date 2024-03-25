package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Cargo;
import br.edu.iff.webapp.Repository.CargoRepository;

@Service
public class CargoService {

	@Autowired
	private CargoRepository CargoRepository;
	
	public Cargo buscaDescricao(String descricao) {
		return CargoRepository.buscarPelaDescricao(descricao);
	}
	
	public String adicionarCargo (Cargo cargo) {
		if(CargoRepository.buscarPelaDescricao(cargo.getDescricao())!=null) {
			return "Este cargo já existe.";
		} else {
			Cargo novoCargo = CargoRepository.save(cargo);
			return "Novo cargo registrado. ID: " + novoCargo.getId();
		}
	}
	
	public String atualizarCargo(double salario, String descricao, int nivelAcesso) {
		Cargo novoCargo = CargoRepository.buscarPelaDescricao(descricao);
		if(novoCargo == null) {
			return "O cargo inserido não foi cadastrado ou não existe.";
		} else {
			if(salario > 0) {
				novoCargo.setSalario(salario);
			}
			if(nivelAcesso >= 0) {
				novoCargo.setNivelAcesso(nivelAcesso);
			}
			CargoRepository.flush();
			return "Cargo Atualizado";
		}
	}
	
	public String deleteCargo(String descricao) {
		Cargo novoCargo = CargoRepository.buscarPelaDescricao(descricao);
		if(novoCargo != null) {
			if(CargoRepository.BuscarFuncionariosPeloCargo(descricao)==0) {
				CargoRepository.delete(novoCargo);
				return "Cargo excluído com sucesso.";
			} else {
				return "O cargo não pode ser excluído pois ainda possui funcionários."; 
			}
		} else {
			return "O cargo inserido não foi cadastrado ou não existe.";
		}
	}
	
	public List<Cargo> listaDeCargos() {
		return CargoRepository.findAll();
	}
	
	public Cargo getCargoId(Long id) {
		return CargoRepository.BuscarPeloId(id);
	}
}