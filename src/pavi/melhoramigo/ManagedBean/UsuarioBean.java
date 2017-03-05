package pavi.melhoramigo.ManagedBean;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import pavi.melhoramigo.dao.UsuarioDAO;
import pavi.melhoramigo.dao.conexao.ConexaoBase;
import pavi.melhoramigo.vo.EnderecoVO;

@ManagedBean
@SessionScoped

public class UsuarioBean extends ConexaoBase {
	private int id_usuario;
	private String email;
	private String senha1;
	private String senha2;
	private String nome;
	private String cpf;
	private int idade;
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
	
	public String getSenha1() {
		return senha1;
	}

	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
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
	
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
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
	
	public void cadastrar_usuario() {
		//CadastroUsuarioBO cadastro = new CadastroUsuarioBO();		

		UsuarioBean novoUsuario = new UsuarioBean();
		EnderecoVO enderecoUsuario = new EnderecoVO();

		novoUsuario.setNome(this.nome);
		novoUsuario.setEmail(this.email);
		novoUsuario.setSenha1(this.senha1);
		novoUsuario.setSenha2(this.senha2);
		novoUsuario.setCpf(this.cpf);
		novoUsuario.setIdade(this.idade);
		novoUsuario.setTelefone(this.telefone);

		enderecoUsuario.setCep(this.endereco.getCep());
		enderecoUsuario.setRua(this.endereco.getRua());
		enderecoUsuario.setnCasa(this.endereco.getnCasa());
		enderecoUsuario.setBairro(this.endereco.getBairro());
		enderecoUsuario.setComplemento(this.endereco.getComplemento());
		enderecoUsuario.setCidade(this.endereco.getCidade());
		enderecoUsuario.setEstado(this.endereco.getEstado());

		novoUsuario.setEndereco(enderecoUsuario);

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		try {
			usuarioDAO.criar(this.getConexao(), novoUsuario);
		} catch (SQLException e) { /*
			out.println("<script>");
			out.println("alert('Erro ao cadastrar o usuário: " + e.getMessage() + "');");
			out.println("history.back();");
			out.println("</script>"); */
		}	
	}
}
