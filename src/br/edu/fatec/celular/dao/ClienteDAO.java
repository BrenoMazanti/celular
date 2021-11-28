package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.dao.EnderecoDAO;
import br.edu.fatec.celular.dominio.Endereco;
import br.edu.fatec.celular.dao.CartaoDAO;
import br.edu.fatec.celular.dominio.Cartao;

public class ClienteDAO extends AbstractDAO {

	public ClienteDAO() {
		super("tb_cliente", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

		openConnection();
		PreparedStatement pst = null;
		Cliente cliente = (Cliente) entidade;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO tb_cliente(dt_cadastro, dt_alteracao, email, senha, cpf, nome, dt_nascimento, sexo, telefone, celular)");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); // boolean ativo default true (ativo e inativo)

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			Timestamp time = new Timestamp(cliente.getDtCadastro().getTime());
			pst.setTimestamp(1, time);
			pst.setTimestamp(2, time);
			pst.setString(3, cliente.getEmail());
			pst.setString(4, cliente.getSenha());
			pst.setString(5, cliente.getCpf());
			pst.setString(6, cliente.getNome());
			pst.setString(7, cliente.getDataNascimento());
			pst.setString(8, cliente.getSexo());
			pst.setString(9, cliente.getTelefone());
			pst.setString(10, cliente.getCelular());
			
			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if (rs.next())
			{
				id = rs.getInt(1);
				cliente.setId(id);
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
		// TODO Auto-generated method stub
		Cliente cliente = (Cliente) entidade;
		PreparedStatement pst = null;
		openConnection();

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append(
					"UPDATE tb_cliente SET dt_alteracao = ?, senha = ?, cpf = ?, nome = ?, dt_nascimento = ?,"
							+ "sexo = ?, telefone = ?, celular = ?");
			sql.append("WHERE id = ?");

			pst = connection.prepareStatement(sql.toString());

			Timestamp time = new Timestamp(cliente.getDtAlteracao().getTime());
			pst.setTimestamp(1, time);
			pst.setString(2, cliente.getSenha());
			pst.setString(3, cliente.getCpf());
			pst.setString(4, cliente.getNome());
			pst.setString(5, cliente.getDataNascimento());
			pst.setString(6, cliente.getSexo());
			pst.setString(7, cliente.getTelefone());
			pst.setString(8, cliente.getCelular());
			pst.setInt(9, cliente.getId());
			
			//N�o ser� cadastrado email e CPF
			//pst.setString(3, cliente.getEmail());
			//pst.setString(3, cliente.getEmail());

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

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Cliente cliente = (Cliente) entidade;
		PreparedStatement pst = null;
		String sql = null;

		if (cliente.getId() != null) {
			sql = "SELECT * FROM tb_cliente WHERE nome LIKE '%?%' OR id = ?";
		} else if (cliente.getEmail() != null && cliente.getCpf() != null) {
			sql = "SELECT * FROM tb_cliente WHERE email = ? OR cpf = ? AND ativo = true";
		} else if (cliente.getEmail() != null && cliente.getSenha() != null) {
			sql = "SELECT * FROM tb_cliente WHERE email = ? AND senha = ? AND ativo = true";
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql);
			
			if (cliente.getNome() != null && cliente.getId() != null) {
				pst.setString(1, cliente.getNome());
				pst.setInt(2, cliente.getId());
			}
            
			else if (cliente.getEmail() != null && cliente.getCpf() != null) {
				pst.setString(1, cliente.getEmail());
				pst.setString(2, cliente.getCpf());
			}
			
			else if (cliente.getEmail() != null && cliente.getSenha() != null) {
				pst.setString(1, cliente.getEmail());
				pst.setString(2, cliente.getSenha());
			}

			List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente cli = new Cliente();

				cli.setDtCadastro(rs.getDate("dt_cadastro"));
				cli.setDtAlteracao(rs.getDate("dt_alteracao"));
				cli.setId(rs.getInt("id"));
				cli.setEmail(rs.getString("email"));
				cli.setSenha(rs.getString("senha"));
				cli.setCpf(rs.getString("cpf"));
				cli.setNome(rs.getString("nome"));
				cli.setDataNascimento(rs.getString("dt_nascimento"));
				cli.setSexo(rs.getString("sexo"));
				cli.setTelefone(rs.getString("telefone"));
				cli.setCelular(rs.getString("celular"));
				// cli.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?
				
				/*Endereco endereco = new Endereco();
				EnderecoDAO enderecodao = new EnderecoDAO();
				endereco.setCliente(cli);
				List<Endereco> enderecos = new ArrayList<Endereco>();
				
				for (EntidadeDominio d : enderecodao.consultar(endereco)) {
						enderecos.add((Endereco) d);
				}
				
				cli.setEnderecos(enderecos);
				
				/*Cartao cartao = new Cartao();
				cartao.setCliente(cli);
				List<EntidadeDominio> listcartoes = new ArrayList<EntidadeDominio>();
				CartaoDAO cartaodao = new CartaoDAO();
				listcartoes = cartaodao.consultar(cartao);
				for (EntidadeDominio d : listcartoes) {
					cli.getCartoes().add((Cartao) d);
				}*/
				
				clientes.add(cli);
			}
			return clientes;
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
	public void excluir(EntidadeDominio entidade) {
		Cliente cliente = (Cliente) entidade;

		openConnection();
		PreparedStatement pst = null;
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE tb_cliente SET ativo = FALSE WHERE id = ?");

		try {
			connection.setAutoCommit(false);
			pst = connection.prepareStatement(sql.toString());
			pst.setInt(1, cliente.getId());

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
				if (ctrlTransaction)
					connection.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<EntidadeDominio> listar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Cliente cliente = (Cliente) entidade;
		PreparedStatement pst = null;
		String sql = null;

		sql = "SELECT * FROM tb_cliente WHERE ativo = true";

		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			List<EntidadeDominio> clientes = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente cli = new Cliente();

				cli.setDtCadastro(rs.getDate("dt_cadastro"));
				cli.setDtAlteracao(rs.getDate("dt_alteracao"));
				cli.setId(rs.getInt("id"));
				cli.setEmail(rs.getString("email"));
				cli.setSenha(rs.getString("senha"));
				cli.setCpf(rs.getString("cpf"));
				cli.setNome(rs.getString("nome"));
				cli.setDataNascimento(rs.getString("dt_nascimento"));
				cli.setSexo(rs.getString("sexo"));
				cli.setTelefone(rs.getString("telefone"));
				cli.setCelular(rs.getString("celular"));
				cli.setAtivo(rs.getBoolean("ativo"));

				clientes.add(cli);
			}
			return clientes;
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
