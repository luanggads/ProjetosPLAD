package model.dao;

import java.util.List;

import model.Cliente;

public interface IClienteDao {

	Integer insert(Cliente obj); // criar ou inserir

	Cliente findById(Integer id); // buscar de acordo com o id

	List<Cliente> findAll();// listar todos os clientes

	void update(Cliente obj);// Atualizar o cliente

	void deleteById(Integer id);// deletar um cliente pelo id

}
