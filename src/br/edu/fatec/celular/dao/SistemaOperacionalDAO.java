package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.IDominio;
import br.edu.fatec.celular.dominio.SistemaOperacional;

public class SistemaOperacionalDAO extends AbstractDAO{
	public SistemaOperacionalDAO()
	{
		super("tb_so", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {

	}

	@Override
	public void alterar(EntidadeDominio entidade) {

	}

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		        SistemaOperacional so = (SistemaOperacional) entidade;
				PreparedStatement pst = null;
				StringBuilder sql = new StringBuilder();

				if (so.getId() != null) {
					sql.append("SELECT * "
							 + "FROM tb_so "
							 + "WHERE tb_so.nome LIKE '%?%' OR tb_so.id = ? "
							 + "AND ativo = true ;");
				}

				try {
					openConnection();
					pst = connection.prepareStatement(sql.toString());
					
					if (so.getDescricao() != null || so.getId() != null) {
						pst.setString(1, so.getDescricao());
						pst.setInt(2, so.getId());
					}
		            
					List<EntidadeDominio> sos = new ArrayList<EntidadeDominio>();
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						//So mar = new So();

						so.setDtCadastro(rs.getDate("dt_cadastro"));
						so.setDtAlteracao(rs.getDate("dt_alteracao"));
						so.setId(rs.getInt("id"));
						so.setDescricao(rs.getString("nome"));
						
						sos.add(so);
					}
					return sos;
				}catch(

	SQLException e)
	{
		e.printStackTrace();
	}finally
	{

		try {
			pst.close();
			if (ctrlTransaction)
				connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// termino equivocado
	return null;
	}

	@Override
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		SistemaOperacional so = (SistemaOperacional) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * "
				 + "FROM tb_so "
				 + "WHERE tb_so.descricao LIKE '%?%' OR tb_so.id = ? "
				 + "AND ativo = true ;");

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());

			List<EntidadeDominio> sos = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//so = new So();

				so.setDtCadastro(rs.getDate("dt_cadastro"));
				so.setDtAlteracao(rs.getDate("dt_alteracao"));
				so.setId(rs.getInt("id"));
				so.setDescricao(rs.getString("descricao"));
				
				sos.add(so);
			}
			return sos;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			try {
				pst.close();
				if (ctrlTransaction)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// termino equivocado
		return null;
	}
}
