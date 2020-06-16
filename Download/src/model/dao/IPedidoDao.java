package model.dao;

import java.util.List;

import model.Pedido;

public interface IPedidoDao {

	Integer insert(Pedido obj);// inserir pedido

	Pedido findById(Integer id);// buscar pedido pelo id

	List<Pedido> findAll();// listar todos os pedidos

	void update(Pedido obj);// atualizar pedido

	void deleteById(Integer id);// deletar pedido

}
