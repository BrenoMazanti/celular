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
import br.edu.fatec.celular.dominio.Marca;

public class MarcaDAO extends AbstractDAO{
	public MarcaDAO()
	{
		super("tb_marca", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {

	}

	@Override
	public void alterar(EntidadeDominio entidade) {

	}

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				Marca marca = (Marca) entidade;
				PreparedStatement pst = null;
				StringBuilder sql = new StringBuilder();

				if (marca.getId() != null) {
					sql.append("SELECT * "
							 + "FROM tb_marca "
							 + "WHERE tb_marca.descricao LIKE '%?%' OR tb_marca.id = ? "
							 + "AND ativo = true ;");
				}

				try {
					openConnection();
					pst = connection.prepareStatement(sql.toString());
					
					if (marca.getDescricao() != null || marca.getId() != null) {
						pst.setString(1, marca.getDescricao());
						pst.setInt(2, marca.getId());
					}
		            
					List<EntidadeDominio> marcas = new ArrayList<EntidadeDominio>();
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						Marca mar = new Marca();

						mar.setDtCadastro(rs.getDate("dt_cadastro"));
						mar.setDtAlteracao(rs.getDate("dt_alteracao"));
						mar.setId(rs.getInt("id"));
						mar.setDescricao(rs.getString("descricao"));
						
						marcas.add(mar);
					}
					return marcas;
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
		Marca marca = (Marca) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * "
				 + "FROM tb_marca "
				 + "WHERE tb_marca.descricao LIKE '%?%' OR tb_marca.id = ? "
				 + "AND ativo = true ;");

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());

			List<EntidadeDominio> marcas = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				marca = new Marca();

				marca.setDtCadastro(rs.getDate("dt_cadastro"));
				marca.setDtAlteracao(rs.getDate("dt_alteracao"));
				marca.setId(rs.getInt("id"));
				marca.setDescricao(rs.getString("descricao"));
				
				marcas.add(marca);
			}
			return marcas;
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
