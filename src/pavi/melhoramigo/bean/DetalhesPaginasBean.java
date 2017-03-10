package pavi.melhoramigo.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import pavi.melhoramigo.bo.ControleLoginBO;
import pavi.melhoramigo.vo.ParametroGetVO;

@ManagedBean
public class DetalhesPaginasBean {
	private ControleLoginBO controle_login = new ControleLoginBO();
	private ParametroGetVO p_cadastro = new ParametroGetVO();
	private ParametroGetVO p_func_edit = new ParametroGetVO();
	private ParametroGetVO p_func_senha = new ParametroGetVO();
	
	public DetalhesPaginasBean() {
		this.p_cadastro.setCod_para_inserir("<div class='alert alert-success fade in'>"
										  + "<button type='button' class='close' data-dismiss='alert'>×</button>"
								 		  + "<strong>Parabéns!</strong> Seu cadastro foi criado com sucesso!"
								  		  + "</div>");
		
		this.p_func_edit.setCod_para_inserir("<div class='alert alert-success fade in'>"
								  	 	   + "<button type='button' class='close' data-dismiss='alert'>×</button>"
								 		   + "<strong>Pronto!</strong> Os seus dados de cadastro foram atualizados com sucesso!"
								  		   + "</div>");
		
		this.p_func_senha.setCod_para_inserir("<div class='alert alert-success fade in'>"
								  	 	    + "<button type='button' class='close' data-dismiss='alert'>×</button>"
								 		    + "<strong>Pronto!</strong> A sua senha foi alterada com sucesso!"
								  		    + "</div>");
	}
	
	public ParametroGetVO getP_func_senha() {
		return p_func_senha;
	}

	public void setP_func_senha(ParametroGetVO p_func_senha) {
		this.p_func_senha = p_func_senha;
	}

	public ParametroGetVO getP_func_edit() {
		return p_func_edit;
	}

	public void setP_func_edit(ParametroGetVO p_func_edit) {
		this.p_func_edit = p_func_edit;
	}

	public ParametroGetVO getP_cadastro() {		
		return p_cadastro;
	}

	public void setP_cadastro(ParametroGetVO p_cadastro) {		
		this.p_cadastro = p_cadastro;
	}

	public ControleLoginBO getControle_login() {
		return controle_login;
	}

	public void setControle_login(ControleLoginBO controle_login) {
		this.controle_login = controle_login;
	}
	
	public String getPathProjeto() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		return externalContext.getRequestServletPath();
	}
}
