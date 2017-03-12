package pavi.melhoramigo.bean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;

import pavi.melhoramigo.dao.CaoDAO;
import pavi.melhoramigo.dao.conexao.ConexaoBase;
import pavi.melhoramigo.vo.CaoVO;

@ManagedBean
public class CaoBean extends ConexaoBase{
	CaoDAO caoDAO = new CaoDAO();
	List<CaoVO> listaCaes = new ArrayList<>();
	
	public List<CaoVO> getListaCaes() {
		return listaCaes;
	}

	public void setListaCaes(List<CaoVO> listaCaes) {
		this.listaCaes = listaCaes;
	}

	public void getCaesDisponiveis() {
		RequestContext requestContext = RequestContext.getCurrentInstance();
		
		try {
			this.listaCaes = caoDAO.listarPorStatus(this.getConexao(), 1);
		} catch (SQLException e) {
			requestContext.execute("alert('Erro ao procurar os cães: " + e.getMessage() + "');");
		}
	}
}
