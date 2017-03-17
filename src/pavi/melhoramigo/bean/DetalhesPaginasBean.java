package pavi.melhoramigo.bean;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pavi.melhoramigo.bo.ControleLoginBO;
import pavi.melhoramigo.vo.ParametroGetVO;

@ManagedBean
public class DetalhesPaginasBean {
	private ControleLoginBO controle_login = new ControleLoginBO();
	private ParametroGetVO p_cadastro = new ParametroGetVO();
	private ParametroGetVO p_func_edit = new ParametroGetVO();
	private ParametroGetVO p_func_senha = new ParametroGetVO();
	private ParametroGetVO p_envioSolicit = new ParametroGetVO();
	private ParametroGetVO p_func_pedidos_ado_sim = new ParametroGetVO();
	private ParametroGetVO p_func_pedidos_ado_nao = new ParametroGetVO();
	
	
	private String encerraThumbnailsListaCaes = "</ul><ul class='thumbnails'>";
	
	private String alertVerificaLoginFichaCao = "<div class='alert alert-info fade in'>"
					                          + "<button type='button' class='close' data-dismiss='alert'>×</button>"
					                          + "<strong>Quer adotar este cãozinho?</strong> Faça o Login ou Cadastre-se para continuar!"
					                          + "</div>";
	
	private String spanCaoDisponivel = "<p class='center'><span class='label label-success'>ESTÁ DISPONÍVEL</span></p>";
	private String spanCaoNaoDisponivel = "<p class='center'><span class='label label-important'>NÃO ESTÁ DISPONÍVEL</span></p>";
	
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
		
		this.p_envioSolicit.setCod_para_inserir("<div class='alert alert-success fade in'>"
								  	 	      + "<button type='button' class='close' data-dismiss='alert'>×</button>"
								 		      + "<strong>Recebemos a sua mensagem!</strong> Seus dados serão analisados e entraremos em contato com você em breve!"
								  		      + "</div>");
		
		this.p_func_pedidos_ado_sim.setCod_para_inserir("<div class='alert alert-success fade in'>"
										  	 	      + "<button type='button' class='close' data-dismiss='alert'>×</button>"
										 		      + "<strong>Aceito!</strong> O pedido de adoção foi aceito por você  com sucesso!"
										  		      + "</div>");
		
		this.p_func_pedidos_ado_nao.setCod_para_inserir("<div class='alert alert-error fade in'>"
										  	 	      + "<button type='button' class='close' data-dismiss='alert'>×</button>"
										 		      + "<strong>Recusado!</strong> O pedido de adoção foi recusado por você com sucesso!"
										  		      + "</div>");
	}
	
	public ParametroGetVO getP_func_pedidos_ado_sim() {
		return p_func_pedidos_ado_sim;
	}

	public void setP_func_pedidos_ado_sim(ParametroGetVO p_func_pedidos_ado_sim) {
		this.p_func_pedidos_ado_sim = p_func_pedidos_ado_sim;
	}

	public ParametroGetVO getP_func_pedidos_ado_nao() {
		return p_func_pedidos_ado_nao;
	}

	public void setP_func_pedidos_ado_nao(ParametroGetVO p_func_pedidos_ado_nao) {
		this.p_func_pedidos_ado_nao = p_func_pedidos_ado_nao;
	}

	public ParametroGetVO getP_envioSolicit() {
		return p_envioSolicit;
	}

	public void setP_envioSolicit(ParametroGetVO p_envioSolicit) {
		this.p_envioSolicit = p_envioSolicit;
	}

	public String getSpanCaoDisponivel() {
		return spanCaoDisponivel;
	}

	public void setSpanCaoDisponivel(String spanCaoDisponivel) {
		this.spanCaoDisponivel = spanCaoDisponivel;
	}

	public String getSpanCaoNaoDisponivel() {
		return spanCaoNaoDisponivel;
	}

	public void setSpanCaoNaoDisponivel(String spanCaoNaoDisponivel) {
		this.spanCaoNaoDisponivel = spanCaoNaoDisponivel;
	}

	public String getAlertVerificaLoginFichaCao() {
		return alertVerificaLoginFichaCao;
	}

	public void setAlertVerificaLoginFichaCao(String alertVerificaLoginFichaCao) {
		this.alertVerificaLoginFichaCao = alertVerificaLoginFichaCao;
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

	public String getEncerraThumbnailsListaCaes() {
		return encerraThumbnailsListaCaes;
	}

	public void setEncerraThumbnailsListaCaes(String encerraThumbnailsListaCaes) {
		this.encerraThumbnailsListaCaes = encerraThumbnailsListaCaes;
	}
	
	public void redirecionaSeAnonimo() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		if (!this.controle_login.isSessaoAtiva()) {
			try {
				externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
			} catch (IOException e) {
				requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
			}
		}
	}
	
	public void redirecionaSeNaoAnonimo() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		if (this.controle_login.isSessaoAtiva()) {
			try {
				externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
			} catch (IOException e) {
				requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
			}
		}
	}
	
	public void redirecionaSeNaoAdmin() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		if (!this.controle_login.getTipoUsarioAtual().equals("admin")) {
			try {
				externalContext.redirect(externalContext.getRequestContextPath() + "/faces/index.xhtml");
			} catch (IOException e) {
				requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
			}
		}
	}
}
