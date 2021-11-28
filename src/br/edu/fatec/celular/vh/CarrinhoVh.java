package br.edu.fatec.celular.vh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.celular.dominio.Carrinho;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class CarrinhoVh implements IViewHelper{
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");
		Carrinho carrinho = new Carrinho();
		Cliente cliente = new Cliente();
		
		
		if(operacao != null && operacao.equals("CONSULTAR")) {
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			carrinho.setCliente(cliente);
		}
		return carrinho;
		
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;

		String operacao = req.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Produto adicionado com sucesso!");
				req.setAttribute("resultado", resultado.getMsg());
				
				d = req.getRequestDispatcher("./Carrinho?operacao=CONSULTAR&pagina=Carrinho.jsp");
			}
			if (operacao.equals("CONSULTAR")) {
				System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("carrinho", resultado.getEntidades().get(0));
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Carrinho não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telainicial.jsp");
				}
					
					
				
			}
			
			if (operacao.equals("LISTAR")) {
				System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("carrinhoes", resultado.getEntidades());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Carrinho não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telainicial.jsp");
				}
					
					
				
			}
			
			if (operacao.equals("ALTERAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					
					/*Cliente cli = (Cliente) req.getSession().getAttribute("cliente");
					Carrinho carrinho = (Carrinho) resultado.getEntidades().get(0);
					
					for(int i = 0; i < cli.getEnderecos().size(); i++) {
						
						if (carrinho.getId() == cli.getEnderecos().get(i).getId()) {
							cli.getEnderecos().set(i, endereco);
							req.getSession().setAttribute("cliente", cli);
							break;
						}
						
					}*/
					
					d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
				}
				else {
					resultado.setMsg("Carrinho não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telalogin.jsp");
				}
				
			}
			
		}
		else {
			if(operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormCarrinho.jsp");
			}
			if(operacao.equals("ALTERAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("FormCarrinho.jsp");
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
