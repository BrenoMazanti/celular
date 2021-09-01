package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.Estoque;

public class EstoqueDAO extends AbstractDAO{
	public EstoqueDAO()
	{
		super("tb_estoque", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {

	}

	@Override
	public void alterar(EntidadeDominio entidade) {

	}

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		        Estoque est = (Estoque) entidade;
				PreparedStatement pst = null;
				StringBuilder sql = new StringBuilder();

				if (est.getId() != null) {
					sql.append("SELECT * "
							 + "FROM tb_estoque "
							 + "WHERE tb_estoque.fk_celular = ? "
							 + "AND ativo = true ;");
				}

				try {
					openConnection();
					pst = connection.prepareStatement(sql.toString());
					
					if (est.getCelular() != null) {
						pst.setInt(1, est.getCelular().getId);
					}
		            
					List<EntidadeDominio> ests = new ArrayList<EntidadeDominio>();
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						//So mar = new So();

						est.setDtCadastro(rs.getDate("dt_cadastro"));
						est.setDtAlteracao(rs.getDate("dt_alteracao"));
						est.getCelular().setId(rs.getInt("fk_celular"));
						est.setQtdeEstoque(rs.getInt("qtde_estoque"));
						est.setQtdeBloqueada(rs.getInt("qtde_bloqueada"));
						
						ests.add(est);
					}
					return ests;
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
		Estoque est = (Estoque) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * "
				 + "FROM tb_estoque "
				 + "AND ativo = true ;");

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());

			List<EntidadeDominio> ests = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//est = new Estoque();

				est.setDtCadastro(rs.getDate("dt_cadastro"));
				est.setDtAlteracao(rs.getDate("dt_alteracao"));
				est.getCelular().setId(rs.getInt("fk_celular"));
				est.setQtdeEstoque(rs.getInt("qtde_estoque"));
				est.setQtdeBloqueada(rs.getInt("qtde_estoque"));
				
				ests.add(est);
			}
			return ests;
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
