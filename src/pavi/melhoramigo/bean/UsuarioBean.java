package pavi.melhoramigo.bean;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pavi.melhoramigo.bo.CadastroUsuarioBO;
import pavi.melhoramigo.dao.UsuarioDAO;
import pavi.melhoramigo.dao.conexao.ConexaoBase;
import pavi.melhoramigo.vo.UsuarioVO;

@ManagedBean
@SessionScoped
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
	
	public void limpar() {
		this.usuarioVO = new UsuarioVO();
		senha2 = null;
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
			
			this.usuarioVO.setCpf(cadastro.formataCPF(this.usuarioVO.getCpf()));
			this.usuarioVO.getEndereco().setCep(cadastro.formataCEP(this.usuarioVO.getEndereco().getCep()));
	
			UsuarioDAO usuarioDAO = new UsuarioDAO(); 
			
			try {
				usuarioDAO.criar(this.getConexao(), this.usuarioVO);
				this.limpar();
				
				externalContext.redirect("login_usuario.xhtml?cadastro=ok");
			} catch (SQLException e) { 
				requestContext.execute("alert('Erro ao cadastrar o usuário: " + e.getMessage() + "');");
			} catch (IOException e) {
				requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
			}
		}
	}
}
