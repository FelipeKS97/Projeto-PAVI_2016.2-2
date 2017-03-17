package pavi.melhoramigo.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;

import pavi.melhoramigo.dao.CaoDAO;
import pavi.melhoramigo.dao.SolicitacaoAdocaoDAO;
import pavi.melhoramigo.dao.conexao.ConexaoBase;
import pavi.melhoramigo.vo.CaoVO;
import pavi.melhoramigo.vo.SolicitacaoAdocaoVO;

@ManagedBean
public class CaoBean extends ConexaoBase{
	private CaoDAO caoDAO = new CaoDAO();
	private CaoVO caoVO = new CaoVO();
	private SolicitacaoAdocaoVO solicit = new SolicitacaoAdocaoVO();
	
	private List<CaoVO> listaCaes = new ArrayList<>();

	public List<CaoVO> getListaCaes() {
		return listaCaes;
	}

	public void setListaCaes(List<CaoVO> listaCaes) {
		this.listaCaes = listaCaes;
	}
	
	public SolicitacaoAdocaoVO getSolicit() {
		return solicit;
	}

	public void setSolicit(SolicitacaoAdocaoVO solicit) {
		this.solicit = solicit;
	}

	public CaoVO getCaoVO() {
		return caoVO;
	}

	public void setCaoVO(CaoVO caoVO) {
		this.caoVO = caoVO;
	}

	public void getCaesDisponiveis() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		try {
			this.listaCaes = caoDAO.listarPorStatus(this.getConexao(), 1);
		} catch (SQLException e) {
			requestContext.execute("alert('Erro ao procurar os cães: " + e.getMessage() + "');");
		}
	}
	
	public void getDadosCao() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		try {
			this.caoVO = caoDAO.buscaCao(this.getConexao(), this.caoVO.getId_cao());
		} catch (SQLException e) {
			requestContext.execute("alert('Erro ao buscar cão: " + e.getMessage() + "');");
		}
	}
	
	public void enviarSolicitacao() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		RequestContext requestContext = RequestContext.getCurrentInstance();	
		
		if (!externalContext.getSessionMap().containsKey("nivel_usuario")){
			requestContext.execute("alert('Por favor! Faça o login para fazer uma solicitação de adoção!');");
		} else if(this.caoVO.getStatus_disponivel() == 0) {
			requestContext.execute("alert('Este cão NÃO está disponível para adoção!');");
		} else {
			Calendar cal = Calendar.getInstance();
			
			this.solicit.setId_adotante((int)externalContext.getSessionMap().get("id_usuario"));
			this.solicit.setId_cao(this.caoVO.getId_cao());
			this.solicit.setData_solicit(cal.get(Calendar.YEAR) + "/" + (cal.get(Calendar.MONTH)+1) + "/" + cal.get(Calendar.DAY_OF_MONTH));
			
			try {
				SolicitacaoAdocaoDAO solicitDAO = new SolicitacaoAdocaoDAO();
				solicitDAO.criar(this.getConexao(), this.solicit);
				
				externalContext.redirect("ficha_cao.xhtml?id=" + this.caoVO.getId_cao() + "&envio=ok");
			} catch (SQLException e) { 
				requestContext.execute("alert('Erro ao enviar a solicitação: " + e.getMessage() + "');");
			} catch (IOException e) {
				requestContext.execute("alert('Erro ao redirecionar a página: " + e.getMessage() + "');");
			}
		}
	}
}
