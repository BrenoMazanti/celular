package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.Endereco;
import br.edu.fatec.celular.dominio.Carrinho;
import br.edu.fatec.celular.dominio.Carrinhoi;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.IDominio;

public class CarrinhoDAO extends AbstractDAO{
	public CarrinhoDAO() {
		super("tb_carrinho", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		Carrinho carrinho = (Carrinho) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) {
		
	}
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Carrinho carrinho = (Carrinho) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		

		//if(carrinho.getId() != null) {
		//	sql.append("AND id = ?");
		//}
		
		if (carrinho.getCliente().getId() != null) {
			sql.append("SELECT * "
					 + "FROM tb_carrinho "
					 + "WHERE ativo = true "
			         + "AND fk_cliente = ?");
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			//if(carrinho.getId() != null) {
			//	pst.setInt(1, carrinho.getId());
			//}
			
			if (carrinho.getCliente().getId() != null) {
				pst.setInt(1, carrinho.getCliente().getId());
			}
            
			List<EntidadeDominio> carrinhos = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				carrinho = new Carrinho();
				
				carrinho.setDtCadastro(rs.getDate("dt_cadastro"));
				carrinho.setDtAlteracao(rs.getDate("dt_alteracao"));
				carrinho.setId(rs.getInt("fk_cliente"));
				carrinho.getCliente().setId(rs.getInt("fk_cliente"));
				carrinho.setPrecoTotal(rs.getDouble("preco_total"));
				
				Carrinhoi carrinhoi = new Carrinhoi();
				CarrinhoiDAO carrinhoidao = new CarrinhoiDAO();
				carrinhoi.setCarrinho(carrinho);
				List<Carrinhoi> carrinhois = new ArrayList<Carrinhoi>();
				
				for (EntidadeDominio d : carrinhoidao.consultar(carrinhoi)) {
					if(d != null) {
						System.out.println(d.getId());
						carrinhois.add((Carrinhoi) d);
					}
				}
				
				carrinho.setItens(carrinhois);
				
				carrinhos.add(carrinho);
			}
			return carrinhos;
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
		Carrinho carrinho = (Carrinho) entidade;
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
}
