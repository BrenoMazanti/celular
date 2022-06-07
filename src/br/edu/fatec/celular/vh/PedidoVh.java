package br.edu.fatec.celular.vh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.celular.dominio.Carrinho;
import br.edu.fatec.celular.dominio.Pedido;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class PedidoVh implements IViewHelper{
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		
		
		if(operacao != null && operacao.equals("CONSULTAR")) {
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			pedido.setCliente(cliente);
			
			if(req.getParameter("confirmado") == "false")
				pedido.setConfirmado(false);
			else
				pedido.setConfirmado(true);
			
		}
		if(operacao != null && operacao.equals("SALVAR")) {
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			pedido.setCliente(cliente);
			
			System.out.println(pedido.getCliente().getId());
			System.out.println(pedido.getCliente().getNome());
		}
		return pedido;
		
	}

	@Override
	public void setEntidade(HttpServletRequest req, HttpServletResponse resp, Resultado resultado)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		RequestDispatcher d = null;

		String operacao = req.getParameter("operacao");

		if (resultado.getMsg() == null) {
			if (operacao.equals("SALVAR")) {
				resultado.setMsg("Pedido salvo com sucesso!");
				req.setAttribute("resultado", resultado.getMsg());
				
				d = req.getRequestDispatcher("redirecionarpedido.jsp");
			}
			if (operacao.equals("CONSULTAR")) {
				System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("pedido", resultado.getEntidades().get(0));
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Pedido não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("redirecionarcarrinho.jsp");
				}
					
					
				
			}
			
			if (operacao.equals("LISTAR")) {
				System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("pedidos", resultado.getEntidades());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Pedido não encontrado!");
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
					resultado.setMsg("Pedido não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telalogin.jsp");
				}
				
			}
			
		}
		else {
			if(operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("redirecionarcarrinho.jsp");
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
