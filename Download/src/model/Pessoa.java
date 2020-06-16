package model;

public class Pessoa {

	private String nome;
	private String cpf;
	private int dataNascimento;
	private String endere�o;
	private int telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nomeCliente) {
		this.nome = nomeCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(int dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndere�o() {
		return endere�o;
	}

	public void setEndere�o(String endere�o) {
		this.endere�o = endere�o;
	}

	public int getTelefone() {
		return telefone;
	}

	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpf=" + cpf + ", dataNascimento=" + dataNascimento + ", endere�o=" + endere�o
				+ ", telefone=" + telefone + "]";
	}

}
