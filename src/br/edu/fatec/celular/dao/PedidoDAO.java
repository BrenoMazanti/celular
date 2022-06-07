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

public class PedidoDAO extends AbstractDAO{
	public PedidoDAO() {
		super("tb_pedido", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		openConnection();
		Pedido pedido = (Pedido) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
		
		try {
			
			connection.setAutoCommit(false);
			sql.append(
					"DELETE FROM tb_pedido WHERE fk_cliente = ? AND confirmado = false ;"
					) ;
			pst = connection.prepareStatement(sql.toString());
			
			pst.setInt(1, pedido.getCliente().getId());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			
		}
		
		try {
			connection.setAutoCommit(false);
			sql = new StringBuilder();
			sql.append("INSERT INTO tb_pedido(dt_cadastro"
					+ ", dt_alteracao"
					+ ", vlfrete"
					+ ", totalitens"
					+ ", fk_cliente"
					+ ", status_pedido"
					+ ", logradouro"
					+ ", numero"
					+ ", bairro"
					+ ", cep"
					+ ", cidade"
					+ ", uf"
					+ ", fk_endereco"
					+ ")"
					+ "SELECT now()             AS dt_cadastro"
					+ ", now()                  AS dt_alteracao"
					+ ", CASE"  
					+"		WHEN logradouro IS NOT NULL THEN "  
					+"			10 + round(CAST (random()*(10-5) AS NUMERIC),1) " 
					+"		ELSE"  
					+"			0 "  
					+"	 END AS vlfrete "
					+ ", preco_total            AS totalitens"
					+ ", tb_carrinho.fk_cliente AS fk_cliente"
					+ ", 1                      AS status_pedido "
					+ ", tb_endereco.logradouro AS logradouro"  
					+ ", tb_endereco.numero     AS numero"  
					+ ", tb_endereco.bairro     AS bairro"  
					+ ", tb_endereco.cep        AS cep"  
					+ ", tb_endereco.cidade     AS cidade"  
					+ ", tb_endereco.uf         AS uf "
					+ ", tb_endereco.id         AS fk_endereco "
					+ "FROM tb_carrinho "
					+ "LEFT JOIN tb_endereco ON tb_endereco.fk_cliente = tb_carrinho.fk_cliente AND tb_endereco.principal = true "
					+ "WHERE tb_carrinho.fk_cliente = ? ;");
			//sql.append(" VALUES (?, ?, ?, ?, ?, ?)"); // boolean ativo default true (ativo e inativo)

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			pst.setInt(1, pedido.getCliente().getId());
			
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if (rs.next())
			{
				id = rs.getInt(1);
				pedido.setId(id);
			}
			connection.commit();
			
			System.out.println(pedido.getId());
			
			Pedidoi pedidoi = new Pedidoi();
			PedidoiDAO pedidoidao = new PedidoiDAO();
			pedidoi.setPedido(pedido);
			
			pedidoidao.salvar(pedidoi);
			

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
		
		if (pedido.getCliente().getId() != null && pedido.getConfirmado() != null) {
			sql.append("SELECT * "
					 + "FROM tb_pedido "
					 + "WHERE ativo = true "
					 + "AND confirmado = ?"
			         + "AND fk_cliente = ?");
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			//if(pedido.getId() != null) {
			//	pst.setInt(1, pedido.getId());
			//}
			
			if (pedido.getCliente().getId() != null) {
				pst.setBoolean(1, pedido.getConfirmado());
				pst.setInt(2, pedido.getCliente().getId());
			}
            
			List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				pedido.setId(rs.getInt("id"));
				pedido.setDtCadastro(rs.getDate("dt_cadastro"));
				pedido.setDtAlteracao(rs.getDate("dt_alteracao"));
				pedido.getCliente().setId(rs.getInt("fk_cliente"));
				pedido.setValorFrete(rs.getDouble("vlfrete"));
				pedido.setTotalItens(rs.getDouble("totalitens"));
				pedido.setPrecoTotal(rs.getDouble("total"));
				
				pedido.getEndereco().setId(rs.getInt("fk_endereco"));
				pedido.getEndereco().setLogradouro(rs.getString("logradouro"));
				pedido.getEndereco().setNumero(rs.getString("numero"));
				pedido.getEndereco().setBairro(rs.getString("bairro"));
				pedido.getEndereco().setCep(rs.getString("cep"));
				pedido.getEndereco().setCidade(rs.getString("cidade"));
				pedido.getEndereco().setUf(rs.getString("uf"));
				
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
