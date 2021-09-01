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
				Cartao cartao = (Cartao) cartao;
				PreparedStatement pst = null;
				StringBuilder sql = new StringBuilder();

				if (cartao.getCliente() != null) {
					sql.append("SELECT * "
							 + "FROM tb_cartao "
							 + "WHERE fk_cliente = ? ;");
				}

				try {
					openConnection();
					pst = connection.prepareStatement(sql.toString());
					
					if (cartao.getCliente().getId() != null) {
						pst.setInt(1, cartao.getCliente().getId());
					}
		            
					List<EntidadeDominio> celulares = new ArrayList<EntidadeDominio>();
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {

						cartao.setDtCadastro(rs.getDate("dt_cadastro"));
						cartao.setDtAlteracao(rs.getDate("dt_alteracao"));
						cartao.setId(rs.getInt("id"));
						cartao.setDescricao(rs.getString("descricao"));
						cartao.setAltura(rs.getString("altura"));
						cartao.setLargura(rs.getString("largura"));
						cartao.setComprimento(rs.getString("comprimento"));
						cartao.setCameraFrontal(rs.getString("camerafrontal"));
						cartao.setCameraTraseira(rs.getString("cameratraseira"));
						cartao.setComponentes(rs.getString("componentes"));
						cartao.setPeso(rs.getString("peso"));
						cartao.setPreco(rs.getDouble("preco"));
						cartao.setProcessador(rs.getString("processador"));
						cartao.setRam(rs.getString("ram"));
						cartao.setResolucao(rs.getString("resolucao"));
						cartao.setTamanhoTela(rs.getString("tamanhotela"));
						cartao.setTipoChip(rs.getString("tipochip"));
				        
						Marca marca = new Marca();
						marca.setId(rs.getInt("fk_marca"));
						List<EntidadeDominio> marcas = new ArrayList<EntidadeDominio>();
						MarcaDAO marcadao = new MarcaDAO();
						marcas = marcadao.consultar(marca);
						for (EntidadeDominio d : marcas) {
							cel.setMarca((Marca) d);
						}
						
						Cartao cartao = new Cartao();
						cartao.setIdcliente(cli.getId());
						List<IDominio> listcartao = new ArrayList<IDominio>();
						CartaoDao cartaodao = new CartaoDao();
						listcartao = cartaodao.listarFiltro(cartao);
						for (IDominio d : listcartao) {
							cli.getEndereList().add((Cartao) d);
						}
						
						cel.getMarca().setId("tb_marca.id");
						cel.getMarca().setNome("tb_marca.nome");
						cel.getSo().setId("tb_so.id");
						cel.getSo().setDescricao("tb_so.id");
						
						var.setId("id");
						var.getCelular().setId("tb_celular.id");
						var.getArmazenamento().setId("tb_armazenamento.id");
						var.getCor().setId("tb_cor.id");
						
						if (cel.getId() == var.getCelular().getId()) {
							cel.getVariedades().add(var);
						}
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
