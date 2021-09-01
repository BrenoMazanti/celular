package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.Celular;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.CelularVariedade;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.Cor;
import br.edu.fatec.celular.dominio.Armazenamento;

public class CelularVariedadeDAO extends AbstractDAO{
	public CelularVariedadeDAO() {
		super("tb_celular_variedade", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		
	}
	
	@Override
	public void alterar(EntidadeDominio entidade) {
		
	}
	
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
                Celular celular;
                CelularVariedade variedade;
		        if (entidade instanceof Celular) {
		        	celular = (Celular) entidade;
		        }
		        
		        else {
		            variedade = (CelularVariedade) entidade;
		        }
		        
				PreparedStatement pst = null;
				StringBuilder sql = new StringBuilder();

				if (celular.getId() != null) {
					sql.append("SELECT tb_celular_variedade.* "
							 + ", tb_cor.*"
							 + "FROM tb_celular_variedade "
							 + "JOIN tb_cor ON tb_cor.id = tb_celular_variedade.fk_cor"
							 + "JOIN tb_armazenamento ON tb_armazenamento.id = tb_celular_variedade.fk_armazenamento "
							 + "WHERE fk_celular = ?");
				}
				else if (variedade.getId() != null){
					sql = "SELECT * FROM tb_celular_variedade WHERE id = ?";
				}

				try {
					openConnection();
					pst = connection.prepareStatement(sql);
					
					List<EntidadeDominio> variedades = new ArrayList<EntidadeDominio>();
					
					if (celular.getId() != null) {
					    pst.setInt(1, celular.getId());
						
						List<EntidadeDominio> variedades = new ArrayList<EntidadeDominio>();
						
						ResultSet rs = pst.executeQuery();
						while (rs.next()) {
							Variedade var = new Variedade();

							var.setDtCadastro(rs.getDate("dt_cadastro"));
							var.setDtAlteracao(rs.getDate("dt_alteracao"));
							//var.getCelular.setid(rs.getInt("id"));
	                        var.getArmazenamento.setId(rs.getInt("fk_armazenamento"));
	                        var.getCor.setId(rs.getInt("fk_cor"));
							// cli.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?

							variedade.add(var);
						}
						return celulares;
					}

					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						Celular cel = new Celular();

						cel.setDtCadastro(rs.getDate("dt_cadastro"));
						cel.setDtAlteracao(rs.getDate("dt_alteracao"));
						cel.setCelular.(rs.getInt("id"));
                        cel.setArmaze
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
		String sql = null;

		sql = "SELECT * FROM tb_celular WHERE ativo = true";

		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			List<EntidadeDominio> celulares = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Celular cel = new Celular();

				cel.setDtCadastro(rs.getDate("dt_cadastro"));
				cel.setDtAlteracao(rs.getDate("dt_alteracao"));
				cel.setId(rs.getInt("id"));
				cel.setDescricao(rs.getString("descricao"));
				cel.set
				cel.setAltura(rs.getString("altura"));
				cel.setLargura(rs.getString("largura"));
				cel.setComprimento(rs.getString("comprimento"));
				cel.setCameraFrontal(rs.getString("camerafrontal"));
				cel.setCameraTraseira(rs.getString("cameratraseira"));
				cel.setComponentes(rs.getString("componentes"));
				cel.setMarca(rs.getString("marca"));
				cel.setPeso(rs.getString("peso"));
				cel.setPreco(rs.getString("preco"));
				cel.setProcessador(rs.getString("processador"));
				cel.setRam(rs.getString("ram"));
				cel.setResolucao(rs.getString("resolucao"));
				cel.setSo(rs.getString("so"));
				cel.setTamanhoTela(rs.getString("tamanhotela"));
				cel.setTipoChip(rs.getString("tipochip"));
				
				List<EntidadeDominio> variedades = new ArrayList<EntidadeDominio>();
				try {
					IDAO dao = new CelularVariedadeDAO ();
					variedades = dao.listar(entidade);
				} catch (Exception e) { // era SQLException
					e.printStackTrace();
				}
				cel.setCores(variedades.getCores());
				cel.setArmazenamentos(variedades.getArmazenamentos());
				
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
