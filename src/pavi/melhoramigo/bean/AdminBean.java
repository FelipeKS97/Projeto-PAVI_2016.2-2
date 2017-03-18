package pavi.melhoramigo.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

import pavi.melhoramigo.dao.SolicitacaoAdocaoDAO;
import pavi.melhoramigo.dao.conexao.ConexaoBase;
import pavi.melhoramigo.vo.SolicitacaoAdocaoVO;

@ManagedBean
public class AdminBean extends ConexaoBase {
	private List<SolicitacaoAdocaoVO> solicitacoes = new ArrayList<>();

	public List<SolicitacaoAdocaoVO> getSolicitacoes() {
		return solicitacoes;
	}

	public void setSolicitacoes(List<SolicitacaoAdocaoVO> solicitacoes) {
		this.solicitacoes = solicitacoes;
	}

	public void getSolicitacoesPendentes() {
		SolicitacaoAdocaoDAO solicitDAO = new SolicitacaoAdocaoDAO();
		
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		try {
			this.solicitacoes = solicitDAO.listarPorStatus(this.getConexao(), 0);
		} catch (SQLException e) {
			requestContext.execute("alert('Erro ao procurar as solicitações pendentes: " + e.getMessage() + "');");
		}
	}
	
}
