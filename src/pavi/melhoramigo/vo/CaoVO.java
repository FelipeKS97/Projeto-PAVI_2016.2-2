package pavi.melhoramigo.vo;

public class CaoVO {
	private int id_cao;
	private String nome;
	private String genero;
	private int idade;
	private String porte;
	private int status_disponivel;
	private String src_imagem;
	
	public int getId_cao() {
		return id_cao;
	}
	
	public void setId_cao(int id_cao) {
		this.id_cao = id_cao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	public String getPorte() {
		return porte;
	}
	
	public void setPorte(String porte) {
		this.porte = porte;
	}
	
	public int getStatus_disponivel() {
		return status_disponivel;
	}
	
	public void setStatus_disponivel(int status_disponivel) {
		this.status_disponivel = status_disponivel;
	}
	
	public String getSrc_imagem() {
		return src_imagem;
	}
	
	public void setSrc_imagem(String src_imagem) {
		this.src_imagem = src_imagem;
	}
}
