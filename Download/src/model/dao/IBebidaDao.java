package model.dao;

import java.util.List;

import model.Bebida;

public interface IBebidaDao {

	Integer insert(Bebida obj);// inserir bebida

	Bebida findById(Integer id);// buscar bebida pelo id

	List<Bebida> findAll();// listar todos as bebidas cadastrados

	void update(Bebida obj);// atualizar a bebida

	void deleteById(Integer id);// deletar a bebida

}
