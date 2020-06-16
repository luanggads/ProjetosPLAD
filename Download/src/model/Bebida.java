package model;

public class Bebida {

	private int idBebida;
	private String tipoBebida;
	private String especificacoes;
	

	public int getIdBebida() {
		return idBebida;
	}

	public void setIdBebida(int idBebida) {
		this.idBebida = idBebida;
	}

	public String getTipoBebida() {
		return tipoBebida;
	}

	public void setTipoBebida(String tipoBebida) {
		this.tipoBebida = tipoBebida;
	}

	public String getEspecificacoes() {
		return especificacoes;
	}

	public void setEspecificacoes(String especificacoes) {
		this.especificacoes = especificacoes;
	}

	@Override
	public String toString() {
		return "Bebida [idBebida=" + idBebida + ", tipoBebida=" + tipoBebida + ", especificacoes=" + especificacoes
				+ "]";
	}

	

	

}
