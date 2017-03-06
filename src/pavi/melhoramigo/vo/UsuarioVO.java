package pavi.melhoramigo.vo;

public class UsuarioVO {
	private int id_usuario;
	private String email;
	private String senha;
	private String nome;
	private String cpf;
	private String idade;
	private String telefone;
	private int nivelUsuario;
	private int status_ban;
	private EnderecoVO endereco = new EnderecoVO();
	
	public int getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getIdade() {
		return idade;
	}
	
	public void setIdade(String idade) {
		this.idade = idade;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public int getNivelUsuario() {
		return nivelUsuario;
	}
	
	public void setNivelUsuario(int nivelUsuario) {
		this.nivelUsuario = nivelUsuario;
	}
	
	public int getStatus_ban() {
		return status_ban;
	}
	
	public void setStatus_ban(int status_ban) {
		this.status_ban = status_ban;
	}
	
	public EnderecoVO getEndereco() {
		return endereco;
	}
	
	public void setEndereco(EnderecoVO endereco) {
		this.endereco = endereco;
	}
}
