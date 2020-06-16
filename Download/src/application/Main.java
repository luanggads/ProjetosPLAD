package application;

import java.util.ArrayList;

//import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	// palco
	private static Stage stage;

	// Primeira tela
	private static Scene mainScene;

	// Tela de Cadastro
	private static Scene pedidoScene;

	// Tela de Consulta
	private static Scene consultaScene;

	@Override
	public void start(Stage primaryStage) {
		try {
			// Carregando o fxml
			// URL fxmlMainView = getClass().getResource("/view/mainView.fxml");

			// Carregando a tela principal (com uma única linda de comando)
			Parent parentMainView = FXMLLoader.load(getClass().getResource("/view/mainView.fxml"));

			// Carregando a tela de cadastro direto
			Parent parentPedidoView = FXMLLoader.load(getClass().getResource("/view/pedidoView.fxml"));

			// Carregando a tela de consulta direto
			Parent parentCosultaView = FXMLLoader.load(getClass().getResource("/view/consultaView.fxml"));

			// Incializando o atributo palco e adicionando um título
			stage = primaryStage;
			primaryStage.setTitle("PLAD");

			// Criando a cena da tela principal
			mainScene = new Scene(parentMainView);
			primaryStage.setScene(mainScene);

			// Criando a cena da tela de cadastro
			pedidoScene = new Scene(parentPedidoView);

			// Criando a cena da tela de consulta
			consultaScene = new Scene(parentCosultaView);

			// exibindo a tela
			primaryStage.setScene(mainScene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		launch(args);

	}

	public static void trocaTela(String tela, Object obj) {
		switch (tela) {

		case "main":
			stage.setScene(mainScene);
			notificarTodosListeners("main", obj);
			break;
		case "cadastro":
			stage.setScene(pedidoScene);
			notificarTodosListeners("cadastro", obj);
			break;
		case "consulta":
			stage.setScene(consultaScene);
			notificarTodosListeners("consulta", obj);
			break;

		}
	}

	public static interface MudancaTela {

		void mudarTelaListener(String tela, Object obj);
	}

	private static ArrayList<MudancaTela> listeners = new ArrayList<>();

	public static void addListenerMudancaTela(MudancaTela novoListener) {

		listeners.add(novoListener);
	}

	private static void notificarTodosListeners(String tela, Object obj) {

		for (MudancaTela listener : listeners) {

			listener.mudarTelaListener(tela, obj);
		}
	}

}
