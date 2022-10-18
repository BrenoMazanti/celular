package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.Celular;
import br.edu.fatec.celular.dominio.CelularVariedade;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.Marca;
import br.edu.fatec.celular.dominio.Endereco;
import br.edu.fatec.celular.dominio.IDominio;

public class CelularDAO extends AbstractDAO{
	public CelularDAO() {
		super("tb_celular", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) {
		
	}
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				Celular celular = (Celular) entidade;
				PreparedStatement pst = null;
				StringBuilder sql = new StringBuilder();
                
				sql.append("SELECT tb_celular.* "
						 + "FROM tb_celular "
						 + "WHERE true ");
				
				
				if (celular.getId() != null) {
					sql.append(" AND id = " + celular.getId());

				}
				
				if (celular.getDescricao() != null) {
					sql.append(" AND descricao = " + celular.getDescricao());

				}
                
				sql.append(";");
				System.out.println(sql);
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
						cel.setCameraFrontal(rs.getString("camera_frontal"));
						cel.setCameraTraseira(rs.getString("camera_traseira"));
						cel.setComponentes(rs.getString("componentes"));
						cel.setPeso(rs.getString("peso"));
						cel.setPreco(rs.getDouble("preco"));
						cel.setProcessador(rs.getString("processador"));
						cel.setRam(rs.getString("ram"));
						cel.setResolucao(rs.getString("resolucao"));
						cel.setTamanhoTela(rs.getString("tamanho_tela"));
						cel.setTipoChip(rs.getString("tipo_chip"));
						cel.setFoto(rs.getString("foto"));
				        
						// cli.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?
						
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
	
	@Override
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Celular celular = (Celular) entidade;
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		/*sql.append("SELECT tb_celular.* "
				+ ", tb_celular_variedade.*"
				 + ", tb_cor.*"
				 + ", tb_armazenamento.*"
				 + ", tb_marca.*"
				 + ", tb_so.*"
				 + "FROM tb_celular "
				 + "JOIN tb_celular_variedade ON tb_celular.id = tb_celular_variedade.fk_celular"
				 + "JOIN tb_cor ON tb_cor.id = tb_celular_variedade.fk_cor"
				 + "JOIN tb_armazenamento ON tb_armazenamento.id = tb_celular_variedade.fk_armazenamento "
				 + "JOIN tb_marca ON tb_marca.id = tb_celular.fk_marca ;");*/
        
		sql.append("SELECT tb_celular.* "
				+ "FROM tb_celular");
		
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
				cel.setCameraFrontal(rs.getString("camera_frontal"));
				cel.setCameraTraseira(rs.getString("camera_traseira"));
				cel.setComponentes(rs.getString("componentes"));
				cel.setPeso(rs.getString("peso"));
				cel.setPreco(rs.getDouble("preco"));
				cel.setProcessador(rs.getString("processador"));
				cel.setRam(rs.getString("ram"));
				cel.setResolucao(rs.getString("resolucao"));
				cel.setTamanhoTela(rs.getString("tamanho_tela"));
				cel.setTipoChip(rs.getString("tipo_chip"));
				cel.setFoto(rs.getString("foto"));
				//cel.setPrecicacao(rs.getInt("fk_precificacao"));
				
				/*cel.getMarca().setId("tb_marca.id");
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
				*/
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
