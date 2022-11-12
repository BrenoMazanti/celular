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
import br.edu.fatec.celular.dominio.Pedido;
import br.edu.fatec.celular.dominio.Pedidoi;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.IDominio;
import br.edu.fatec.celular.dao.PedidoiDAO;

public class PedidoiDAO extends AbstractDAO{
	public PedidoiDAO() {
		super("tb_pedido", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		Pedidoi pedidoi = (Pedidoi) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		try {
			connection.setAutoCommit(false);
			sql.append(
					"INSERT INTO tb_pedidoi(dt_cadastro"
					+ ", dt_alteracao"
					+ ", preco_uni"
					+ ", qtde"
					+ ", totalitem"
					+ ", fk_celular"
					+ ", fk_pedido"
					+ ")"
					+ "SELECT now()             AS dt_cadastro"
					+ ", now()                  AS dt_alteracao"
					+ ", preco_uni              AS preco_uni"  
					+ ", qtde                   AS qtde"
					+ ", preco_uni * qtde       AS totalitem "
					+ ", fk_celular             AS fk_celular "
					+ ", ?                      AS fk_pedido "
					+ "FROM tb_carrinhoi "
					+ "WHERE fk_carrinho = ? ;");
			//sql.append(" VALUES (?, ?, ?, ?, ?, ?)"); // boolean ativo default true (ativo e inativo)

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			
			pst.setInt(1, pedidoi.getPedido().getId());
			pst.setInt(2, pedidoi.getPedido().getCliente().getId());
			
			
			pst.execute();

			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if (rs.next())
			{
				id = rs.getInt(1);
				pedidoi.setId(id);
			}
			connection.commit();
			
			System.out.println(pedidoi.getId());	

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
		Pedidoi pedidoi = (Pedidoi) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT tb_pedidoi.* "
				 + "     , tb_celular.* "
				 + " FROM tb_pedidoi "
				 + " JOIN tb_celular ON tb_celular.id = tb_pedidoi.fk_celular "
				 + " WHERE ativo = true ");
		
		if (pedidoi.getPedido().getId() != null) {
			sql.append(" AND fk_pedido = " + pedidoi.getPedido().getId());
		}

		sql.append(";");
		
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			//if(pedido.getId() != null) {
			//	pst.setInt(1, pedido.getId());
			//}
			
            
			List<EntidadeDominio> pedidois = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				pedidoi = new Pedidoi();
				
				pedidoi.setDtCadastro(rs.getDate("dt_cadastro"));
				pedidoi.setDtAlteracao(rs.getDate("dt_alteracao"));
				pedidoi.setId(rs.getInt("id"));
				pedidoi.getCelular().setId(rs.getInt("fk_celular"));
				pedidoi.getPedido().setId(rs.getInt("fk_pedido"));
				pedidoi.setPrecoUni(rs.getDouble("preco_uni"));
				pedidoi.setQtde(rs.getInt("qtde"));
				pedidoi.setTotalItem(rs.getDouble("totalitem"));
				pedidoi.getCelular().setDescricao(rs.getString("descricao"));
				pedidoi.getCelular().setFoto(rs.getString("foto"));
				
				pedidois.add(pedidoi);
				
			}
			return pedidois;
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
		Pedido pedido = (Pedido) entidade;
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
				//Cartao pedido = new Cartao();
				//CelularVariedade var = new CelularVariedade();
				
				pedido.setDtCadastro(rs.getDate("dt_cadastro"));
				pedido.setDtAlteracao(rs.getDate("dt_alteracao"));
				pedido.setId(rs.getInt("id"));
				
				// cli.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?
                
				List<EntidadeDominio> variedades = new ArrayList<EntidadeDominio>();
				try {
					IDAO dao = new CelularVariedadeDAO ();
					variedades = dao.consultar(entidade);
				} catch (Exception e) { // era SQLException
					e.printStackTrace();
				}
				
				celulares.add(pedido);
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
}
