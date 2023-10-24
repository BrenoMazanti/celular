package br.edu.fatec.celular.dao;

import java.io.PrintStream;
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
import br.edu.fatec.celular.dominio.Pagamento;
import br.edu.fatec.celular.dao.PedidoiDAO;
import br.edu.fatec.celular.dao.PagamentoDAO;

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
					+ ", 0                      AS totalitens"
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
			
			//System.out.println(pedido.getId());
			
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
		// TODO Auto-generated method stub
		Pedido pedido = (Pedido) entidade;
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE tb_pedido SET ");
			
			List<Object> params = new ArrayList<>(); // lista para armazenar os parâmetros
	        
			// Verifica se foi preenchido
	        if (pedido.getConfirmado() != null) {
	            sql.append("confirmado = ?, ");
	            params.add(pedido.getConfirmado());
	        }
   
			sql.setLength(sql.length() - 2);

			sql.append(" WHERE id = ? ");
			params.add(pedido.getId());

			pst = connection.prepareStatement(sql.toString());

		    // Defina os valores dos campos a serem atualizados, na ordem correta.

		    for (int i = 0; i < params.size(); i++) {
	            Object param = params.get(i);
	            if (param instanceof Timestamp) {
	                pst.setTimestamp(i + 1, (Timestamp) param);
	            } else if (param instanceof Boolean) {
	                pst.setBoolean(i + 1, (Boolean) param);
	            } else if (param instanceof Integer){
	                pst.setInt(i + 1, (Integer) param);
	            } else {
	                pst.setString(i + 1, (String) param);
	            }
	            
	        }

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
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Pedido pedido = (Pedido) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		sql.append("SELECT * "
				 + "FROM tb_pedido "
				 + "WHERE true "
				 + "AND ativo = true ");
		
		if (pedido.getCliente().getId() != null) {
			sql.append( " AND fk_cliente = " + pedido.getCliente().getId());
		}

		if (pedido.getConfirmado() != null) {
			if (pedido.getConfirmado() == false) {
				sql.append(" AND confirmado = false ");
			}
			else {
				sql.append(" AND confirmado = true ");
			}
		}

		sql.append(";");
		//System.out.println(sql);
		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			//if(pedido.getId() != null) {
			//	pst.setInt(1, pedido.getId());
			//}
			
            
			List<EntidadeDominio> pedidos = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				
				pedido = new Pedido();
				
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
				
				//Itens do Pedido
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
				
				//Pagamentos
				Pagamento pagamento = new Pagamento();
				PagamentoDAO pagamentodao = new PagamentoDAO();
				pagamento.setPedido(pedido);
				List<Pagamento> pagamentos = new ArrayList<Pagamento>();
				for (EntidadeDominio d : pagamentodao.consultar(pagamento)) {
					if(d != null) {
						pagamentos.add((Pagamento) d);
					}
				}
				
				pedido.setPagamentos(pagamentos);
				
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
				
				pedido = new Pedido();
				
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
