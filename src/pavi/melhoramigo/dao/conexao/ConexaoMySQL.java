package pavi.melhoramigo.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

public class ConexaoMySQL implements IConexao {
	static Connection conexao;
	
	@Override
	public synchronized Connection getConexao(String baseDados, String enderecoBanco, String nomeBanco, String usuario,
			String senha) {
		conexao = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(baseDados + "://" + enderecoBanco + "/" + nomeBanco, usuario, senha);
		} catch (CommunicationsException e) {
			System.err.println("Erro: A comunicação com o banco de dados falhou!");
		} catch (ClassNotFoundException  e) {
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexao;
	}

	public void fecharConexao() {
		try {
			ConexaoMySQL.conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
