package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.Pagamento;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.IDominio;

public class PagamentoDAO extends AbstractDAO{
	public PagamentoDAO() {
		super("tb_pagamento", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

				openConnection();
				PreparedStatement pst = null;
				Pagamento pagamento = (Pagamento) entidade;

				try {
					connection.setAutoCommit(false);
					StringBuilder sql = new StringBuilder();
					
					sql.append("INSERT INTO tb_pagamento( ");
					
					List<Object> params = new ArrayList<>(); // lista para armazenar os par�metros
			        
					
					sql.append(
							"INSERT INTO tb_pagamento( dt_cadastro"
                            + ", dt_alteracao"
                            + ", fk_pedido"
                            + ", fk_cartao"
                            + ", fk_cupom"
                            + ", qtdeparcelas"
                            + ", vlparcela"
                            + ", vltotal)"); 
					sql.append(" VALUES ("); // boolean ativo default true (ativo e inativo)
					
					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

					
					if (pagamento.getDtCadastro() != null) {
			            sql.append("?, ");
			            params.add(new Timestamp(pagamento.getDtCadastro().getTime()));
			        }
					
					if (pagamento.getDtAlteracao() != null) {
			            sql.append("?, ");
			            params.add(new Timestamp(pagamento.getDtAlteracao().getTime()));
			        }
										
					if (pagamento.getPedido() != null && pagamento.getPedido().getId() != null) {
					    sql.append("?, ");
					    params.add(pagamento.getPedido().getId());
					}

					if (pagamento.getCartao() != null && pagamento.getCartao().getId() != null) {
					    sql.append("?, ");
					    params.add(pagamento.getCartao().getId());
					}

					if (pagamento.getCupom() != null && pagamento.getCupom().getId() != null) {
					    sql.append("?, ");
					    params.add(pagamento.getCupom().getId());
					}


					if (pagamento.getQtdeParcelas() != null) {
					    sql.append("?, ");
					    params.add(pagamento.getQtdeParcelas());
					}

					if (pagamento.getVlParcela() != null) {
					    sql.append("?, ");
					    params.add(pagamento.getVlParcela());
					}

					if (pagamento.getVlTotal() != null) {
					    sql.append("?, ");
					    params.add(pagamento.getVlTotal());
					}
					
					sql.setLength(sql.length() - 2); // remove a �ltima v�rgula e o espa�o
					System.out.println(params.get(0));
					sql.append(")");
					System.out.println(params.size());
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

					ResultSet rs = pst.getGeneratedKeys();
					int id = 0;
					if (rs.next())
					{
						id = rs.getInt(1);
						pagamento.setId(id);
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
		openConnection();
		PreparedStatement pst = null;
		Endereco endereco = (Endereco) entidade;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE tb_endereco SET ");
	        
	        List<Object> params = new ArrayList<>(); // lista para armazenar os par�metros
	        
	        if (endereco.getDtAlteracao() != null) {
	            sql.append("dt_alteracao = ?, ");
	            params.add(new Timestamp(endereco.getDtAlteracao().getTime()));
	        }
	        
	        if (endereco.getDescricao() != null) {
	            sql.append("descricao = ?, ");
	            params.add(endereco.getDescricao());
	        }
	        
	        if (endereco.getLogradouro() != null) {
	            sql.append("logradouro = ?, ");
	            params.add(endereco.getLogradouro());
	        }
	        
	        if (endereco.getNumero() != null) {
	            sql.append("numero = ?, ");
	            params.add(endereco.getNumero());
	        }
	        
	        if (endereco.getBairro() != null) {
	            sql.append("bairro = ?, ");
	            params.add(endereco.getBairro());
	        }
	        
	        if (endereco.getComplemento() != null) {
	            sql.append("complemento = ?, ");
	            params.add(endereco.getComplemento());
	        }
	        
	        if (endereco.getCep() != null) {
	            sql.append("cep = ?, ");
	            params.add(endereco.getCep());
	        }
	        
	        if (endereco.getCidade() != null) {
	            sql.append("cidade = ?, ");
	            params.add(endereco.getCidade());
	        }
	        
	        if (endereco.getUf() != null) {
	            sql.append("uf = ?, ");
	            params.add(endereco.getUf());
	        }
	        
	        if (endereco.getPrincipal() != null) {
	            sql.append("principal = ?, ");
	            params.add(endereco.getPrincipal());
	        }
	        
	        if (endereco.getCobranca() != null) {
	            sql.append("cobranca = ?, ");
	            params.add(endereco.getCobranca());
	        }
	        
	        sql.setLength(sql.length() - 2); // remove a �ltima v�rgula e o espa�o
	        
	        sql.append(" WHERE id = ?");
	        params.add(endereco.getId());
	        
	        pst = connection.prepareStatement(sql.toString());
	        
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
				Endereco endereco = (Endereco) entidade;
				PreparedStatement pst = null;
				StringBuilder sql = new StringBuilder();
				
				sql.append("SELECT * "
						 + "FROM tb_endereco "
						 + "WHERE true "
						 + "AND ativo = true ");
				
				if (endereco.getId() != null) {
					sql.append(" AND id = " + endereco.getId());
				}

				if (endereco.getCliente().getId() != null) {
					sql.append(" AND fk_cliente = " + endereco.getCliente().getId());
				}

				try {
					openConnection();
					pst = connection.prepareStatement(sql.toString());
					
					/*if (endereco.getCliente().getId() != null) {
						pst.setInt(1, endereco.getCliente().getId());
					}*/
		            
					List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						
						endereco = new Endereco();
						
						endereco.setDtCadastro(rs.getDate("dt_cadastro"));
						endereco.setDtAlteracao(rs.getDate("dt_alteracao"));
						endereco.setId(rs.getInt("id"));
						endereco.setDescricao(rs.getString("descricao"));
						endereco.setLogradouro(rs.getString("logradouro"));
						endereco.setNumero(rs.getString("numero"));
						endereco.setComplemento(rs.getString("complemento"));
						endereco.setBairro(rs.getString("bairro"));
						endereco.setCep(rs.getString("cep"));
						endereco.setCidade(rs.getString("cidade"));
						endereco.setUf(rs.getString("uf"));
						endereco.setPrincipal(rs.getBoolean("principal"));
						endereco.setCobranca(rs.getBoolean("cobranca"));
						endereco.getCliente().setId(rs.getInt("fk_cliente"));
				   
						//System.out.println(endereco.getId());
						//System.out.println(endereco.getDescricao());
						// cli.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?
						
						enderecos.add(endereco);
					}
					return enderecos;
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