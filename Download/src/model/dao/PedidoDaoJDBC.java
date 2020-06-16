package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.DB;
import db.DbException;
import model.Bebida;
import model.Cliente;
import model.Comida;
import model.Pedido;

public class PedidoDaoJDBC implements IPedidoDao {

	Connection conn = null;

	public PedidoDaoJDBC() {
		this.conn = DB.getConnection();

	}

	@Override
	public Integer insert(Pedido obj) {

		Integer idPedido = null;

		PreparedStatement st = null;

		try {
			String sql = "INSERT INTO pedido (idPedido,idCliente,nome,idComida,nomePrato,observacoesComida,idBebida,tipoBebida,especificacoes,dataPedido) VALUES (?,?,?,?,?,?,?,?,?,?)";

			st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getIdPedido());
			st.setInt(2, obj.getCliente().getIdCliente());
			st.setString(3, obj.getCliente().getNome());
			st.setInt(4, obj.getComida().getIdComida());
			st.setString(5, obj.getComida().getNomePrato());
			st.setString(6, obj.getComida().getObsevacoes());
			st.setInt(7, obj.getBebida().getIdBebida());
			st.setString(8, obj.getBebida().getTipoBebida());
			st.setString(9, obj.getBebida().getEspecificacoes());
			st.setDate(10, new java.sql.Date(obj.getData().getTime()));

			int linhas = st.executeUpdate();

			if (linhas > 0) {
				ResultSet rs = st.getGeneratedKeys(); // retorna o id criado
				while (rs.next()) {
					idPedido = rs.getInt(1);
				}

				DB.closeResultSet(rs);
			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage()); // envia uma exceção

		} finally {

			DB.closeStatement(st);
		}
		return idPedido;

	}

	@Override
	public Pedido findById(Integer id) {

		PreparedStatement st = null;

		ResultSet rs = null;

		Pedido pedido = new Pedido();

		try {
			String sql = "SELECT idPedido, idCliente,nome,idComida,nomePrato,observacoesComida,idBebida,tipoBebida,especificacoes,dataPedido FROM pedido WHERE idPedido = ?";

			st = conn.prepareStatement(sql);

			st.setInt(1, id);

			rs = st.executeQuery();

			if (rs.next()) {
				int id_pedido = rs.getInt("idPedido");

				int id_cliente = rs.getInt("idCliente");
				String nome = rs.getString("nome");

				int id_comida = rs.getInt("idComida");
				String nomePrato = rs.getString("nomePrato");
				String observacoesComida = rs.getString("observacoesComida");

				int id_bebida = rs.getInt("idBebida");
				String tipoBebida = rs.getString("tipoBebida");
				String especificacoes = rs.getString("especificacoes");

				Date dataPedido = rs.getDate("dataPedido");

				pedido.setIdPedido(id_pedido);

				Cliente cliente = new Cliente();
				cliente.setIdCliente(id_cliente);
				cliente.setNome(nome);

				Comida comida = new Comida();
				comida.setIdComida(id_comida);
				comida.setNomePrato(nomePrato);
				comida.setObsevacoes(observacoesComida);

				Bebida bebida = new Bebida();
				bebida.setIdBebida(id_bebida);
				bebida.setTipoBebida(tipoBebida);
				bebida.setEspecificacoes(especificacoes);

				pedido.setCliente(cliente);
				pedido.setComida(comida);
				pedido.setBebida(bebida);
				pedido.setData(dataPedido);

			}
		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return pedido;

	}

	@Override
	public List<Pedido> findAll() {

		List<Pedido> pedidos = new ArrayList<Pedido>();

		PreparedStatement st = null;

		ResultSet rs = null;
		try {

			String sql = "SELECT idPedido, idCliente,nome,idComida,nomePrato,observacoesComida,idBebida,tipoBebida,especificacoes,dataPedido  FROM pedido";

			st = conn.prepareStatement(sql);

			rs = st.executeQuery();

			while (rs.next()) {

				int id_pedido = rs.getInt("idPedido");

				int id_cliente = rs.getInt("idCliente");
				String nome = rs.getString("nome");

				int id_comida = rs.getInt("idComida");
				String nomePrato = rs.getString("nomePrato");
				String observacoesComida = rs.getString("observacoesComida");

				int id_bebida = rs.getInt("idBebida");
				String tipoBebida = rs.getString("tipoBebida");
				String especificacoes = rs.getString("especificacoes");

				Date dataPedido = rs.getDate("dataPedido");

				Pedido pedido = new Pedido();

				pedido.setIdPedido(id_pedido);

				Cliente cliente = new Cliente();
				cliente.setIdCliente(id_cliente);
				cliente.setNome(nome);

				Comida comida = new Comida();
				comida.setIdComida(id_comida);
				comida.setNomePrato(nomePrato);
				comida.setObsevacoes(observacoesComida);

				Bebida bebida = new Bebida();
				bebida.setIdBebida(id_bebida);
				bebida.setTipoBebida(tipoBebida);
				bebida.setEspecificacoes(especificacoes);

				pedido.setCliente(cliente);
				pedido.setComida(comida);
				pedido.setBebida(bebida);
				pedido.setData(dataPedido);

				pedidos.add(pedido);
			}

		} catch (SQLException e) {

			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return pedidos;

	}

	public List<Pedido> findByName(String nomeBuscado) {

		PreparedStatement st = null;
		ResultSet rs = null;
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {

			String sql = "SELECT idPedido, idCliente,nome,idComida,nomePrato,observacoesComida,idBebida,tipoBebida,especificacoes,dataPedido  FROM pedido WHERE nome LIKE ?";

			st = conn.prepareStatement(sql);

			st.setString(1, '%' + nomeBuscado + '%');

			rs = st.executeQuery();

			while (rs.next()) {

				int id_pedido = rs.getInt("idPedido");

				int id_cliente = rs.getInt("idCliente");
				String nome = rs.getString("nome");

				int id_comida = rs.getInt("idComida");
				String nomePrato = rs.getString("nomePrato");
				String observacoesComida = rs.getString("observacoesComida");

				int id_bebida = rs.getInt("idBebida");
				String tipoBebida = rs.getString("tipoBebida");
				String especificacoes = rs.getString("especificacoes");

				Date dataPedido = rs.getDate("dataPedido");

				Pedido pedido = new Pedido();

				pedido.setIdPedido(id_pedido);

				Cliente cliente = new Cliente();
				cliente.setIdCliente(id_cliente);
				cliente.setNome(nome);

				Comida comida = new Comida();
				comida.setIdComida(id_comida);
				comida.setNomePrato(nomePrato);
				comida.setObsevacoes(observacoesComida);

				Bebida bebida = new Bebida();
				bebida.setIdBebida(id_bebida);
				bebida.setTipoBebida(tipoBebida);
				bebida.setEspecificacoes(especificacoes);

				pedido.setCliente(cliente);
				pedido.setComida(comida);
				pedido.setBebida(bebida);
				pedido.setData(dataPedido);

				pedidos.add(pedido);
			}

		} catch (SQLException e) {

			throw new DbException(e.getMessage());

		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

		return pedidos;

	}

	@Override
	public void update(Pedido obj) {
		PreparedStatement st = null;

		try {
			String sql = "UPDATE pedido SET nome = ?, nomePrato = ?,observacoesComida=?, tipoBebida = ?,especificacoes=?  WHERE idPedido = ?";

			st = conn.prepareStatement(sql);

			st.setString(1, obj.getCliente().getNome());
			st.setString(2, obj.getComida().getNomePrato());
			st.setString(3, obj.getComida().getObsevacoes());
			st.setString(4, obj.getBebida().getTipoBebida());
			st.setString(5, obj.getBebida().getEspecificacoes());
			st.setInt(6, obj.getIdPedido());

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
	public void deleteById(Integer obj) {

		PreparedStatement st = null;
		try {
			String sql = "DELETE FROM pedido WHERE idPedido = ?";

			st = conn.prepareStatement(sql);

			st.setInt(1, obj);
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
