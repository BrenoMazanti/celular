package br.edu.fatec.celular.vh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.Endereco;
import br.edu.fatec.celular.dominio.Cartao;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class CartaoVh implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");
		Cartao cartao = new Cartao();
		Cliente cliente = new Cliente();
		
		
		if(operacao != null && operacao.equals("CONSULTAR")) {
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			
			if(cliente != null) {
				cartao.setCliente(cliente);
			}
			
			if(req.getParameter("codigo") != null) {
				Integer id = Integer.valueOf(req.getParameter("codigo"));
				System.out.println(id);
				cartao.setId(id);
			}
		}
		return cartao;
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;

		String operacao = req.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Cartão cadastrado com sucesso!");
				req.setAttribute("resultado", resultado.getMsg());
				
				Cliente cli = (Cliente) req.getSession().getAttribute("cliente");
				cli.getCartoes().add((Cartao) resultado.getEntidades().get(0));
				
				req.getSession().setAttribute("cliente", cli);
				
				d = req.getRequestDispatcher("/Cartao?operacao=CONSULTAR");
			}
			if (operacao.equals("CONSULTAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("cartoes", resultado.getEntidades());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Cartão não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
					
					
				
			}
			
			if (operacao.equals("ALTERAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					
					Cliente cli = (Cliente) req.getSession().getAttribute("cliente");
					Endereco endereco = (Endereco) resultado.getEntidades().get(0);
					
					for(int i = 0; i < cli.getEnderecos().size(); i++) {
						
						if (endereco.getId() == cli.getEnderecos().get(i).getId()) {
							cli.getEnderecos().set(i, endereco);
							req.getSession().setAttribute("cliente", cli);
							break;
						}
						
					}
					
					d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
				}
				else {
					resultado.setMsg("Cartão não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telalogin.jsp");
				}
				
			}
			
		}
		else {
			if(operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormCartao.jsp");
			}
			if(operacao.equals("ALTERAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormCartao.jsp");
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
