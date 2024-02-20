package br.edu.iff.webapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.webapp.Entities.Disco;
import br.edu.iff.webapp.Repository.DiscoRepository;

@Service
public class DiscoService {

	@Autowired
	private DiscoRepository DiscoRepository;

	public boolean adicionarDisco(double valor, String titulo, String interprete, String genero, String gravadora,
			double tempo_duracao, int total_musicas) {
		Disco d = DiscoRepository.buscarPeloTitulo(titulo);
		if (d != null) {
			return false;
		}
		Disco novoDisco = new Disco(valor, titulo, interprete, genero, gravadora, tempo_duracao, total_musicas);
		DiscoRepository.saveAndFlush(novoDisco);
		return true;
	}

	public String atualizarDisco(Long id, double valor, String titulo, String interprete, String genero,
			String gravadora, double tempo_duracao, int total_musicas) {
		Disco d = buscarPeloId(id);
		if (d == null) {
			return "Disco não achado";
		} else {
			if (valor >= 0) {
				d.setValor(valor);
			}
			if (total_musicas >= 0) {
				d.setTotalMusicas(total_musicas);
			}
			if (genero != null) {
				d.setGenero(genero);
			}
			if (interprete != null) {
				d.setInterprete(interprete);
			}
			if (gravadora != null) {
				d.setGravadora(gravadora);
			}
			if (tempo_duracao > 0) {
				d.setTempoDuracao(tempo_duracao);
			}
			DiscoRepository.saveAndFlush(d);
			return "Disco atualizado no id " + d.getId();
		}
	}

	public String deletarDisco(Long id) {
		Disco d = buscarPeloId(id);
		if (d != null) {
			DiscoRepository.delete(d);
			return "Disco deletado no id " + d.getId();
		} else {
			return "Disco não encontrado";
		}
	}

	public List<Disco> listarDiscos() {
		return DiscoRepository.listarDiscos();
	}

	public Disco buscarPeloId(Long id) {
		return DiscoRepository.buscarPeloId(id);
	}

}
