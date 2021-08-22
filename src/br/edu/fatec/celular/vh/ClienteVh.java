package br.edu.fatec.celular.vh;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.vh.IViewHelper;
import javafx.scene.control.Alert;

public class ClienteVh implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest req) {
		// TODO Auto-generated method stub
		String operacao = req.getParameter("operacao");
		Cliente cli = new Cliente();
		Date dtCadastro;
		Calendar calendar;
		String email;
		String senha;
		String confirmarSenha;
		String cpf;
		String nome;
		String dataNascimento;
		String sexo;
		String telefone;
		String celular;

		if (operacao != null && operacao.equals("SALVAR")) {

			calendar = Calendar.getInstance();
			dtCadastro = calendar.getTime();

			if (req.getParameter("txtEmail") != null && !req.getParameter("txtEmail").equals(""))
				email = req.getParameter("txtEmail");
			else
				email = "";

			if (req.getParameter("txtSenha") != null && !req.getParameter("txtSenha").equals(""))
				senha = req.getParameter("txtSenha");
			else
				senha = "";

			if (req.getParameter("txtConfirmarSenha") != null && !req.getParameter("txtConfirmarSenha").equals(""))
				confirmarSenha = req.getParameter("txtConfirmarSenha");
			else
				confirmarSenha = "";

			if (req.getParameter("txtCpf") != null && !req.getParameter("txtCpf").equals(""))
				cpf = req.getParameter("txtCpf");
			else
				cpf = "";

			if (req.getParameter("txtNome") != null && !req.getParameter("txtNome").equals(""))
				nome = req.getParameter("txtNome");
			else
				nome = "";

			if (req.getParameter("txtDataNascimento") != null && !req.getParameter("txtDataNascimento").equals(""))
				dataNascimento = req.getParameter("txtDataNascimento");
			else
				dataNascimento = "";

			if (req.getParameter("txtSexo") != null && !req.getParameter("txtSexo").equals(""))
				sexo = req.getParameter("txtSexo");
			else
				sexo = "";

			if (req.getParameter("txtTelefone") != null && !req.getParameter("txtTelefone").equals(""))
				telefone = req.getParameter("txtTelefone");
			else
				telefone = "";
			
			if (req.getParameter("txtCelular") != null && !req.getParameter("txtCelular").equals(""))
				celular = req.getParameter("txtCelular");
			else
				celular = "";

			cli.setDtCadastro(dtCadastro);
			cli.setEmail(email);
			cli.setSenha(senha);
			cli.setConfirmarSenha(confirmarSenha);
			cli.setCpf(cpf);
			cli.setNome(nome.toUpperCase());
			cli.setDataNascimento(dataNascimento);
			cli.setSexo(sexo);
			cli.setTelefone(telefone);
			cli.setCelular(celular);
		}

		else if (operacao != null && operacao.equals("LOGIN")) {
			if (req.getParameter("txtEmail") != null && !req.getParameter("txtEmail").equals(""))
				email = req.getParameter("txtEmail");
			else
				email = "";

			if (req.getParameter("txtSenha") != null && !req.getParameter("txtSenha").equals(""))
				senha = req.getParameter("txtSenha");
			else
				senha = "";

			cli.setEmail(email);
			cli.setSenha(senha);
		}
		
		else if (operacao != null && operacao.equals("ALTERAR")) {
			cli = (Cliente) req.getSession().getAttribute("cliente");

			//Não poderá alterar CPF e email
			//if (req.getParameter("txtEmail") != null && !req.getParameter("txtEmail").equals(""))
				//cli.setEmail(req.getParameter("txtEmail"));

			if (req.getParameter("txtSenha") != null && !req.getParameter("txtSenha").equals(""))
				cli.setSenha(req.getParameter("txtSenha"));

			if (req.getParameter("txtConfirmarSenha") != null && !req.getParameter("txtConfirmarSenha").equals(""))
				cli.setConfirmarSenha(req.getParameter("txtConfirmarSenha"));
			
			//Não poderá alterar CPF e email
			//if (req.getParameter("txtCpf") != null && !req.getParameter("txtCpf").equals(""))
				//cli.setCpf(req.getParameter("txtCpf"));

			if (req.getParameter("txtNome") != null && !req.getParameter("txtNome").equals(""))
				cli.setNome(req.getParameter("txtNome"));

			if (req.getParameter("txtDataNascimento") != null && !req.getParameter("txtDataNascimento").equals(""))
				cli.setDataNascimento(req.getParameter("txtDataNascimento"));

			if (req.getParameter("txtSexo") != null && !req.getParameter("txtSexo").equals(""))
				cli.setSexo(req.getParameter("txtSexo"));

			if (req.getParameter("txtTelefone") != null && !req.getParameter("txtTelefone").equals(""))
				cli.setTelefone(req.getParameter("txtTelefone"));

			if (req.getParameter("txtCelular") != null && !req.getParameter("txtCelular").equals(""))
				cli.setCelular(req.getParameter("txtCelular"));
		}

		/*else {
			HttpSession session = req.getSession();
			Resultado resultado = (Resultado) session.getAttribute("resultado");
			String txtId = req.getParameter("txtId");
			int id = 0;

			if (txtId != null && !txtId.trim().equals("")) {
				id = Integer.parseInt(txtId);
			}

			for (EntidadeDominio e : resultado.getEntidades()) {
				if (e.getId() == id) {
					cli = (Cliente) e;
				}
			}
		}*/
		return cli;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;

		String operacao = req.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Cliente cadastrado com sucesso!");
				req.setAttribute("resultado", resultado.getMsg());
				req.getSession().setAttribute("cliente", resultado.getEntidades().get(0));
				d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
			}
			if (operacao.equals("LISTAR")) {
					if (!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
						req.setAttribute("clientes", resultado.getEntidades());
						d = req.getRequestDispatcher("admconsultarcliente.jsp");
					} else {
						resultado.setMsg("Cliente não encontrado!");
						req.setAttribute("resultado", resultado.getMsg());
						d = req.getRequestDispatcher("admconsultarcliente.jsp");
					}
				}
				
				else if (operacao.equals("LOGIN")) {
					if (!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
						req.getSession().setAttribute("cliente", resultado.getEntidades().get(0));
						d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
					} else {
						resultado.setMsg("Cliente_não_encontrado!");
						req.setAttribute("resultado", resultado.getMsg());
						d = req.getRequestDispatcher("telalogin.jsp");
					}
				}
			
				else if (operacao.equals("LOGIN")) {
					if (!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
						req.getSession().setAttribute("cliente", resultado.getEntidades().get(0));
						d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
					} else {
						resultado.setMsg("Cliente_não_encontrado!");
						req.setAttribute("resultado", resultado.getMsg());
						d = req.getRequestDispatcher("telalogin.jsp");
					}
				}
				
				else if (operacao.equals("ALTERAR")) {
					if (!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
						req.getSession().setAttribute("cliente", resultado.getEntidades().get(0));
						d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
					} else {
						resultado.setMsg("Cliente_não_encontrado!");
						req.setAttribute("resultado", resultado.getMsg());
						d = req.getRequestDispatcher("telalogin.jsp");
					}
				}
			}

		else {
			if (operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormCliente.jsp");
			}
			if (operacao.equals("ALTERAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormCliente.jsp");
			}
		}

		// d = req.getRequestDispatcher("consultarcliente.jsp");

		/*
		 * req.getSession().setAttribute("resultado", resultado); d =
		 * req.getRequestDispatcher("FormCliente.jsp");
		 */

		/*
		 * if (resultado.getMsg() == null && operacao.equals("CONSULTAR")) {
		 * 
		 * req.setAttribute("cli", resultado.getEntidades().get(0)); d =
		 * req.getRequestDispatcher("consultarcliente.jsp"); }
		 * 
		 * if (resultado.getMsg() == null && operacao.equals("EXCLUIR")) {
		 * 
		 * req.getSession().setAttribute("resultado", null); d =
		 * req.getRequestDispatcher("consultarcliente.jsp"); }
		 * 
		 * if (resultado.getMsg() != null) { if (operacao.equals("SALVAR") ||
		 * operacao.equals("ALTERAR")) {
		 * 
		 * } }
		 */

		try {
			d.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}