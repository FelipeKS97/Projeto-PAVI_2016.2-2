package pavi.melhoramigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pavi.melhoramigo.vo.EnderecoVO;

public class EnderecoDAO {
	public void criar (Connection conexao, int id_usuario, EnderecoVO endereco) throws SQLException {
		String sql = "insert into t_endereco (id_usuario, cep, rua, ncasa, bairro, complemento, cidade, estado) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, id_usuario);
			stmt.setString(2, endereco.getCep());
			stmt.setString(3, endereco.getRua());
			stmt.setString(4, endereco.getnCasa());
			stmt.setString(5, endereco.getBairro());
			stmt.setString(6, endereco.getComplemento());
			stmt.setString(7, endereco.getCidade());
			stmt.setString(8, endereco.getEstado());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Erro: "+e.getMessage());
		}
	}
	
	public EnderecoVO buscaEndereco (Connection conexao, int id_usuario) {		
		try (PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM t_endereco WHERE t_endereco.id_usuario = '" + id_usuario +"'");
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				EnderecoVO endereco = new EnderecoVO();
					
				endereco.setCep(rs.getString(2));
				endereco.setRua(rs.getString(3));
				endereco.setnCasa(rs.getString(4));
				endereco.setBairro(rs.getString(5));
				endereco.setComplemento(rs.getString(6));
				endereco.setCidade(rs.getString(7));
				endereco.setEstado(rs.getString(8));
					
				return endereco;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public void updateEndereco(Connection conexao, EnderecoVO endereco, int id_usuario) throws SQLException {
		String sql = "update t_endereco set cep='" + endereco.getCep() + "', rua='" + endereco.getRua() +"', ncasa='" + endereco.getnCasa() + "', bairro='" + endereco.getBairro() + "', complemento='" + endereco.getComplemento() + "', cidade='" + endereco.getCidade() + "', estado='" + endereco.getEstado() + "' where id_usuario='" + id_usuario + "'";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
}
