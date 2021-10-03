package br.edu.fatec.celular.fachada;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.fatec.celular.dao.IDAO;
import br.edu.fatec.celular.dao.AdministradorDAO;
import br.edu.fatec.celular.dao.CartaoDAO;
import br.edu.fatec.celular.dao.ClienteDAO;
import br.edu.fatec.celular.dao.EnderecoDAO;
import br.edu.fatec.celular.dao.CelularDAO;
import br.edu.fatec.celular.dominio.Administrador;
import br.edu.fatec.celular.dominio.Cartao;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.Endereco;
import br.edu.fatec.celular.dominio.Celular;
import br.edu.fatec.celular.strategy.IStrategy;
import br.edu.fatec.celular.strategy.ValidadorClienteSenha;
import br.edu.fatec.celular.strategy.ValidadorClienteUnico;
import br.edu.fatec.celular.strategy.ValidadorDadosObrigatoriosCliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class Fachada implements IFachada {

	private Map<String, IDAO> daos;
	private Map<String, Map<String, List<IStrategy>>> rns;
	private Resultado resultado;

	public Fachada() {
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, Map<String, List<IStrategy>>>();

		ClienteDAO cliDAO = new ClienteDAO();
		AdministradorDAO admDAO = new AdministradorDAO();
		EnderecoDAO endDAO = new EnderecoDAO();
		CartaoDAO cartaoDAO = new CartaoDAO();
		CelularDAO celDAO = new CelularDAO();
		daos.put(Cliente.class.getName(), cliDAO);
		daos.put(Administrador.class.getName(), admDAO);
		daos.put(Endereco.class.getName(), endDAO);
		daos.put(Cartao.class.getName(), cartaoDAO);
		daos.put(Celular.class.getName(), celDAO);

		// REGRAS DE NEGOCIO Cliente
		ValidadorDadosObrigatoriosCliente vrDadosObrigatoriosCliente = new ValidadorDadosObrigatoriosCliente();
		ValidadorClienteUnico vrClienteUnico = new ValidadorClienteUnico();
		ValidadorClienteSenha vrClienteSenha = new ValidadorClienteSenha();

		List<IStrategy> rnsSalvarCliente = new ArrayList<IStrategy>();
		List<IStrategy> rnsAlterarCliente = new ArrayList<IStrategy>();
		List<IStrategy> rnsExcluirCliente = new ArrayList<IStrategy>();

		rnsSalvarCliente.add(vrDadosObrigatoriosCliente);
		rnsSalvarCliente.add(vrClienteUnico);
		rnsSalvarCliente.add(vrClienteSenha);
		
		rnsAlterarCliente.add(vrClienteSenha);
		// acrescentar cada regra de negócio referente ao seu tipo de ação (se for
		// salvar, alterar ou excluir terá regras diferentes)

		Map<String, List<IStrategy>> rnsCliente = new HashMap<String, List<IStrategy>>();
		rnsCliente.put("SALVAR", rnsSalvarCliente);
		rnsCliente.put("ALTERAR", rnsAlterarCliente);
		rnsCliente.put("EXCLUIR", rnsExcluirCliente);

		rns.put(Cliente.class.getName(), rnsCliente);
	}

	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();

		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);

		if (regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);

			if (regras != null) {
				for (IStrategy s : regras) {
					String m = s.processar(entidade);

					if (m != null) {
						msg.append(m);
						msg.append("\n");
					}
				}
			}

		}

		if (msg.length() > 0)
			return msg.toString();
		else
			return null;
	}

	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) { // era SQLException
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");

			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}

	public Resultado alterar(EntidadeDominio entidade) {

		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

	public Resultado excluir(EntidadeDominio entidade) {

		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

	public Resultado consultar(EntidadeDominio entidade) {

		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		
		String msg = executarRegras(entidade, "CONSULTAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
			
				resultado.setEntidades(dao.consultar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

	@Override
	public Resultado listar(EntidadeDominio entidade) {
		
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "LISTAR");

		if (msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
			
				resultado.setEntidades(dao.listar(entidade));
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar a consulta!");

			}
		} else {
			resultado.setMsg(msg);

		}

		return resultado;
	}

}
