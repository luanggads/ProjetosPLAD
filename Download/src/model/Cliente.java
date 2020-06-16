package model;

public class Cliente extends Pessoa {

	private int idCliente;

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente= " + idCliente + " Nome " + getNome() + " ]";
	}

}
