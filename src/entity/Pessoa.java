package entity;

public class Pessoa {
	private Integer id;
	private String nome;
	private Integer idade;
	private String cidade;
	public Pessoa() {
	}
	public Pessoa(Integer id, String nome, Integer idade, String cidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.cidade = cidade;
	}
	@Override
	public String toString() {
		return id + " - " + nome + " - " + idade + " - " + cidade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
}
