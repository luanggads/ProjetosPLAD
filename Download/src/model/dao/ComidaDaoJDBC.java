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
import model.Comida;

public class ComidaDaoJDBC implements IComidaDao {

	Connection conn = null;

	public ComidaDaoJDBC() {
		this.conn = DB.getConnection();
	}

	@Override
	public Integer insert(Comida obj) {
		Integer idComida = null;

		PreparedStatement st = null;

		try {
			String sql = "INSERT INTO comida (nomePrato, observacoes) VALUES (?, ?)";

			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomePrato());
			st.setString(2, obj.getObsevacoes());

			int linhas = st.executeUpdate();

			if (linhas > 0) {
				ResultSet rs = st.getGeneratedKeys(); // retorna o id criado
				while (rs.next()) {
					idComida = rs.getInt(1);
				}

				DB.closeResultSet(rs);
			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());// envia uma exceção

		} finally {

			DB.closeStatement(st);
		}
		return idComida;

	}

	@Override
	public Comida findById(Integer id) {

		PreparedStatement st = null;

		ResultSet rs = null;

		Comida comida = new Comida();

		try {
			String sql = "SELECT idComida, nomePrato, observacoes  FROM comida WHERE idComida = ?";
			st = conn.prepareStatement(sql);

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				int id_comida = rs.getInt("idComida");
				String nomePrato = rs.getString("nomePrato");
				String observacoes = rs.getString("observacoes");

				comida.setIdComida(id_comida);

				comida.setNomePrato(nomePrato);

				comida.setObsevacoes(observacoes);

			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return comida;

	}

	@Override
	public List<Comida> findAll() {

		List<Comida> comidas = new ArrayList<Comida>();

		PreparedStatement st = null;

		ResultSet rs = null;

		try {

			String sql = "SELECT idComida, nomePrato, observacoes  FROM comida";

			st = conn.prepareStatement(sql);

			rs = st.executeQuery();

			while (rs.next()) {

				int id_comida = rs.getInt("idComida");
				String nomePrato = rs.getString("nomePrato");
				String observacoes = rs.getString("observacoes");

				Comida comida = new Comida();

				comida.setIdComida(id_comida);

				comida.setNomePrato(nomePrato);

				comida.setObsevacoes(observacoes);

				comidas.add(comida);
			}

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return comidas;

	}

	@Override
	public void update(Comida obj) {

		PreparedStatement st = null;

		try {
			String sql = "UPDATE comida SET nomePrato = ?,observacoes = ? WHERE idComida = ?";

			st = conn.prepareStatement(sql);

			st.setString(1, obj.getNomePrato());
			st.setString(2, obj.getObsevacoes());
			st.setInt(3, obj.getIdComida());

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
			String sql = "DELETE FROM comida WHERE idComida = ?";

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
