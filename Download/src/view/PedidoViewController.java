package view;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import application.Main;
import application.Main.MudancaTela;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Bebida;
import model.Cliente;
import model.Comida;
import model.Pedido;
import model.dao.BebidaDaoJDBC;
import model.dao.ClienteDaoJDBC;
import model.dao.ComidaDaoJDBC;
import model.dao.DaoFactory;
import model.dao.PedidoDaoJDBC;
import view.MainViewController.TableObject;

public class PedidoViewController implements Initializable {

	ClienteDaoJDBC clienteDao = null;
	ComidaDaoJDBC comidaDao = null;
	BebidaDaoJDBC bebidaDao = null;
	PedidoDaoJDBC pedidoDao = null;

	Pedido pedido = new Pedido();

	@FXML
	private TextField tfCliente;

	@FXML
	private TextField tfNomeDoPrato;

	@FXML
	private TextField tfObservacoesComida;

	@FXML
	private TextField tfTipoDaBebida;

	@FXML
	private TextField tfEspecificacoes;

	@FXML
	private DatePicker dpData;

	@FXML
	private Button btnOk;

	@FXML
	private Button btnCancelar;

	@FXML
	private void onBtnok() {

		try {

			if (tfCliente.getText().isEmpty()) {
				throw new RuntimeException("Campo (cliente) é obrigatório");
			}
			if (tfNomeDoPrato.getText().isEmpty()) {
				throw new RuntimeException("Campo (nome do prato) é obrigatório");
			}
			if (tfTipoDaBebida.getText().isEmpty()) {
				throw new RuntimeException("Campo (tipo da bebida) é obrigatório");
			}

			if (tfEspecificacoes.getText().isEmpty()) {
				throw new RuntimeException("Campo (especificações) é obrigatório");
			}

			String nomeCliente = tfCliente.getText();
			String nomeDoPrato = tfNomeDoPrato.getText();
			String observacoesComida = tfObservacoesComida.getText();
			String tipoDaBebida = tfTipoDaBebida.getText();
			String especificacoes = tfEspecificacoes.getText();
			LocalDate localDate = dpData.getValue();
			Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			Date data = Date.from(instant);

			if (pedido != null) {
				// vem de update
				Cliente cliente = pedido.getCliente();
				cliente.setNome(nomeCliente);

				Comida comida = pedido.getComida();
				comida.setNomePrato(nomeDoPrato);
				comida.setObsevacoes(observacoesComida);

				Bebida bebida = pedido.getBebida();
				bebida.setTipoBebida(tipoDaBebida);
				bebida.setEspecificacoes(especificacoes);

				clienteDao.update(cliente);
				comidaDao.update(comida);
				bebidaDao.update(bebida);
				pedidoDao.update(pedido);

				Main.trocaTela("main", null);
			} else {

				Cliente cliente = new Cliente();
				cliente.setNome(nomeCliente);

				Comida comida = new Comida();
				comida.setNomePrato(nomeDoPrato);
				comida.setObsevacoes(observacoesComida);

				Bebida bebida = new Bebida();
				bebida.setTipoBebida(tipoDaBebida);
				bebida.setEspecificacoes(especificacoes);

				Pedido pedido = new Pedido();
				pedido.setData(data);

				int id_cliente = clienteDao.insert(cliente);
				cliente.setIdCliente(id_cliente);

				pedido.setCliente(cliente);

				int id_comida = comidaDao.insert(comida);
				comida.setIdComida(id_comida);

				pedido.setComida(comida);

				int id_bebida = bebidaDao.insert(bebida);
				bebida.setIdBebida(id_bebida);

				pedido.setBebida(bebida);

				pedidoDao.insert(pedido);

				Main.trocaTela("main", null);
			}
		} catch (RuntimeException e) {

			Alert alerta = new Alert(Alert.AlertType.ERROR);
			alerta.setTitle("Erro");
			alerta.setHeaderText("Erro no formulário");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();

		}

	}

	@FXML
	private void onBtnCancelar() {

		Main.trocaTela("main", null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		MudancaTela novoListener = new MudancaTela() {
			@Override
			public void mudarTelaListener(String tela, Object obj) {
				// garantir que o evento é na tela cliente
				if (tela.equals("cadastro")) {

				}
				if (obj != null) {

					// vem do botão Atualizar
					TableObject tableObject = (TableObject) obj;

					pedido = (Pedido) pedidoDao.findById(tableObject.getId());

					Cliente cliente = (Cliente) clienteDao.findById(pedido.getCliente().getIdCliente());
					Comida comida = (Comida) comidaDao.findById(pedido.getComida().getIdComida());
					Bebida bebida = (Bebida) bebidaDao.findById(pedido.getBebida().getIdBebida());
					pedido.setCliente(cliente);
					pedido.setComida(comida);
					pedido.setBebida(bebida);

					tfCliente.setText(pedido.getCliente().getNome());
					tfNomeDoPrato.setText(pedido.getComida().getNomePrato());
					tfObservacoesComida.setText(pedido.getComida().getObsevacoes());
					tfTipoDaBebida.setText(pedido.getBebida().getTipoBebida());
					tfEspecificacoes.setText(pedido.getBebida().getEspecificacoes());

				} else {
					// vem do botão cadastro
					pedido = null;

					tfCliente.clear();
					tfNomeDoPrato.clear();
					tfObservacoesComida.clear();
					tfTipoDaBebida.clear();
					tfEspecificacoes.clear();
				}

			}

		};

		Main.addListenerMudancaTela(novoListener);

		clienteDao = (ClienteDaoJDBC) DaoFactory.createClienteDao();
		comidaDao = (ComidaDaoJDBC) DaoFactory.createComidaDao();
		bebidaDao = (BebidaDaoJDBC) DaoFactory.createBebidaDao();
		pedidoDao = (PedidoDaoJDBC) DaoFactory.createPedidoDao();

	}
}
