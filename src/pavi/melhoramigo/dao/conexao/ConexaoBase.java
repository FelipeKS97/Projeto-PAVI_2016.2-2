package pavi.melhoramigo.dao.conexao;

import java.sql.Connection;

public abstract class ConexaoBase {
	private ConexaoMySQL mysql = new ConexaoMySQL();
	private Connection conexao;
	
	public ConexaoBase() {
		this.conexao = mysql.getConexao("jdbc:mysql", "localhost:3306", "melhor_amigo", "root", "");
	}
	
	public Connection getConexao() {
		return conexao;
	}
}
