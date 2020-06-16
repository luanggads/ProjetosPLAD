package view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import application.Main.MudancaTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Bebida;
import model.Cliente;
import model.Comida;
import model.Pedido;
import model.dao.BebidaDaoJDBC;
import model.dao.ClienteDaoJDBC;
import model.dao.ComidaDaoJDBC;
import model.dao.DaoFactory;
import model.dao.PedidoDaoJDBC;

public class ConsultaViewController implements Initializable {

	ClienteDaoJDBC clienteDao = null;
	ComidaDaoJDBC comidaDao = null;
	BebidaDaoJDBC bebidaDao = null;
	PedidoDaoJDBC pedidoDao = null;

	@FXML
	private TableView<TableObject> tblPedido;

	@FXML
	private TextField tfCliente;

	@FXML
	private TableColumn<TableObject, String> clNomeCliente;

	@FXML
	private TableColumn<TableObject, String> clComida;

	@FXML
	private TableColumn<TableObject, String> clBebida;

	@FXML
	private TableColumn<TableObject, String> clEspecificacoesBebida;

	@FXML
	private Button btnBuscar;

	@FXML
	private Button btnVoltar;

	@FXML
	private void onBtnBuscar() {

		String pesquisaNome = tfCliente.getText();

		updateTable(pesquisaNome);

	}

	@FXML
	private void onBtnVoltar() {

		Main.trocaTela("main", null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MudancaTela novoListener = new MudancaTela() {
			@Override
			public void mudarTelaListener(String tela, Object obj) {

				associarColunas();
				updateTable(null);
			}
		};
		Main.addListenerMudancaTela(novoListener);

		clienteDao = (ClienteDaoJDBC) DaoFactory.createClienteDao();
		comidaDao = (ComidaDaoJDBC) DaoFactory.createComidaDao();
		bebidaDao = (BebidaDaoJDBC) DaoFactory.createBebidaDao();
		pedidoDao = (PedidoDaoJDBC) DaoFactory.createPedidoDao();

		associarColunas();
		updateTable(null);

	}

	private void associarColunas() {

		PropertyValueFactory clNomeClienteProperty = new PropertyValueFactory<>("nomeCliente");
		PropertyValueFactory clComidaProperty = new PropertyValueFactory<>("comida");
		PropertyValueFactory clBebidaProperty = new PropertyValueFactory<>("bebida");
		PropertyValueFactory clEspecificacoesBebidaProperty = new PropertyValueFactory<>("especificacoesBebida");

		clNomeCliente.setCellValueFactory(clNomeClienteProperty);
		clComida.setCellValueFactory(clComidaProperty);
		clBebida.setCellValueFactory(clBebidaProperty);
		clEspecificacoesBebida.setCellValueFactory(clEspecificacoesBebidaProperty);

	}

	public static class TableObject {

		private int id;
		private String nomeCliente;
		private String comida;
		private String bebida;
		private String especificacoesBebida;

		public TableObject(int id, String nomeCliente, String comida, String bebida, String especificacoesBebida) {

			this.id = id;
			this.nomeCliente = nomeCliente;
			this.comida = comida;
			this.bebida = bebida;
			this.especificacoesBebida = especificacoesBebida;

		}

		public TableObject(String nomeCliente, String comida, String bebida, String especificacoesBebida) {

			this.nomeCliente = nomeCliente;
			this.comida = comida;
			this.bebida = bebida;
			this.especificacoesBebida = especificacoesBebida;

		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNomeCliente() {
			return nomeCliente;
		}

		public void setNomeCliente(String nomeCliente) {
			this.nomeCliente = nomeCliente;
		}

		public String getComida() {
			return comida;
		}

		public void setComida(String comida) {
			this.comida = comida;
		}

		public String getBebida() {
			return bebida;
		}

		public void setBebida(String bebida) {
			this.bebida = bebida;
		}

		public String getEspecificacoesBebida() {
			return especificacoesBebida;
		}

		public void setEspecificacoesBebida(String especificacoesBebida) {
			this.especificacoesBebida = especificacoesBebida;
		}

	}

	private void updateTable(Object obj) {

		// limpa antes de adicionar
		tblPedido.getItems().clear();

		// lista para obter os pedidos

		List<Pedido> pedidos;
		if (obj != null) {

			String nomeBuscado = (String) obj;
			pedidos = pedidoDao.findByName(nomeBuscado);

		} else {
			pedidos = pedidoDao.findAll();
		}

		String nomeCliente = null;
		String comida = null;
		String bebida = null;
		String especificacoesBebida = null;
		Integer id_pedido = null;

		for (Pedido ped : pedidos) {

			Cliente cliente = (Cliente) clienteDao.findById(ped.getIdPedido());
			nomeCliente = cliente.getNome();

			Comida co = (Comida) comidaDao.findById(ped.getIdPedido());
			comida = co.getNomePrato();

			Bebida beb = (Bebida) bebidaDao.findById(ped.getIdPedido());
			bebida = beb.getTipoBebida();
			especificacoesBebida = beb.getEspecificacoes();

			id_pedido = ped.getIdPedido();

			TableObject table = new TableObject(id_pedido, nomeCliente, comida, bebida, especificacoesBebida);

			tblPedido.getItems().add(table);

		}

	}

}
