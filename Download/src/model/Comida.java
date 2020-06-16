package model;

public class Comida {

	private int idComida;
	private String nomePrato;
	private String obsevacoes;

	public int getIdComida() {
		return idComida;
	}

	public void setIdComida(int idComida) {
		this.idComida = idComida;
	}

	public String getNomePrato() {
		return nomePrato;
	}

	public void setNomePrato(String nomePrato) {
		this.nomePrato = nomePrato;
	}

	public String getObsevacoes() {
		return obsevacoes;
	}

	public void setObsevacoes(String obsevacoes) {
		this.obsevacoes = obsevacoes;
	}

	@Override
	public String toString() {
		return "Comida [idComida=" + idComida + ", nomePrato=" + nomePrato + ", obsevacoes=" + obsevacoes + "]";
	}

}
