package model;

import java.util.Date;

public class Pedido {

	private int idPedido;
	private Cliente cliente;
	private Comida comida;
	private Bebida bebida;
	private Date data;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Comida getComida() {
		return comida;
	}

	public void setComida(Comida comida) {
		this.comida = comida;
	}

	public Bebida getBebida() {
		return bebida;
	}

	public void setBebida(Bebida bebida) {
		this.bebida = bebida;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", cliente=" + cliente + ", comida=" + comida + ", bebida=" + bebida
				+ ", data=" + data + "]";
	}

}
