package pavi.melhoramigo.bo;

import java.sql.Connection;
import java.sql.SQLException;

import pavi.melhoramigo.dao.UsuarioDAO;
import pavi.melhoramigo.vo.UsuarioVO;

public class LoginUsuarioBO {
	
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private UsuarioVO usuarioParaLogin;

	public UsuarioVO getUsuarioParaLogin() {
		return usuarioParaLogin;
	}

	public void setUsuarioParaLogin(UsuarioVO usuarioParaLogin) {
		this.usuarioParaLogin = usuarioParaLogin;
	}
	
	public boolean verificaCadastro (Connection conexao, String email) {
		try {
			this.usuarioParaLogin = usuarioDAO.buscaUsuario(conexao, email);
			
			if (this.usuarioParaLogin != null) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	public boolean validaSenha (String senha) {
		if (this.usuarioParaLogin.getSenha().equals(senha)) {
			return true;
		}
		return false;
	}
	
	public boolean isBan () {
		if (this.usuarioParaLogin.getStatus_ban() == 1) {
			return true;
		}
		return false;		
	}
}
