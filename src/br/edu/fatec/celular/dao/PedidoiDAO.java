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
					+ ", preco_uni + qtde       AS totalitem "
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
		Pedido pedido = (Pedido) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		

		//if(pedido.getId() != null) {
		//	sql.append("AND id = ?");
		//}
		
		if (pedido.getCliente().getId() != null) {
			sql.append("SELECT * "
					 + "FROM tb_pedido "
					 + "WHERE ativo = true "
			         + "AND fk_cliente = ?");
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			//if(pedido.getId() != null) {
			//	pst.setInt(1, pedido.getId());
			//}
			
			if (pedido.getCliente().getId() != null) {
				pst.setInt(1, pedido.getCliente().getId());
			}
            
			List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				pedido.setDtCadastro(rs.getDate("dt_cadastro"));
				pedido.setDtAlteracao(rs.getDate("dt_alteracao"));
				pedido.setId(rs.getInt("fk_cliente"));
				pedido.getCliente().setId(rs.getInt("fk_cliente"));
				pedido.setPrecoTotal(rs.getDouble("preco_total"));
				
				Pedidoi pedidoi = new Pedidoi();
				PedidoiDAO pedidoidao = new PedidoiDAO();
				pedidoi.setPedido(pedido);
				List<Pedidoi> pedidois = new ArrayList<Pedidoi>();
				
				for (EntidadeDominio d : pedidoidao.consultar(pedidoi)) {
					if(d != null) {
						pedidois.add((Pedidoi) d);
					}
				}
				
				pedido.setItens(pedidois);
				
				pedidos.add(pedido);
			}
			return pedidos;
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
