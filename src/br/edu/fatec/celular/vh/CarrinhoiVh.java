package br.edu.fatec.celular.vh;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.fatec.celular.dominio.Carrinhoi;
import br.edu.fatec.celular.dominio.Celular;
import br.edu.fatec.celular.dominio.Carrinho;
import br.edu.fatec.celular.dominio.Cliente;
import br.edu.fatec.celular.dominio.EntidadeDominio;
import br.edu.fatec.celular.util.Resultado;

public class CarrinhoiVh implements IViewHelper{
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest req) {
		String operacao = req.getParameter("operacao");
		Date dtCadastro;
		Calendar calendar;
		Carrinhoi carrinhoi = new Carrinhoi();
		Carrinho carrinho = new Carrinho();
		Cliente cliente = new Cliente();
		//Celular celular = new Celular();
		
		if(operacao != null && operacao.equals("SALVAR")) {
			
			calendar = Calendar.getInstance();
			dtCadastro = calendar.getTime();
			
			cliente = (Cliente) req.getSession().getAttribute("cliente");
			
			carrinho.getCliente().setId(cliente.getId());
			carrinho.setId(cliente.getId());
			
			carrinhoi.setDtCadastro(dtCadastro);
			carrinhoi.getCelular().setId(Integer.parseInt(req.getParameter("celularid")));
			carrinhoi.setPrecoUni(Double.parseDouble(req.getParameter("celularpreco")));
			carrinhoi.setQtde(1);
			carrinhoi.setCarrinho(carrinho);
		}
		
		if(operacao != null && operacao.equals("CONSULTAR")) {
			carrinho = (Carrinho) req.getSession().getAttribute("carrinho");
			carrinhoi.setCarrinho(carrinho);
		}
		
		if(operacao != null && operacao.equals("EXCLUIR")) {
			
			if(req.getParameter("carrinhoiid") != null && req.getParameter("carrinhoiid") != "")
				carrinhoi.setId(Integer.parseInt(req.getParameter("carrinhoiid")));
			
			else {
				cliente = (Cliente) req.getSession().getAttribute("cliente");
				
				carrinho.getCliente().setId(cliente.getId());
				carrinho.setId(cliente.getId());
				
				carrinhoi.setCarrinho(carrinho);
			}
		}
		return carrinhoi;
		
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
				
				d = req.getRequestDispatcher("redirecionarcarrinho.jsp");
			}
			if (operacao.equals("CONSULTAR")) {
				System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("carrinhoi", resultado.getEntidades().get(0));
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Carrinhoi não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telainicial.jsp");
				}
					
					
				
			}
			
			if (operacao.equals("LISTAR")) {
				System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					req.setAttribute("carrinhoies", resultado.getEntidades());
					d = req.getRequestDispatcher(req.getParameter("pagina"));
				}
				else {
					resultado.setMsg("Carrinhoi não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telainicial.jsp");
				}
					
					
				
			}
			
			if (operacao.equals("ALTERAR")) {
				//System.out.println(resultado.getEntidades());
				if(!resultado.getEntidades().isEmpty() && resultado.getEntidades().get(0) != null) {
					
					/*Cliente cli = (Cliente) req.getSession().getAttribute("cliente");
					Carrinhoi carrinhoi = (Carrinhoi) resultado.getEntidades().get(0);
					
					for(int i = 0; i < cli.getEnderecos().size(); i++) {
						
						if (carrinhoi.getId() == cli.getEnderecos().get(i).getId()) {
							cli.getEnderecos().set(i, endereco);
							req.getSession().setAttribute("cliente", cli);
							break;
						}
						
					}*/
					
					d = req.getRequestDispatcher((String) req.getSession().getAttribute("pagina"));
				}
				else {
					resultado.setMsg("Carrinhoi não encontrado!");
					req.setAttribute("resultado", resultado.getMsg());
					d = req.getRequestDispatcher("telalogin.jsp");
				}
				
			}
			
			if (operacao.equals("EXCLUIR")) {
				resultado.setMsg("Produto removido do carrinho com sucesso!");
				req.setAttribute("resultado", resultado.getMsg());
				
				d = req.getRequestDispatcher("redirecionarcarrinho.jsp");
			}
			
		}
		else {
			if(operacao.equals("SALVAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("iniciar.jsp");
			}
			if(operacao.equals("ALTERAR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("redirecionarcarrinho.jsp");
			}
			if(operacao.equals("EXCLUIR")) {
				req.setAttribute("resultado", resultado.getMsg());
				d = req.getRequestDispatcher("redirecionarcarrinho.jsp");
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
