package pavi.melhoramigo.bean;

import javax.faces.bean.ManagedBean;

import pavi.melhoramigo.vo.ParametroGetVO;

@ManagedBean
public class DetalhesPaginasBean {
	private ParametroGetVO p_cadastro = new ParametroGetVO();
	
	public DetalhesPaginasBean() {
		this.p_cadastro.setCod_para_inserir("<div class='alert alert-success fade in'>"
										  + "<button type='button' class='close' data-dismiss='alert'>×</button>"
								 		  + "<strong>Parabéns!</strong> Seu cadastro foi criado com sucesso!"
								  		  + "</div>");
	}
	
	public ParametroGetVO getP_cadastro() {		
		return p_cadastro;
	}

	public void setP_cadastro(ParametroGetVO p_cadastro) {		
		this.p_cadastro = p_cadastro;
	}
}
