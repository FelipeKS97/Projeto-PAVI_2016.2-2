package pavi.melhoramigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pavi.melhoramigo.vo.CaoVO;

public class CaoDAO {	
	public List<CaoVO> listarPorStatus(Connection conexao, int status) throws SQLException {
		String sql = "SELECT * FROM t_cao WHERE t_cao.status_disponivel = " + status;
		
		List<CaoVO> alunos = this.selectListCaes(conexao, sql);
		
		return alunos;
	}
	
	public List<CaoVO> selectListCaes(Connection conexao, String sql) throws SQLException{
		List<CaoVO> caes = new ArrayList<>();
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			if(rs.next()) {
				do {
					CaoVO cao = new CaoVO();
					
					cao.setId_cao(rs.getInt("id_cao"));
					cao.setNome(rs.getString("nome"));
					cao.setGenero(rs.getString("genero"));
					cao.setIdade(rs.getInt("idade"));
					cao.setPorte(rs.getString("porte"));
					cao.setStatus_disponivel(rs.getInt("status_disponivel"));
					cao.setSrc_imagem(rs.getString("src_imagem"));
					
					caes.add(cao);
				} while (rs.next());
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao consultar: " + e.getMessage());
		}
		
		return caes;
	}
	
	public CaoVO buscaCao(Connection conexao, int id_cao) throws SQLException {
		try (PreparedStatement stmt = conexao.prepareStatement("select * from t_cao where id_cao ='" + id_cao + "'");
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				CaoVO cao = new CaoVO();
				
				cao.setId_cao(rs.getInt("id_cao"));
				cao.setNome(rs.getString("nome"));
				cao.setGenero(rs.getString("genero"));
				cao.setIdade(rs.getInt("idade"));
				cao.setPorte(rs.getString("porte"));
				cao.setStatus_disponivel(rs.getInt("status_disponivel"));
				cao.setSrc_imagem(rs.getString("src_imagem"));
				
				return cao;
			}
		} catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
		
		return null;		
	}
}
