package view;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import application.Main.MudancaTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class MainViewController implements Initializable {

	ClienteDaoJDBC clienteDao = null;
	ComidaDaoJDBC comidaDao = null;
	BebidaDaoJDBC bebidaDao = null;
	PedidoDaoJDBC pedidoDao = null;

	@FXML
	private TableView<TableObject> tblPedido;

	@FXML
	private TableColumn<TableObject, String> clNomeCliente;

	@FXML
	private TableColumn<TableObject, String> clComida;

	@FXML
	private TableColumn<TableObject, String> clObservacoesComida;

	@FXML
	private TableColumn<TableObject, String> clBebida;

	@FXML
	private TableColumn<TableObject, String> clEspecificacoesBebida;

	@FXML
	private TableColumn<TableObject, Date> clData;

	@FXML
	private Button btnCadastrar;

	@FXML
	private Button btnAtualizar;

	@FXML
	private Button btnApagar;

	@FXML
	private Button btnConsultar;

	@FXML
	public void onBtnCadastrar() {

		Main.trocaTela("cadastro", null);
	}

	@FXML
	public void onBtnAtualizar() {

		TableObject selecionado = tblPedido.getSelectionModel().getSelectedItem();

		if (selecionado != null) {

			Main.trocaTela("cadastro", selecionado);

		}

	}

	@FXML
	public void onBtnApagar() {

		// pegando o elemento
		TableObject selecionado = tblPedido.getSelectionModel().getSelectedItem();

		// mostrando mensagem de alerta
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");

		alert.setHeaderText("Deseja excluir o Pedido?");
		alert.setContentText(selecionado.getNomeCliente());
		Optional<ButtonType> resultado = alert.showAndWait();

		// se confirmardo então será deletado
		if (resultado.get() == ButtonType.OK) {

			pedidoDao.deleteById(selecionado.getId());

			updateTable();
		}

	}

	@FXML
	public void onBtnConsultar() {

		Main.trocaTela("consulta", null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		MudancaTela novoListener = new MudancaTela() {
			@Override
			public void mudarTelaListener(String tela, Object obj) {

				associarColunas();
				updateTable();
			}
		};
		Main.addListenerMudancaTela(novoListener);

		clienteDao = (ClienteDaoJDBC) DaoFactory.createClienteDao();
		comidaDao = (ComidaDaoJDBC) DaoFactory.createComidaDao();
		bebidaDao = (BebidaDaoJDBC) DaoFactory.createBebidaDao();
		pedidoDao = (PedidoDaoJDBC) DaoFactory.createPedidoDao();

		associarColunas();
		updateTable();
	}

	private void associarColunas() {

		PropertyValueFactory clNomeClienteProperty = new PropertyValueFactory<>("nomeCliente");
		PropertyValueFactory clComidaProperty = new PropertyValueFactory<>("comida");
		PropertyValueFactory clObservacoesComidaProperty = new PropertyValueFactory<>("observacoesComida");
		PropertyValueFactory clBebidaProperty = new PropertyValueFactory<>("bebida");
		PropertyValueFactory clEspecificacoesBebidaProperty = new PropertyValueFactory<>("especificacoesBebida");
		PropertyValueFactory clDataProperty = new PropertyValueFactory<>("data");

		clNomeCliente.setCellValueFactory(clNomeClienteProperty);
		clComida.setCellValueFactory(clComidaProperty);
		clObservacoesComida.setCellValueFactory(clObservacoesComidaProperty);
		clBebida.setCellValueFactory(clBebidaProperty);
		clEspecificacoesBebida.setCellValueFactory(clEspecificacoesBebidaProperty);
		clData.setCellValueFactory(clDataProperty);
	}

	public static class TableObject {

		private int id;
		private String nomeCliente;
		private String comida;
		private String observacoesComida;
		private String bebida;
		private String especificacoesBebida;
		private String data;

		public TableObject(int id, String nomeCliente, String comida, String observacoesComida, String bebida,
				String especificacoesBebida, String data) {

			this.id = id;
			this.nomeCliente = nomeCliente;
			this.comida = comida;
			this.observacoesComida = observacoesComida;
			this.bebida = bebida;
			this.especificacoesBebida = especificacoesBebida;
			this.data = data;
		}

		public TableObject(String nomeCliente, String comida, String observacoesComida, String bebida,
				String especificacoesBebida, Object data) {

			this.nomeCliente = nomeCliente;
			this.comida = comida;
			this.observacoesComida = observacoesComida;
			this.bebida = bebida;
			this.especificacoesBebida = especificacoesBebida;
			this.data = (String) data;
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

		public String getObservacoesComida() {
			return observacoesComida;
		}

		public void setObservacoesComida(String observacoesComida) {
			this.observacoesComida = observacoesComida;
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

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}

	}

	private void updateTable() {

		// limpa antes de adicionar
		tblPedido.getItems().clear();

		// lista para obter os pedidos
		List<Pedido> pedidos = pedidoDao.findAll();

		String nomeCliente = null;
		String comida = null;
		String observacoesComida = null;
		String bebida = null;
		String especificacoesBebida = null;
		Date data = null;
		Integer id_pedido = null;

		for (Pedido ped : pedidos) {

			Cliente cliente = (Cliente) clienteDao.findById(ped.getIdPedido());
			nomeCliente = cliente.getNome();

			Comida co = (Comida) comidaDao.findById(ped.getIdPedido());
			comida = co.getNomePrato();
			observacoesComida = co.getObsevacoes();

			Bebida beb = (Bebida) bebidaDao.findById(ped.getIdPedido());
			bebida = beb.getTipoBebida();
			especificacoesBebida = beb.getEspecificacoes();

			data = ped.getData();
			id_pedido = ped.getIdPedido();

			TableObject table = new TableObject(id_pedido, nomeCliente, comida, observacoesComida, bebida,
					especificacoesBebida, data.toString());

			tblPedido.getItems().add(table);

		}

	}

}
