package br.edu.fatec.celular.vh;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.celular.dominio.Administrador;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class AdministradorVh implements IViewHelper {

	public EntidadeDominio getEntidade(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");
		Administrador adm = new Administrador();
		Date dtCadastro;
		Calendar calendar;
		String email;
		String senha;

		if (operacao != null && operacao.equals("LOGIN")) {
			if (req.getParameter("txtEmail") != null && !req.getParameter("txtEmail").equals(""))
				email = req.getParameter("txtEmail");
			else
				email = "";

			if (req.getParameter("txtSenha") != null && !req.getParameter("txtSenha").equals(""))
				senha = req.getParameter("txtSenha");
			else
				senha = "";

			adm.setEmail(email);
			adm.setSenha(senha);
		}
		return adm;
	}

	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado)
			throws IOException, ServletException {
		RequestDispatcher d = null;

		String operacao = req.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("LOGIN")) {
				if (!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.getSession().setAttribute("adm", resultado.getEntidades().get(0));
					d = req.getRequestDispatcher("admInicial.jsp");
				} else {
					resultado.setMsg("Administrador_não_encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("admLogin.jsp");
				}

			}
		}
		try {
			d.forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
