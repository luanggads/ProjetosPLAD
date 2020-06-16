package model.dao;

import java.util.List;

import model.Comida;

public interface IComidaDao {

	Integer insert(Comida obj);// inserir prato

	Comida findById(Integer id);// buscar prato pelo id

	List<Comida> findAll();// listar todos os pratos cadastrados

	void update(Comida obj);// atualizar prato

	void deleteById(Integer id);// deletar prato

}
