package pavi.melhoramigo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pavi.melhoramigo.vo.SolicitacaoAdocaoVO;

public class SolicitacaoAdocaoDAO {
	public void criar(Connection conexao, SolicitacaoAdocaoVO solicit) throws SQLException {
		String sql = "insert into t_solicit_adocao (id_adotante, id_cao, mensagem, data_solicit) values (?, ?, ?, ?)";
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.setInt(1, solicit.getId_adotante());
			stmt.setInt(2, solicit.getId_cao());
			stmt.setString(3, solicit.getMensagem());
			stmt.setString(4, solicit.getData_solicit());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Erro: " + e.getMessage());
		}
	}
	
	public List<SolicitacaoAdocaoVO> selectListSolicitacoes(Connection conexao, String sql) throws SQLException{
		List<SolicitacaoAdocaoVO> solicitacoes = new ArrayList<>();
		
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			if(rs.next()) {
				do {
					SolicitacaoAdocaoVO solicitacao = new SolicitacaoAdocaoVO();
					
					solicitacao.setCod_solicitacao(rs.getInt("cod_solicit"));
					solicitacao.setId_adotante(rs.getInt("id_adotante"));
					solicitacao.setId_cao(rs.getInt("id_cao"));
					solicitacao.setMensagem(rs.getString("mensagem"));
					solicitacao.setStatus_apr(rs.getInt("status_aprovacao"));
					solicitacao.setData_solicit(rs.getString("data_solicit"));
					
					solicitacoes.add(solicitacao);
				} while (rs.next());
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao consultar: " + e.getMessage());
		}
		
		return solicitacoes;
	}
	
	public List<SolicitacaoAdocaoVO> listarPorStatus(Connection conexao, int status) throws SQLException {
		String sql = "SELECT * FROM t_solicit_adocao WHERE status_aprovacao = " + status;
		
		List<SolicitacaoAdocaoVO> solicitacoes = this.selectListSolicitacoes(conexao, sql);
		
		return solicitacoes;
	}
	
	/*public SolicitacaoAdocaoVO buscaSolicitacoes(Connection conexao, String sql) throws SQLException {
		try (PreparedStatement stmt = conexao.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			if (rs.first()) {
				SolicitacaoAdocaoVO solicit = new SolicitacaoAdocaoVO();
				
				solicit.setCod_solicitacao(rs.getInt("cod_solicit"));
				solicit.setId_adotante(rs.getInt("id_adotante"));
				solicit.setId_cao(rs.getInt("id_cao"));
				solicit.setMensagem(rs.getString("mensagem"));
				solicit.setStatus_apr(rs.getInt("status_aprovacao"));
				solicit.setData_solicit(rs.getString("data_solicit"));
				
				return solicit;
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao consultar: "+e.getMessage());
		}
		
		return null;
	}
	
	public SolicitacaoAdocaoVO buscaSolicitacoesPendentesUsuario(Connection conexao, int id_adotante) throws SQLException {
		String sql = "SELECT id_adotante, id_cao FROM t_solicit_adocao WHERE id_adotante = " + id_adotante + " and status_aprovacao = 0";
		
		SolicitacaoAdocaoVO solicit = this.buscaSolicitacoes(conexao, sql);
		
		return solicit;
	}*/
}
