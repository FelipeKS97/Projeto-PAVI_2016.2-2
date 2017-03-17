package pavi.melhoramigo.bo;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class ControleLoginBO {
	public String getPrimeiroNome_usuario() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		String nomeCompleto = (String)externalContext.getSessionMap().get("nome_usuario");
		String primeiroNome = "";

		for (int i = 0; i < nomeCompleto.length(); i++) {
			if (nomeCompleto.charAt(i) != ' ')
				primeiroNome = primeiroNome + nomeCompleto.charAt(i);
			else
				break;
		}

		return primeiroNome;
	}
	
	public String getTipoUsarioAtual() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		String tipoUsarioAtual = null;
		
		if (!externalContext.getSessionMap().containsKey("nivel_usuario")) {
			tipoUsarioAtual = "anonimo";
		} else if(externalContext.getSessionMap().get("nivel_usuario").equals(0)) {
			tipoUsarioAtual = "comum";
		} else if(externalContext.getSessionMap().get("nivel_usuario").equals(1)) {
			tipoUsarioAtual = "padrinho";			
		} else if(externalContext.getSessionMap().get("nivel_usuario").equals(2)) {
			tipoUsarioAtual = "voluntario";
		} else if(externalContext.getSessionMap().get("nivel_usuario").equals(3) || externalContext.getSessionMap().get("nivel_usuario").equals(4)) {
			tipoUsarioAtual = "admin";
		}
		
		return tipoUsarioAtual;
	}
	
	public boolean isSessaoAtiva() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		
		return externalContext.getSessionMap().containsKey("nivel_usuario");
	}
}
