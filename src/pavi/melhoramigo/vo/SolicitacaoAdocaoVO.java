package pavi.melhoramigo.vo;

public class SolicitacaoAdocaoVO {
	private int cod_solicitacao;
	private int id_adotante;
	private int id_cao;
	private String mensagem;
	private int status_apr;
	private String data_solicit;
	private String nome_adotante;
	private String nome_cao;
	
	public String getNome_adotante() {
		return nome_adotante;
	}

	public void setNome_adotante(String nome_adotante) {
		this.nome_adotante = nome_adotante;
	}

	public String getNome_cao() {
		return nome_cao;
	}

	public void setNome_cao(String nome_cao) {
		this.nome_cao = nome_cao;
	}

	public int getCod_solicitacao() {
		return cod_solicitacao;
	}
	
	public void setCod_solicitacao(int cod_solicitacao) {
		this.cod_solicitacao = cod_solicitacao;
	}
	
	public int getId_adotante() {
		return id_adotante;
	}
	
	public void setId_adotante(int id_adotante) {
		this.id_adotante = id_adotante;
	}
	
	public int getId_cao() {
		return id_cao;
	}
	
	public void setId_cao(int id_cao) {
		this.id_cao = id_cao;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public int getStatus_apr() {
		return status_apr;
	}
	
	public void setStatus_apr(int status_apr) {
		this.status_apr = status_apr;
	}
	
	public String getData_solicit() {
		return data_solicit;
	}
	
	public void setData_solicit(String data_solicit) {
		this.data_solicit = data_solicit;
	}
}
