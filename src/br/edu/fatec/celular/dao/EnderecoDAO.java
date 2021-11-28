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
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dominio.IDominio;

public class EnderecoDAO extends AbstractDAO{
	public EnderecoDAO() {
		super("tb_endereco", "id");
	}
	
	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

				openConnection();
				PreparedStatement pst = null;
				Endereco endereco = (Endereco) entidade;

				try {
					connection.setAutoCommit(false);
					StringBuilder sql = new StringBuilder();
					sql.append(
							"INSERT INTO tb_endereco(dt_cadastro"
							+ ", dt_alteracao"
							+ ", descricao"
							+ ", logradouro"
							+ ", numero"
							+ ", bairro"
							+ ", complemento"
							+ ", cep"
							+ ", cidade"
							+ ", uf"
							+ ", principal"
							+ ", cobranca"
							+ ", fk_cliente)"); 
					sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); // boolean ativo default true (ativo e inativo)

					pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

					Timestamp time = new Timestamp(endereco.getDtCadastro().getTime());
					pst.setTimestamp(1, time);
					pst.setTimestamp(2, time);
					pst.setString(3, endereco.getDescricao());
					pst.setString(4, endereco.getLogradouro());
					pst.setString(5, endereco.getNumero());
					pst.setString(6, endereco.getComplemento());
					pst.setString(7, endereco.getBairro());
					pst.setString(8, endereco.getCep());
					pst.setString(9, endereco.getCidade());
					pst.setString(10, endereco.getUf());
					pst.setBoolean(11, endereco.getPrincipal());
					pst.setBoolean(12, endereco.getCobranca());
					pst.setInt(13, endereco.getCliente().getId());
					
					pst.executeUpdate();

					ResultSet rs = pst.getGeneratedKeys();
					int id = 0;
					if (rs.next())
					{
						id = rs.getInt(1);
						endereco.setId(id);
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
		
	}
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
				Endereco endereco = (Endereco) entidade;
				PreparedStatement pst = null;
				StringBuilder sql = new StringBuilder();

				if (endereco.getCliente().getId() != null) {
					sql.append("SELECT * "
							 + "FROM tb_endereco "
							 + "WHERE fk_cliente = ? AND ativo = true "
							 + "ORDER BY id;");
				}

				try {
					openConnection();
					pst = connection.prepareStatement(sql.toString());
					
					if (endereco.getCliente().getId() != null) {
						pst.setInt(1, endereco.getCliente().getId());
					}
		            
					List<EntidadeDominio> enderecos = new ArrayList<EntidadeDominio>();
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {

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
