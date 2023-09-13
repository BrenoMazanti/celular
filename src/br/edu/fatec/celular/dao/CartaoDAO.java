package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
		openConnection();
		PreparedStatement pst = null;
		Cartao cartao = (Cartao) entidade;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO tb_cartao(")
            .append("dt_cadastro,")
            .append("dt_alteracao,")
            .append("descricao,")
            .append("numero,")
            .append("mes,")
            .append("ano,")
            .append("codigo,")
            .append("nome_titular,")
            .append("cpf_titular,")
            .append("fk_cliente)")
            .append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

            Timestamp time = new Timestamp(cartao.getDtCadastro().getTime());
            pst.setTimestamp(1, time);
            pst.setTimestamp(2, time);
            pst.setString(3, cartao.getDescricao());
            pst.setString(4, cartao.getNumero());
            pst.setString(5, cartao.getMes());
            pst.setString(6, cartao.getAno());
            pst.setString(7, cartao.getCodigo());
            pst.setString(8, cartao.getNomeTitular());
            pst.setString(9, cartao.getCpfTitular());
            pst.setInt(10, cartao.getCliente().getId());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
                cartao.setId(id);
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
                if (pst != null) {
                    pst.close();
                }
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
			         + "AND fk_cliente = ? "
			         + "ORDER BY id");
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

				cartao = new Cartao();
				
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
				//cartao.getCliente().setId(rs.getInt("fk_cliente"));
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
		Cartao cartao = (Cartao) entidade;
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
				//Cartao cartao = new Cartao();
				//CelularVariedade var = new CelularVariedade();
				
				cartao.setDtCadastro(rs.getDate("dt_cadastro"));
				cartao.setDtAlteracao(rs.getDate("dt_alteracao"));
				cartao.setId(rs.getInt("id"));
				
				// cli.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?
                
				List<EntidadeDominio> variedades = new ArrayList<EntidadeDominio>();
				try {
					IDAO dao = new CelularVariedadeDAO ();
					variedades = dao.consultar(entidade);
				} catch (Exception e) { // era SQLException
					e.printStackTrace();
				}
				
				celulares.add(cartao);
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
