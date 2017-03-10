package pavi.melhoramigo.bean;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pavi.melhoramigo.bo.CadastroUsuarioBO;
import pavi.melhoramigo.bo.LoginUsuarioBO;
import pavi.melhoramigo.dao.UsuarioDAO;
import pavi.melhoramigo.dao.conexao.ConexaoBase;
import pavi.melhoramigo.vo.UsuarioVO;

@ManagedBean
public class UsuarioBean extends ConexaoBase {
	private UsuarioVO usuarioVO = new UsuarioVO();	
	private String senha2;

	public UsuarioVO getUsuarioVO() {
		return usuarioVO;
	}

	public void setUsuarioVO(UsuarioVO usuarioVO) {
		this.usuarioVO = usuarioVO;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}
	
	public void cadastrar_usuario() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		CadastroUsuarioBO cadastro = new CadastroUsuarioBO();		
		
		if (!cadastro.isCPF(this.usuarioVO.getCpf())) {
			requestContext.execute("alert('Este número de CPF é inválido!');");
		} else if (!cadastro.verificaIdade(this.usuarioVO.getIdade())) {
			requestContext.execute("alert('Idade inválida, você precisa ter mais de 18 anos!');");
		} else if (!cadastro.isCEP(this.usuarioVO.getEndereco().getCep())) {
			requestContext.execute("alert('Este CEP está incorreto!');");
		} else if (!cadastro.verificaEmail(this.getConexao(), this.usuarioVO.getEmail())) {
			requestContext.execute("alert('Este endereço de email já está sendo usado!');");
		} else if (!cadastro.isSenha(this.usuarioVO.getSenha())) {
			requestContext.execute("alert('A senha deve conter no mínimo 6 caracteres!');");
		} else if (!cadastro.verificaConfirmaSenha(this.usuarioVO.getSenha(), this.senha2)) {
			requestContext.execute("alert('A confirmação de senha está diferente da senha!');");
		} else {
			UsuarioDAO usuarioDAO = new UsuarioDAO(); 
			
			try {
				usuarioDAO.criar(this.getConexao(), this.usuarioVO);
				externalContext.redirect("login_usuario.xhtml?cadastro=ok");
			} catch (SQLException e) { 
				requestContext.execute("alert('Erro ao cadastrar o usuário: " + e.getMessage() + "');");
			} catch (IOException e) {
				requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
			}
		}
	}
	
	public void logar_usuario() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		LoginUsuarioBO login = new LoginUsuarioBO();
		
		if (!login.verificaCadastro(this.getConexao(), this.usuarioVO.getEmail())) {
			requestContext.execute("alert('Este endereço de email não está cadastrado!');");
		} else if (!login.validaSenha(this.usuarioVO.getSenha())) {;
			requestContext.execute("alert('A senha está INCORRETA!');");
		} else if (login.isBan()) {
			requestContext.execute("alert('Este usuário encontra-se BANIDO por tempo indeterminado!');");
		} else {
			externalContext.getSessionMap().put("email_usuario", login.getUsuarioParaLogin().getEmail());
			externalContext.getSessionMap().put("id_usuario", login.getUsuarioParaLogin().getId_usuario());
			externalContext.getSessionMap().put("nome_usuario", login.getUsuarioParaLogin().getNome());
			externalContext.getSessionMap().put("nivel_usuario", login.getUsuarioParaLogin().getNivelUsuario());
			
			try {
				externalContext.redirect("../index.xhtml");
			} catch (IOException e) {
				requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
			}
		}
	}
	
	public void deslogar_usuario() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		externalContext.invalidateSession();
		
		try {
			externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
		} catch (IOException e) {
			requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
		}
	}
	
	public void get_dados_usuario() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		String email = (String)externalContext.getSessionMap().get("email_usuario");		
		this.usuarioVO = usuarioDAO.buscaUsuario(this.getConexao(), email);
	}
	
	public void alterar_dados_usuario() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		CadastroUsuarioBO recadastro = new CadastroUsuarioBO();		
		
		if (!recadastro.verificaIdade(this.usuarioVO.getIdade())) {
			requestContext.execute("alert('Idade inválida, você precisa ter mais de 18 anos!');");
		} else if (!recadastro.isCEP(this.usuarioVO.getEndereco().getCep())) {
			requestContext.execute("alert('Este CEP está incorreto!');");
		} else {	
			UsuarioDAO usuarioDAO = new UsuarioDAO(); 		
			
			try {
				usuarioDAO.updateUsuario(this.getConexao(), this.usuarioVO);
				externalContext.redirect("meus_dados.xhtml?func_edit=ok");
			} catch (SQLException e) { 
				requestContext.execute("alert('Erro ao atualizar os dados do usuário: " + e.getMessage() + "');");
			} catch (IOException e) {
				requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
			}
		}
	}
}
