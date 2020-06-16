package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.Cliente;

public class ClienteDaoJDBC implements IClienteDao {
	Connection conn = null;

	public ClienteDaoJDBC() {
		this.conn = DB.getConnection();
	}

	@Override
	public Integer insert(Cliente obj) {

		Integer idCliente = null;

		PreparedStatement st = null;
		try {
			String sql = "INSERT INTO cliente (nome) VALUES (?)";

			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			
			int linhas = st.executeUpdate();

			if (linhas > 0) {
				ResultSet rs = st.getGeneratedKeys(); // retorna o id criado
				while (rs.next()) {
					idCliente = rs.getInt(1);
				}
				
				DB.closeResultSet(rs);
			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());// envia uma exceção

		} finally {

			DB.closeStatement(st);
		}
		return idCliente;

	}

	@Override
	public Cliente findById(Integer id) {

		PreparedStatement st = null;

		ResultSet rs = null;

		Cliente cliente = new Cliente();

		try {
			String sql = "SELECT idCliente, nome  FROM cliente WHERE idCliente = ?";
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				int id_cliente = rs.getInt("idCliente");
				String nome = rs.getString("nome");

				cliente.setIdCliente(id_cliente);
				cliente.setNome(nome);

			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return cliente;
	}

	@Override
	public List<Cliente> findAll() {

		List<Cliente> clientes = new ArrayList<Cliente>();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT idCliente, nome  FROM cliente";
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next()) {
				int id_cliente = rs.getInt("idCliente");
				String nome = rs.getString("nome");

				Cliente cliente = new Cliente();
				cliente.setIdCliente(id_cliente);
				cliente.setNome(nome);

				clientes.add(cliente);
			}

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return clientes;
	}

	@Override
	public void update(Cliente obj) {

		PreparedStatement st = null;

		try {
			String sql = "UPDATE cliente SET nome = ? WHERE idCliente = ?";
			st = conn.prepareStatement(sql);

			st.setString(1, obj.getNome());
			st.setInt(2, obj.getIdCliente());

			int linhas = st.executeUpdate();

			if (linhas > 0) {
				
			}

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {

			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {

		PreparedStatement st = null;
		try {
			String sql = "DELETE FROM cliente WHERE idCliente = ?";
			st = conn.prepareStatement(sql);

			st.setInt(1, id);
			int linhas = st.executeUpdate();

			if (linhas > 0) {
				
			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {

			DB.closeStatement(st);
		}

	}

}
