package model.dao;

public class DaoFactory {

	public static IClienteDao createClienteDao() {
		return new ClienteDaoJDBC();
	}

	public static IComidaDao createComidaDao() {
		return new ComidaDaoJDBC();
	}

	public static IBebidaDao createBebidaDao() {
		return new BebidaDaoJDBC();
	}

	public static IPedidoDao createPedidoDao() {
		return new PedidoDaoJDBC();
	}

}
