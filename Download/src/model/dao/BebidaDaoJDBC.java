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
import model.Bebida;

public class BebidaDaoJDBC implements IBebidaDao {

	Connection conn = null;

	public BebidaDaoJDBC() {
		this.conn = DB.getConnection();
	}

	@Override
	public Integer insert(Bebida obj) {

		Integer idBebida = null;

		PreparedStatement st = null;

		try {
			String sql = "INSERT INTO bebida (tipoBebida, especificacoes ) VALUES (?, ?)";

			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getTipoBebida());
			st.setString(2, obj.getEspecificacoes());

			int linhas = st.executeUpdate();

			if (linhas > 0) {
				ResultSet rs = st.getGeneratedKeys(); // retorna o id criado
				while (rs.next()) {
					idBebida = rs.getInt(1);
				}

				DB.closeResultSet(rs);
			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());// envia uma exceção

		} finally {

			DB.closeStatement(st);
		}
		return idBebida;

	}

	@Override
	public Bebida findById(Integer id) {

		PreparedStatement st = null;

		ResultSet rs = null;

		Bebida bebida = new Bebida();

		try {
			String sql = "SELECT idBebida, tipoBebida, especificacoes  FROM bebida WHERE idBebida = ?";
			st = conn.prepareStatement(sql);

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				int id_bebida = rs.getInt("idBebida");
				String tipoBebida = rs.getString("tipoBebida");
				String especificacoes = rs.getString("especificacoes");

				bebida.setIdBebida(id_bebida);

				bebida.setTipoBebida(tipoBebida);

				bebida.setEspecificacoes(especificacoes);

			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return bebida;
	}

	@Override
	public List<Bebida> findAll() {

		List<Bebida> bebidas = new ArrayList<Bebida>();

		PreparedStatement st = null;

		ResultSet rs = null;

		try {

			String sql = "SELECT idBebida, tipoBebida, especificacoes  FROM bebida";

			st = conn.prepareStatement(sql);

			rs = st.executeQuery();

			while (rs.next()) {

				int id_bebida = rs.getInt("idBebida");
				String tipoBebida = rs.getString("tipoBebida");
				String especificacoes = rs.getString("especificacoes");

				Bebida bebida = new Bebida();

				bebida.setIdBebida(id_bebida);

				bebida.setTipoBebida(tipoBebida);

				bebida.setEspecificacoes(especificacoes);

				bebidas.add(bebida);
			}

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return bebidas;

	}

	@Override
	public void update(Bebida obj) {

		PreparedStatement st = null;

		try {
			String sql = "UPDATE bebida SET tipoBebida = ?,especificacoes = ? WHERE idBebida = ?";

			st = conn.prepareStatement(sql);

			st.setString(1, obj.getTipoBebida());
			st.setString(2, obj.getEspecificacoes());
			st.setInt(3, obj.getIdBebida());

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
			String sql = "DELETE FROM bebida WHERE idBebida = ?";

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
