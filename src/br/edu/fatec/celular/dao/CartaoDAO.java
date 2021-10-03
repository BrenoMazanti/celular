package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.Cartao;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.IDominio;

public class CartaoDAO extends AbstractDAO{
	public CartaoDAO() {
		super("tb_cartao", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) {
		
	}
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Cartao cartao = (Cartao) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		

		//if(cartao.getId() != null) {
		//	sql.append("AND id = ?");
		//}
		
		if (cartao.getCliente().getId() != null) {
			sql.append("SELECT * "
					 + "FROM tb_cartao "
					 + "WHERE ativo = true "
			         + "AND fk_cliente = ?");
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql.toString());
			
			//if(cartao.getId() != null) {
			//	pst.setInt(1, cartao.getId());
			//}
			
			if (cartao.getCliente().getId() != null) {
				pst.setInt(1, cartao.getCliente().getId());
			}
            
			List<EntidadeDominio> cartoes = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {

				cartao.setDtCadastro(rs.getDate("dt_cadastro"));
				cartao.setDtAlteracao(rs.getDate("dt_alteracao"));
				cartao.setId(rs.getInt("id"));
				cartao.setDescricao(rs.getString("descricao"));
				cartao.setNumero(rs.getString("numero"));
				cartao.setMes(rs.getString("mes"));
				cartao.setAno(rs.getString("ano"));
				cartao.setCodigo(rs.getString("codigo"));
				cartao.setNomeTitular(rs.getString("nome_titular"));
				cartao.setCpfTitular(rs.getString("cpf_titular"));
				cartao.getCliente().setId(rs.getInt("fk_cliente"));
				//cartao.getBandeira().setId(rs.getInt("fk_bandeira"));
				
				cartoes.add(cartao);
			}
			return cartoes;
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
		Celular celular = (Celular) entidade;
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
				Celular cel = new Celular();
				CelularVariedade var = new CelularVariedade();
				
				cel.setDtCadastro(rs.getDate("dt_cadastro"));
				cel.setDtAlteracao(rs.getDate("dt_alteracao"));
				cel.setId(rs.getInt("id"));
				cel.setDescricao(rs.getString("descricao"));
				cel.setAltura(rs.getString("altura"));
				cel.setLargura(rs.getString("largura"));
				cel.setComprimento(rs.getString("comprimento"));
				cel.setCameraFrontal(rs.getString("camerafrontal"));
				cel.setCameraTraseira(rs.getString("cameratraseira"));
				cel.setComponentes(rs.getString("componentes"));
				cel.setPeso(rs.getString("peso"));
				cel.setPreco(rs.getString("preco"));
				cel.setProcessador(rs.getString("processador"));
				cel.setRam(rs.getString("ram"));
				cel.setResolucao(rs.getString("resolucao"));
				cel.setTamanhoTela(rs.getString("tamanhotela"));
				cel.setTipoChip(rs.getString("tipochip"));
				
				cel.getMarca().setId("tb_marca.id");
				cel.getMarca().setNome("tb_marca.nome");
				cel.getSo().setId("tb_so.id");
				cel.getSo().setDescricao("tb_so.id");
				
				var.setId("id");
				var.getCelular().setId("tb_celular.id");
				var.getArmazenamento().setId("tb_armazenamento.id");
				var.getCor().setId("cor.id");
				
				if (cel.getId() == var.getCelular().getId()) {
					cel.getVariedades().add(var);
				}
				// cli.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?
                
				List<EntidadeDominio> variedades = new ArrayList<EntidadeDominio>();
				try {
					IDAO dao = new CelularVariedadeDAO ();
					variedades = dao.consultar(entidade);
				} catch (Exception e) { // era SQLException
					e.printStackTrace();
				}
				
				celulares.add(cel);
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
