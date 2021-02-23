package br.edu.fatec.celular.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.edu.fatec.celular.dominio.Administrador;
import br.edu.fatec.celular.dominio.EntidadeDominio;

public class AdministradorDAO extends AbstractDAO {

	public AdministradorDAO() {
		super("tb_adm", "id");
	}

	@Override
	public void salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub

		openConnection();
		PreparedStatement pst = null;
		Administrador administrador = (Administrador) entidade;

		try {
			connection.setAutoCommit(false);
			StringBuilder sql = new StringBuilder();
			sql.append(
					"INSERT INTO tb_adm(dt_cadastro, dt_alteracao, email, senha, cpf, nome, dt_nascimento, sexo, telefone)");
			sql.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"); // boolean ativo default true (ativo e inativo)

			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);

			Timestamp time = new Timestamp(administrador.getDtCadastro().getTime());
			pst.setTimestamp(1, time);
			pst.setTimestamp(2, time);
			pst.setString(3, administrador.getEmail());
			pst.setString(4, administrador.getSenha());

			pst.executeUpdate();

			ResultSet rs = pst.getGeneratedKeys();
			int id = 0;
			if (rs.next())
				id = rs.getInt(1);
			administrador.setId(id);

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

	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) throws SQLException {
		// TODO Auto-generated method stub
		Administrador administrador = (Administrador) entidade;
		PreparedStatement pst = null;
		String sql = null;

		if (administrador.getId() != null) {
			sql = "SELECT * FROM tb_adm WHERE id = ?";
		} else if (administrador.getEmail() != null && administrador.getSenha() != null) {
			sql = "SELECT * FROM tb_adm WHERE email = ? AND senha = ? AND ativo = true";
		} else {
			sql = "SELECT * FROM tb_adm WHERE ativo = true";
		}

		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			if (administrador.getId() != null) {
				pst.setInt(1, administrador.getId());
			}

			else if (administrador.getEmail() != null && administrador.getSenha() != null) {
				pst.setString(1, administrador.getEmail());
				pst.setString(2, administrador.getSenha());
			}

			List<EntidadeDominio> adms = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Administrador adm = new Administrador();

				adm.setDtCadastro(rs.getDate("dt_cadastro"));
				adm.setDtAlteracao(rs.getDate("dt_alteracao"));
				adm.setId(rs.getInt("id"));
				adm.setEmail(rs.getString("email"));
				adm.setSenha(rs.getString("senha"));
				// adm.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?

				adms.add(adm);
			}
			return adms;
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
	public List<EntidadeDominio> listar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		Administrador administrador = (Administrador) entidade;
		PreparedStatement pst = null;
		String sql = null;

			sql = "SELECT * FROM tb_adm WHERE ativo = true";


		try {
			openConnection();
			pst = connection.prepareStatement(sql);

			List<EntidadeDominio> adms = new ArrayList<EntidadeDominio>();
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Administrador adm = new Administrador();

				adm.setDtCadastro(rs.getDate("dt_cadastro"));
				adm.setDtAlteracao(rs.getDate("dt_alteracao"));
				adm.setId(rs.getInt("id"));
				adm.setEmail(rs.getString("email"));
				adm.setSenha(rs.getString("senha"));
				// adm.setAtivo(rs.getBoolean("ativo")); TODO: Necessita ?

				adms.add(adm);
			}
			return adms;
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
