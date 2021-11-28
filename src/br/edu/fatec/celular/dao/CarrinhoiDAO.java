package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.Endereco;
import br.edu.fatec.celular.dominio.Carrinho;
import br.edu.fatec.celular.dominio.Carrinhoi;
import br.edu.fatec.celular.dominio.Celular;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.IDominio;

public class CarrinhoiDAO extends AbstractDAO{
	public CarrinhoiDAO() {
		super("tb_carrinhoi", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		Carrinhoi carrinhoi = (Carrinhoi) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		try {
			connection.setAutoCommit(false);
			sql.append(
					"INSERT INTO tb_carrinhoi(dt_cadastro"
					+ ", dt_alteracao"
					+ ", preco_uni"
					+ ", qtde"
					+ ", fk_celular"
					+ ", fk_carrinho)");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?)"); // boolean ativo default true (ativo e inativo)

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			Timestamp time = new Timestamp(carrinhoi.getDtCadastro().getTime());
			pst.setTimestamp(1, time);
			pst.setTimestamp(2, time);
			pst.setDouble(3, carrinhoi.getPrecoUni());
			pst.setInt(4, carrinhoi.getQtde());
			pst.setInt(5, carrinhoi.getCelular().getId());
			pst.setInt(6, carrinhoi.getCarrinho().getId());
			
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if (rs.next())
			{
				id = rs.getInt(1);
				carrinhoi.setId(id);
			}
			connection.commit();

		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) {
		
	}
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Carrinhoi carrinhoi = (Carrinhoi) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		

		//if(carrinho.getId() != null) {
		//	sql.append("AND id = ?");
		//}
		
		if (carrinhoi.getCarrinho().getId() != null) {
			sql.append("SELECT tb_carrinhoi.* "
					+ ",tb_celular.descricao "
					+ ",tb_celular.foto "
					+ "FROM tb_carrinhoi "
					+ "JOIN tb_celular ON tb_celular.id = tb_carrinhoi.fk_celular "
					+ "WHERE ativo = true "
			        + "AND fk_carrinho = ?");
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			//if(carrinho.getId() != null) {
			//	pst.setInt(1, carrinho.getId());
			//}
			
			if (carrinhoi.getCarrinho().getId() != null) {
				pst.setInt(1, carrinhoi.getCarrinho().getId());
			}
            
			List<EntidadeDominio> carrinhois = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				carrinhoi.setDtCadastro(rs.getDate("dt_cadastro"));
				carrinhoi.setDtAlteracao(rs.getDate("dt_alteracao"));
				carrinhoi.setId(rs.getInt("id"));
				carrinhoi.setPrecoUni(rs.getDouble("preco_uni"));
				carrinhoi.setQtde(rs.getInt("qtde"));
				carrinhoi.getCelular().setId(rs.getInt("fk_celular"));
				carrinhoi.getCelular().setDescricao(rs.getString("descricao"));
				carrinhoi.getCelular().setFoto(rs.getString("foto"));
				carrinhoi.getCarrinho().setId(rs.getInt("fk_carrinho"));
				
				/*Celular celular = new Celular();
				CelularDAO celulardao = new CelularDAO();
				celular.setId(carrinhoi.getCelular().getId());
				List<Celular> celulares = new ArrayList<Celular>();
				
				for (EntidadeDominio d : celulardao.consultar(celular)) {
					celulares.add((Celular) d);
				}*/
				
				carrinhois.add(carrinhoi);
			}
			return carrinhois;
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
	
	@Override
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Cartao carrinho = (Cartao) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT tb_celular.* "
				+ ", tb_celular_variedade.*"
				 + ", tb_cor.*"
				 + ", tb_armazenamento.*"
				 + ", tb_marca.*"
				 + ", tb_so.*"
				 + "FROM tb_celular "
				 + "JOIN tb_celular_variedade ON tb_celular.id = tb_celular_variedade.fk_celular"
				 + "JOIN tb_cor ON tb_cor.id = tb_celular_variedade.fk_cor"
				 + "JOIN tb_armazenamento ON tb_armazenamento.id = tb_celular_variedade.fk_armazenamento "
				 + "JOIN tb_marca ON tb_marca.id = tb_celular.fk_marca ;");

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());

			List<EntidadeDominio> celulares = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//Cartao carrinho = new Cartao();
				//CelularVariedade var = new CelularVariedade();
				
				carrinho.setDtCadastro(rs.getDate("dt_cadastro"));
				carrinho.setDtAlteracao(rs.getDate("dt_alteracao"));
				carrinho.setId(rs.getInt("id"));
				
				// cli.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?
                
				List<EntidadeDominio> variedades = new ArrayList<EntidadeDominio>();
				try {
					IDAO dao = new CelularVariedadeDAO ();
					variedades = dao.consultar(entidade);
				} catch (Exception e) { // era SQLException
					e.printStackTrace();
				}
				
				celulares.add(carrinho);
			}
			return celulares;
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
	
	@Override
	public void excluir(EntidadeDominio entidade) {
		Carrinhoi carrinhoi = (Carrinhoi) entidade;

		openConnection();
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		if (carrinhoi.getId() != null)
			sql.append("DELETE FROM tb_carrinhoi WHERE id = ?");
		else
			sql.append("DELETE FROM tb_carrinhoi WHERE fk_carrinho = ?");
		

		try {
			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sql.toString());
			
			if (carrinhoi.getId() != null)
				pst.setInt(1, carrinhoi.getId());
			else
				pst.setInt(1, carrinhoi.getCarrinho().getId());

			pst.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
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
	}
}
